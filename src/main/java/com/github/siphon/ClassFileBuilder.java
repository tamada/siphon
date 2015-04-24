package com.github.siphon;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import com.github.siphon.attributes.AttributeInfoFactory;
import com.github.siphon.attributes.AttributeInfoFactoryBuilder;
import com.github.siphon.constants.ConstantInfoFactoryBuilder;

public class ClassFileBuilder {
    private static final ClassFileBuilder builder = new ClassFileBuilder();

    public static ClassFileBuilder getInstance(){
        return builder;
    }

    public ClassFile build(String file) throws IOException, SiphonException{
        try(DataInputStream in = new DataInputStream(new FileInputStream(file))){
            ClassFile cf = new ClassFile();
            buildHeader(cf, in);
            cf.pool = buildConstantPool(cf, in);
            buildBody(cf, in);
            cf.fields = buildFields(cf, in);
            cf.methods = buildMethods(cf, in);
            cf.attributes = buildAttributes(cf, in);

            return cf;
        }
    }

    public MethodList buildMethods(ClassFile cf, DataInputStream in) throws IOException, SiphonException{
        int count = in.readShort();
        MethodList list = new MethodList(count);

        while(count > list.getCurrentSize()){
            MethodInfo info = new MethodInfo();
            info.accessFlags = in.readShort();
            info.nameIndex = in.readShort();
            info.descriptorIndex = in.readShort();
            info.attributes = buildAttributes(cf, in);

            list.list.add(info);
        }
        return list;
    }

    public FieldList buildFields(ClassFile cf, DataInputStream in) throws IOException, SiphonException{
        int count = in.readShort();
        FieldList list = new FieldList(count);

        while(count > list.getCurrentSize()){
            FieldInfo info = new FieldInfo();
            info.accessFlags = in.readShort();
            info.nameIndex = in.readShort();
            info.descriptorIndex = in.readShort();
            info.attributes = buildAttributes(cf, in);

            list.list.add(info);
        }
        return list;
    }

    public AttributeList buildAttributes(ClassFile cf, DataInputStream in) throws IOException, SiphonException{
        int count = in.readShort();
        AttributeList list = new AttributeList(count);

        AttributeInfoFactoryBuilder builder = AttributeInfoFactoryBuilder.getBuilder();
        while(count > list.getCurrentSize()){
            AttributeInfoFactory factory = builder.getFactory(cf, in);
            AttributeInfo info = factory.create(in);
            list.list.add(info);
        }
        return list;
    }

    public ClassFile buildHeader(ClassFile cf, DataInputStream in) throws IOException{
        cf.magic = in.readInt();
        cf.minor = in.readShort();
        cf.major = in.readShort();

        return cf;
    }

    public ConstantPool buildConstantPool(ClassFile cf, DataInputStream in) throws IOException, SiphonException{
        int size = in.readShort();
        ConstantPool pool = new ConstantPool(size);

        while(size > pool.getCurrentSize()){
            int tag = in.readByte();
            ConstantInfoFactory factory = ConstantInfoFactoryBuilder.getBuilder().getFactory(tag);
            if(factory == null){
                throw new UnknownConstantInfoException(tag + ": unknown tag");
            }
            ConstantInfo info = factory.create(in);
            info.index = pool.getCurrentSize();
            pool.add(info);
        }

        return pool;
    }

    public ClassFile buildBody(ClassFile cf, DataInputStream in) throws IOException{
        cf.accessFlag = in.readShort();
        cf.thisClass = in.readShort();
        cf.superClass = in.readShort();
        cf.interfaceCount = in.readShort();
        cf.interfaces = new int[cf.interfaceCount];
        for(int i = 0; i < cf.interfaceCount; i++){
            cf.interfaces[i] = in.readShort();
        }

        return cf;
    }
}
