package com.github.siphon.attributes;

import java.io.DataInputStream;
import java.io.IOException;

import com.github.siphon.ClassFile;
import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantPool;
import com.github.siphon.constants.ConstantUtf8Info;

public class AttributeInfoFactoryBuilder {
    private static AttributeInfoFactoryBuilder builder = new AttributeInfoFactoryBuilder();

    private AttributeInfoFactoryBuilder(){
    }

    public static AttributeInfoFactoryBuilder getBuilder(){
        return builder;
    }

    public AttributeInfoFactory getFactory(ClassFile cf, DataInputStream in) throws IOException{
        int attributeNameIndex = in.readShort();
        ConstantPool pool = cf.getConstantPool();

        ConstantInfo info = pool.get(attributeNameIndex);
        String attributeName = null;
        if(info.getType() == ConstantInfo.Type.Utf8){
            attributeName = ((ConstantUtf8Info)info).getValue();
        }

        return buildFactory(attributeName, attributeNameIndex);
    }

    private AttributeInfoFactory buildFactory(String name, int index){
        AttributeInfoFactory factory;
        switch(name){
        default:
            factory = new DefaultAttributeInfoFactory(name, index);
            break;
        }
        return factory;
    }
}
