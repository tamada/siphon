package com.github.siphon.visitor;

public class MethodInfoVisitor {

    public MethodInfoVisitor(int access, String name, String descriptor){
    }

    public AttributeInfoVisitor visitAttributeInfo(String name){
        return new AttributeInfoVisitor(name);
    }
}
