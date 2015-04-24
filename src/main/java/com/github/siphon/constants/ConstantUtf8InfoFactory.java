package com.github.siphon.constants;

import java.io.DataInputStream;
import java.io.IOException;

import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantInfo.Type;
import com.github.siphon.ConstantInfoFactory;

public class ConstantUtf8InfoFactory extends ConstantInfoFactory {

    @Override
    public Type getType() {
        return ConstantInfo.Type.String;
    }

    @Override
    public ConstantInfo create(DataInputStream in) throws IOException {
        ConstantUtf8Info utf8 = new ConstantUtf8Info();
        utf8.length = in.readShort();
        byte[] data = new byte[utf8.length];
        in.readFully(data);
        utf8.value = new String(data);

        return utf8;
    }
}
