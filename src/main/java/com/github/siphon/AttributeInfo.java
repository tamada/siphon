package com.github.siphon;

public abstract class AttributeInfo {
    int attributeNameIndex;
    String name;

    public AttributeInfo(String name, int attributeNameIndex){
        this.name = name;
        this.attributeNameIndex = attributeNameIndex;
    }

    public String getName(){
        return name;
    }

    public int getAttributeNameIndex(){
        return attributeNameIndex;
    }
}
