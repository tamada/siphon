package com.github.siphon.constants;

import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantPool;

public class ConstantNameAndTypeInfo extends ConstantInfo {
    int nameIndex;
    int descriptorIndex;

    private String nameValue;
    private String descriptorValue;

    public String getNameValue(){
        return nameValue;
    }

    public String getDescriptorValue(){
        return descriptorValue;
    }

    public ConstantNameAndTypeInfo() {
        super(ConstantInfo.Type.NameAndType);
    }

    public int getNameIndex(){
        return nameIndex;
    }

    public int getDescriptorIndex(){
        return descriptorIndex;
    }

    @Override
    public void resolve(ConstantPool pool) {
        nameValue = pool.getUtf8(getNameIndex());
        descriptorValue = pool.getUtf8(getDescriptorIndex());
    }
}
