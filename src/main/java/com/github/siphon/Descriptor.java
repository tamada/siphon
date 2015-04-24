package com.github.siphon;

public abstract class Descriptor {
    private String descriptor;

    public Descriptor(String descriptor){
        this.descriptor = descriptor;
    }

    public String getDescriptor(){
        return descriptor;
    }

    public abstract void parse();

    public abstract String format(String name);

    public static class Method extends Descriptor{
        private String[] params;
        private String returnType;

        public Method(String desc){
            super(desc);
        }

        @Override
        public void parse() {
        }

        @Override
        public String format(String name) {
            return getDescriptor() + " " + name;
        }
    }

    public static class Field extends Descriptor{
        private String type;

        public Field(String desc){
            super(desc);
        }

        @Override
        public void parse() {
            
        }

        @Override
        public String format(String name) {
            return getDescriptor() + " " + name;
        }
    }
}
