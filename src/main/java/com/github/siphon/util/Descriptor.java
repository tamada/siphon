package com.github.siphon.util;

import java.util.ArrayList;
import java.util.List;

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

    protected String getPrimitiveType(char c){
        String type = null;

        if(c == 'B') type = "byte";
        else if(c == 'C') type = "char";
        else if(c == 'D') type = "double";
        else if(c == 'F') type = "float";
        else if(c == 'I') type = "int";
        else if(c == 'J') type = "long";
        else if(c == 'S') type = "short";
        else if(c == 'Z') type = "boolean";

        return type;
    }

    protected String parseType(String string){
        int array = 0;
        String type = null;
        boolean primitive = true;

        for(char c: string.toCharArray()){
            if(c == '[')      array++;
            else if(c == 'L'){
                primitive = false;
                break;
            }
            else{
                type = getPrimitiveType(c);
            }
        }
        if(!primitive){
            int index1 = string.indexOf('L');
            int index2 = string.indexOf(';');
            type = string.substring(index1 + 1, index2).replace('/', '.');
        }
        for(int i = 0; i < array; i++){
            type = type + "[]";
        }
        return type;
    }

    public static class Method extends Descriptor{
        private String[] params;
        private String returnType;

        public Method(String desc){
            super(desc);
            parse();
        }

        @Override
        public void parse() {
            List<String> paramList = new ArrayList<>();
            String desc = getDescriptor();
            int index1 = desc.indexOf('(');
            int index2 = desc.indexOf(')');

            String paramString = desc.substring(index1 + 1, index2);
            int array = 0;
            int index = 0;
            int length = paramString.length();
            while(index < length){
                char c = paramString.charAt(index);
                if(c == '[')    array++;
                else{
                    String type;
                    if(c == 'L'){
                        int index3 = paramString.indexOf(';', index);
                        type = paramString.substring(index + 1, index3).replace('/', '.');
                        index = index3;
                    }
                    else{
                        type = getPrimitiveType(c);
                    }
                    for(int i = 0; i < array; i++){
                        type = type + "[]";
                    }
                    paramList.add(type);
                    array = 0;
                }
                index++;
            }

            params = paramList.toArray(new String[paramList.size()]);
            returnType = parseType(desc.substring(index2 + 1));
            if(returnType == null){
                returnType = "void";
            }
        }

        @Override
        public String format(String name) {
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for(String param: params){
                if(!first){
                    sb.append(", ");
                }
                sb.append(param);
                first = false;
            }
            if(name.equals("<init>") || name.equals("<clinit>")){
                return String.format("%s(%s)", name, new String(sb));
            }
            return String.format("%s %s(%s)", returnType, name, new String(sb));
        }
    }

    public static class Field extends Descriptor{
        private String type;

        public Field(String desc){
            super(desc);
            parse();
        }

        @Override
        public void parse() {
            this.type = parseType(getDescriptor());
        }

        @Override
        public String format(String name) {
            return String.format("%s %s", type, name);
        }
    }
}
