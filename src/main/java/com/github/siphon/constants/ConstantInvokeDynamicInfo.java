package com.github.siphon.constants;

import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantPool;

public class ConstantInvokeDynamicInfo extends ConstantInfo {
    int bootstrapMethodAttrIndex;
    int nameAndTypeIndex;

    private ConstantNameAndTypeInfo nameAndTypeValue;

    public ConstantInvokeDynamicInfo() {
        super(ConstantInfo.Type.InvokeDynamic);
    }

    public int getBootstrapMethodAttrIndex(){
        return bootstrapMethodAttrIndex;
    }

    public int getNameAndTypeIndex(){
        return nameAndTypeIndex;
    }

    public ConstantNameAndTypeInfo getNameAndTypeValue(){
        return nameAndTypeValue;
    }

    @Override
    public void resolve(ConstantPool pool) {
        nameAndTypeValue = pool.getNameAndTypeInfo(getNameAndTypeIndex());
    }
}
