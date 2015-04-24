package com.github.siphon.constants;

import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantPool;

public class ConstantMethodTypeInfo extends ConstantInfo{
    int descriptorIndex;

    private String descriptorValue;

    public ConstantMethodTypeInfo() {
        super(Type.MethodType);
    }

    public int getDescriptorIndex(){
        return descriptorIndex;
    }

    public String getDescriptorValue(){
        return descriptorValue;
    }

    @Override
    public void resolve(ConstantPool pool) {
        descriptorValue = pool.getUtf8(getDescriptorIndex());
    }
}
