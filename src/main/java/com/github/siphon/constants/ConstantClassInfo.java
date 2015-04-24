package com.github.siphon.constants;

import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantPool;

public class ConstantClassInfo extends ConstantInfo{
    int nameIndex;
    private String name;

    public ConstantClassInfo() {
        super(Type.Class);
    }

    public int getNameIndex(){
        return nameIndex;
    }

    public String getNameValue(){
        return name;
    }

    public void resolve(ConstantPool pool){
        this.name = pool.getUtf8(getNameIndex());
    }
}
