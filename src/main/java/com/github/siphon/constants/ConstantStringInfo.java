package com.github.siphon.constants;

import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantPool;

public class ConstantStringInfo extends ConstantInfo {
    int stringIndex;

    private String stringValue;

    public ConstantStringInfo() {
        super(ConstantInfo.Type.String);
    }

    public int getStringIndex(){
        return stringIndex;
    }

    public String getStringValue(){
        return stringValue;
    }

    @Override
    public void resolve(ConstantPool pool) {
        stringValue = pool.getUtf8(getStringIndex());
    }
}
