package com.github.siphon.constants;

import java.io.DataInputStream;
import java.io.IOException;

import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantInfoFactory;
import com.github.siphon.ConstantInfo.Type;

public class ConstantMethodHandleInfoFactory extends ConstantInfoFactory {
    @Override
    public Type getType() {
        return ConstantInfo.Type.NameAndType;
    }

    @Override
    public ConstantInfo create(DataInputStream in) throws IOException {
        ConstantMethodHandleInfo mhandle = new ConstantMethodHandleInfo();

        mhandle.referenceKind = in.readByte();
        mhandle.referenceIndex = in.readShort();

        return mhandle;
    }

}
