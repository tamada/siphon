package com.github.siphon.constants;

import java.io.DataInputStream;
import java.io.IOException;

import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantInfoFactory;
import com.github.siphon.ConstantInfo.Type;

public class ConstantNumberInfoFactory extends ConstantInfoFactory {
    private ConstantInfo.Type target;

    ConstantNumberInfoFactory(ConstantInfo.Type type){
        this.target = type;
    }

    @Override
    public Type getType() {
        return target;
    }

    @Override
    public ConstantInfo create(DataInputStream in) throws IOException {
        ConstantNumberInfo num = new ConstantNumberInfo(getType());

        switch(getType()){
        case Integer:
            num.number = new Integer(in.readInt());
            break;
        case Float:
            num.number = new Float(in.readFloat());
            break;
        case Long:
            num.number = new Long(in.readLong());
            break;
        case Double:
            num.number = new Double(in.readDouble());
            break;
        default:
            throw new InternalError("unknown type: " + getType());
        }

        return num;
    }

}
