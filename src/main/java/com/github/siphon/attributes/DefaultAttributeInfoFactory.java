package com.github.siphon.attributes;

import java.io.DataInputStream;
import java.io.IOException;

import com.github.siphon.AttributeInfo;

public class DefaultAttributeInfoFactory extends AbstractAttributeInfoFactory{
    public DefaultAttributeInfoFactory(String name, int index){
        super(name, index);
    }

    @Override
    public AttributeInfo createInfo(String name, int index, DataInputStream in) throws IOException{
        DefaultAttributeInfo info = new DefaultAttributeInfo(name, index);
        info.attributeLength = in.readInt();

        info.info = new byte[info.attributeLength];
        in.readFully(info.info);

        return info;
    }
}
