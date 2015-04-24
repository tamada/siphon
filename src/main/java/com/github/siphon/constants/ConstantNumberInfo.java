package com.github.siphon.constants;

import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantPool;

public class ConstantNumberInfo extends ConstantInfo {
    Number number;

    public ConstantNumberInfo(ConstantInfo.Type type){
        super(type);
    }

    public Number getValue(){
        return number;
    }

    @Override
    public void resolve(ConstantPool pool) {
        //  nothing to do.
    }
}
