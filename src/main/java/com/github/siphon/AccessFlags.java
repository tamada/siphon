package com.github.siphon;

import java.util.ArrayList;
import java.util.List;

public class AccessFlags {
    public static final int ACC_PUBLIC       = 0x0001;
    public static final int ACC_PRIVATE      = 0x0002;
    public static final int ACC_PROTECTED    = 0x0004;
    public static final int ACC_STATIC       = 0x0008;
    public static final int ACC_FINAL        = 0x0010;
    public static final int ACC_SYNCHRONIZED = 0x0020;
    public static final int ACC_SUPER        = 0x0020;
    public static final int ACC_VOLATILE     = 0x0040;
    public static final int ACC_BRIDGE       = 0x0040;
    public static final int ACC_TRANSIENT    = 0x0080;
    public static final int ACC_VARARGS      = 0x0080;
    public static final int ACC_NATIVE       = 0x0100;
    public static final int ACC_INTERFACE    = 0x0200;
    public static final int ACC_ABSTRACT     = 0x0400;
    public static final int ACC_STRICT       = 0x0800;
    public static final int ACC_SYNTHETIC    = 0x1000;
    public static final int ACC_ANNOTATION   = 0x2000;
    public static final int ACC_ENUM         = 0x4000;

    private static final int CLASS_FLAG = ACC_PUBLIC | ACC_FINAL | ACC_SUPER | ACC_INTERFACE |
            ACC_ABSTRACT | ACC_SYNTHETIC | ACC_ANNOTATION | ACC_ENUM;
    private static final int FIELD_FLAG = ACC_PUBLIC | ACC_PRIVATE | ACC_PROTECTED | ACC_STATIC |
            ACC_FINAL | ACC_VOLATILE | ACC_TRANSIENT | ACC_SYNTHETIC | ACC_ENUM;
    private static final int METHOD_FLAG = ACC_PUBLIC | ACC_PRIVATE | ACC_PROTECTED | ACC_STATIC |
            ACC_FINAL | ACC_SYNCHRONIZED | ACC_BRIDGE | ACC_VARARGS | ACC_NATIVE | ACC_ABSTRACT | 
            ACC_STRICT | ACC_SYNTHETIC;

    private int flags;

    public AccessFlags(int flags){
        this.flags = flags;
    }

    public boolean isClassFlags(){
        return isClassFlag(flags);
    }

    public boolean isMethodFlags(){
        return isMethodFlag(flags);
    }

    public boolean isFieldFlags(){
        return isFieldFlag(flags);
    }

    public static boolean isClassFlag(int access){
        return (access | CLASS_FLAG) == CLASS_FLAG;
    }

    public static boolean isFieldFlag(int access){
        return (access | FIELD_FLAG) == FIELD_FLAG;
    }

    public static boolean isMethodFlag(int access){
        return (access | METHOD_FLAG) == METHOD_FLAG;
    }

    public static String toStringMethod(int access){
        List<String> items = new ArrayList<>();
        if(isOn(access, ACC_PUBLIC))       items.add("public");
        if(isOn(access, ACC_PRIVATE))      items.add("private");
        if(isOn(access, ACC_PROTECTED))    items.add("protected");
        if(isOn(access, ACC_STATIC))       items.add("static");
        if(isOn(access, ACC_FINAL))        items.add("final");
        if(isOn(access, ACC_ABSTRACT))     items.add("abstract");
        if(isOn(access, ACC_SYNCHRONIZED)) items.add("synchronized");
        if(isOn(access, ACC_BRIDGE))       items.add("bridge");
        if(isOn(access, ACC_VARARGS))      items.add("varargs");
        if(isOn(access, ACC_NATIVE))       items.add("native");
        if(isOn(access, ACC_STRICT))       items.add("strict");
        if(isOn(access, ACC_SYNTHETIC))    items.add("synthetic");

        return toString(items);
    }

    public static String toStringClass(int access){
        List<String> items = new ArrayList<>();
        if(isOn(access, ACC_PUBLIC))     items.add("public");
        if(isOn(access, ACC_FINAL))      items.add("final");
        if(isOn(access, ACC_ABSTRACT))   items.add("abstract");
        if(isOn(access, ACC_SYNTHETIC))  items.add("synthetic");
        if(isOn(access, ACC_INTERFACE))  items.add("interface");
        if(isOn(access, ACC_ENUM))       items.add("enum");
        if(isOn(access, ACC_ANNOTATION)) items.add("annotation");
        if(isOff(access, ACC_INTERFACE | ACC_ENUM | ACC_ANNOTATION)){
            items.add("class");
        }
        return toString(items);
    }

    public static String toStringField(int access){
        List<String> items = new ArrayList<>();
        if(isOn(access, ACC_PUBLIC))     items.add("public");
        if(isOn(access, ACC_PRIVATE))    items.add("private");
        if(isOn(access, ACC_PROTECTED))  items.add("protected");
        if(isOn(access, ACC_STATIC))     items.add("static");
        if(isOn(access, ACC_FINAL))      items.add("final");
        if(isOn(access, ACC_VOLATILE))   items.add("volatile");
        if(isOn(access, ACC_TRANSIENT))  items.add("transient");
        if(isOn(access, ACC_SYNTHETIC))  items.add("synthetic");
        if(isOn(access, ACC_ENUM))       items.add("enum");

        return toString(items);
    }

    private static String toString(List<String> items){
        StringBuilder sb = new StringBuilder();

        boolean first = true;
        for(String item: items){
            if(!first){
                sb.append(" ");
            }
            sb.append(item);
            first = false;
        }
        return new String(sb);
    }

    private static boolean isOff(int flag, int check){
        return (flag & check) == 0;
    }

    private static boolean isOn(int flag, int check){
        return (flag & check) == check;
    }
}
