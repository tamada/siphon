package com.github.siphon.constants;

import java.io.DataInputStream;
import java.io.IOException;

import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantInfoFactory;
import com.github.siphon.ConstantInfo.Type;

public class ConstantClassInfoFactory extends ConstantInfoFactory{

    @Override
    public Type getType() {
        return ConstantInfo.Type.Class;
    }

    @Override
    public ConstantInfo create(DataInputStream in) throws IOException {
        ConstantClassInfo classInfo = new ConstantClassInfo();

        classInfo.nameIndex = in.readShort();

        return classInfo;
    }
    
}
