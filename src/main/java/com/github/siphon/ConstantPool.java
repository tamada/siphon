package com.github.siphon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.github.siphon.constants.ConstantClassInfo;
import com.github.siphon.constants.ConstantNameAndTypeInfo;
import com.github.siphon.constants.ConstantUtf8Info;

public class ConstantPool implements Iterable<ConstantInfo>{
    private static final String OUT_OF_RANGE = "<out of range: %d>";
    private static final String NULL_VALUE = "<null value: %d>";
    private static final String NOT_UTF8_INFO = "<not utf8: %d>";

    int size;
    List<ConstantInfo> pool;

    ConstantPool(int size){
        pool = new ArrayList<>();
        pool.add(null);
        this.size = size;
    }

    public int getSize(){
        return size;
    }

    public String getUtf8(int index){
        String value;
        if(index < 0 || index >= pool.size()){
            value = String.format(OUT_OF_RANGE, index);
        }
        else{
            ConstantInfo info = get(index);
            if(info == null){
                value = String.format(NULL_VALUE, index);
            }
            else if(info instanceof ConstantUtf8Info){
                value = ((ConstantUtf8Info)info).getValue();
            }
            else{
                value = String.format(NOT_UTF8_INFO, index);
            }
        }
        return value;
    }

    public Iterator<ConstantInfo> iterator(){
        return pool.iterator();
    }

    int getCurrentSize(){
        return pool.size() + 1;
    }

    public ConstantInfo get(int index){
        if(index >= 0 && index < pool.size()){
            return pool.get(index);
        }
        return null;
    }

    boolean add(ConstantInfo info){
        pool.add(info);
        boolean isNextEmptyFlag = false;

        if(info.getType() == ConstantInfo.Type.Double ||
                info.getType() == ConstantInfo.Type.Long){
            pool.add(null);
            isNextEmptyFlag = true;
        }
        return isNextEmptyFlag;
    }

    public ConstantClassInfo getClassInfo(int classIndex) {
        ConstantInfo info = get(classIndex);
        if(info instanceof ConstantClassInfo){
            return (ConstantClassInfo)info;
        }
        return null;
    }

    public ConstantNameAndTypeInfo getNameAndTypeInfo(int nameAndTypeIndex) {
        ConstantInfo info = get(nameAndTypeIndex);
        if(info instanceof ConstantNameAndTypeInfo){
            return (ConstantNameAndTypeInfo)info;
        }
        return null;
    }
}
