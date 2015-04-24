package com.github.siphon;

public abstract class ConstantInfo {
    public static enum Type{
        Utf8(1), Integer(3), Float(4), Long(5), Double(6),
        Class(7), String(8), Fieldref(9), Methodref(10),
        InterfaceMethodref(11), NameAndType(12), MethodHandle(15),
        MethodType(16), InvokeDynamic(17);

        private int tag;

        Type(int tag){
            this.tag = tag;
        }

        public int getTag(){
            return tag;
        }
    };
    Type type;
    int index;

    public ConstantInfo(Type type){
        this.type = type;
    }

    public int getIndex(){
        return index;
    }

    public int getTag(){
        return type.tag;
    }

    public Type getType(){
        return type;
    }

    public abstract void resolve(ConstantPool pool);
}
