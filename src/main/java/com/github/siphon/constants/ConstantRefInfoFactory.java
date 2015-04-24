package com.github.siphon.constants;

import java.io.DataInputStream;
import java.io.IOException;

import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantInfoFactory;
import com.github.siphon.ConstantInfo.Type;

public class ConstantRefInfoFactory extends ConstantInfoFactory {
    private ConstantInfo.Type target;

    ConstantRefInfoFactory(ConstantInfo.Type type){
        this.target = type;
    }

    @Override
    public Type getType() {
        return target;
    }

    @Override
    public ConstantInfo create(DataInputStream in) throws IOException {
        ConstantRefInfo ref = new ConstantRefInfo(getType());

        ref.classIndex = in.readShort();
        ref.nameAndTypeIndex = in.readShort();

        return ref;
    }

}
