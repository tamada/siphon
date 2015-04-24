package com.github.siphon.constants;

import java.io.DataInputStream;
import java.io.IOException;

import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantInfo.Type;
import com.github.siphon.ConstantInfoFactory;

public class ConstantInvokeDynamicInfoFactory extends ConstantInfoFactory {
    @Override
    public Type getType() {
        return ConstantInfo.Type.InvokeDynamic;
    }

    @Override
    public ConstantInfo create(DataInputStream in) throws IOException {
        ConstantInvokeDynamicInfo info = new ConstantInvokeDynamicInfo();

        info.bootstrapMethodAttrIndex = in.readShort();
        info.nameAndTypeIndex = in.readShort();

        return info;
    }

}
