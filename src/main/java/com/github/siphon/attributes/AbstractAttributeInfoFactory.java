package com.github.siphon.attributes;

import java.io.DataInputStream;
import java.io.IOException;

import com.github.siphon.AttributeInfo;

public abstract class AbstractAttributeInfoFactory implements AttributeInfoFactory {
    private int attributeNameIndex;
    private String name;

    public AbstractAttributeInfoFactory(String name, int index){
        this.name = name;
        this.attributeNameIndex = index;
    }

    public String getName(){
        return name;
    }

    public int getAttributeNameIndex(){
        return attributeNameIndex;
    }

    public abstract AttributeInfo createInfo(String name, int index, DataInputStream in) throws IOException;

    @Override
    public final AttributeInfo create(DataInputStream in) throws IOException{
        return createInfo(name, attributeNameIndex, in);
    }

}
