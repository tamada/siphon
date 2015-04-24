package com.github.siphon.constants;

import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantPool;

public class ConstantUtf8Info extends ConstantInfo {
    int length;
    String value;

    public ConstantUtf8Info() {
        super(ConstantInfo.Type.Utf8);
    }

    public int getLength(){
        return length;
    }

    public String getValue(){
        return value;
    }

    @Override
    public void resolve(ConstantPool pool) {
        // nothing to do.
    }

    public String toString(){
        return "constant utf8: " + value;
    }
}
