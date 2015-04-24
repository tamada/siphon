package com.github.siphon.constants;

import java.io.DataInputStream;
import java.io.IOException;

import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantInfoFactory;
import com.github.siphon.ConstantInfo.Type;

public class ConstantStringInfoFactory extends ConstantInfoFactory {

    @Override
    public Type getType() {
        return ConstantInfo.Type.String;
    }

    @Override
    public ConstantInfo create(DataInputStream in) throws IOException {
        ConstantStringInfo string = new ConstantStringInfo();
        string.stringIndex = in.readShort();

        return string;
    }
}
