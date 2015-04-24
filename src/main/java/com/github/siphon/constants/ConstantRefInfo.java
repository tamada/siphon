package com.github.siphon.constants;

import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantPool;

public class ConstantRefInfo extends ConstantInfo {
    int classIndex;
    int nameAndTypeIndex;

    private ConstantClassInfo classInfo;
    private ConstantNameAndTypeInfo nameAndTypeInfo;

    public ConstantRefInfo(Type type) {
        super(type);
    }

    public int getClassIndex(){
        return classIndex;
    }

    public int getNameAndTypeIndex(){
        return nameAndTypeIndex;
    }

    public ConstantClassInfo getClassInfo(){
        return classInfo;
    }

    public ConstantNameAndTypeInfo getNameAndTypeInfo(){
        return nameAndTypeInfo;
    }

    @Override
    public void resolve(ConstantPool pool) {
        classInfo = pool.getClassInfo(classIndex);
        nameAndTypeInfo = pool.getNameAndTypeInfo(nameAndTypeIndex);
    }

}
