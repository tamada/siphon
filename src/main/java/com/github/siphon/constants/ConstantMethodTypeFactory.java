package com.github.siphon.constants;

import java.io.DataInputStream;
import java.io.IOException;

import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantInfoFactory;
import com.github.siphon.ConstantInfo.Type;

public class ConstantMethodTypeFactory extends ConstantInfoFactory{

    @Override
    public Type getType() {
        return ConstantInfo.Type.MethodType;
    }

    @Override
    public ConstantInfo create(DataInputStream in) throws IOException {
        ConstantMethodTypeInfo methodType = new ConstantMethodTypeInfo();

        methodType.descriptorIndex = in.readShort();

        return methodType;
    }
    
}
