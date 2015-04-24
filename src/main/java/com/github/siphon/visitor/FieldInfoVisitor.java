package com.github.siphon.visitor;

public class FieldInfoVisitor {
    public FieldInfoVisitor(int access, String name, String descriptor){
    }

    public AttributeInfoVisitor visitAttributeInfo(String name){
        return new AttributeInfoVisitor(name);
    }
}
