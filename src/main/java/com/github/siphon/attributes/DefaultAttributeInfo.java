package com.github.siphon.attributes;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.github.siphon.AttributeInfo;

public class DefaultAttributeInfo extends AttributeInfo{
    int attributeLength;
    byte[] info;

    public DefaultAttributeInfo(String name, int index){
        super(name, index);
    }

    public int getAttributeLength(){
        return attributeLength;
    }

    public InputStream getInfo(){
        return new ByteArrayInputStream(info);
    }
}
