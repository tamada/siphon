package com.github.siphon;

public class ClassFile {
    int magic;
    int minor;
    int major;

    ConstantPool pool;

    int accessFlag;
    int thisClass;
    int superClass;
    int interfaceCount;
    int[] interfaces;

    FieldList fields;
    MethodList methods;
    AttributeList attributes;

    public ConstantPool getConstantPool(){
        return pool;
    }
}
