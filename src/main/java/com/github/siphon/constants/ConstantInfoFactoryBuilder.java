package com.github.siphon.constants;

import java.util.HashMap;
import java.util.Map;

import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantInfoFactory;

public class ConstantInfoFactoryBuilder {
    private static ConstantInfoFactoryBuilder builder = new ConstantInfoFactoryBuilder();

    public static ConstantInfoFactoryBuilder getBuilder(){
        return builder;
    }

    private Map<ConstantInfo.Type, ConstantInfoFactory> factories = new HashMap<>();

    private ConstantInfo.Type[] types = new ConstantInfo.Type[] {
        null, ConstantInfo.Type.Utf8, null, ConstantInfo.Type.Integer,
        ConstantInfo.Type.Float, ConstantInfo.Type.Long, ConstantInfo.Type.Double,
        ConstantInfo.Type.Class, ConstantInfo.Type.String, ConstantInfo.Type.Fieldref,
        ConstantInfo.Type.Methodref, ConstantInfo.Type.InterfaceMethodref,
        ConstantInfo.Type.NameAndType, ConstantInfo.Type.MethodHandle,
        ConstantInfo.Type.MethodType, ConstantInfo.Type.InvokeDynamic,        
    };

    private ConstantInfoFactoryBuilder(){
        factories.put(ConstantInfo.Type.Utf8, new ConstantUtf8InfoFactory());
        factories.put(ConstantInfo.Type.Integer, new ConstantNumberInfoFactory(ConstantInfo.Type.Integer));
        factories.put(ConstantInfo.Type.Float, new ConstantNumberInfoFactory(ConstantInfo.Type.Float));
        factories.put(ConstantInfo.Type.Long, new ConstantNumberInfoFactory(ConstantInfo.Type.Long));
        factories.put(ConstantInfo.Type.Double, new ConstantNumberInfoFactory(ConstantInfo.Type.Double));
        factories.put(ConstantInfo.Type.Class, new ConstantClassInfoFactory());
        factories.put(ConstantInfo.Type.String, new ConstantStringInfoFactory());
        factories.put(ConstantInfo.Type.Fieldref, new ConstantRefInfoFactory(ConstantInfo.Type.Fieldref));
        factories.put(ConstantInfo.Type.Methodref, new ConstantRefInfoFactory(ConstantInfo.Type.Methodref));
        factories.put(ConstantInfo.Type.InterfaceMethodref, new ConstantRefInfoFactory(ConstantInfo.Type.InterfaceMethodref));
        factories.put(ConstantInfo.Type.NameAndType, new ConstantNameAndTypeInfoFactory());
        factories.put(ConstantInfo.Type.MethodHandle, new ConstantMethodHandleInfoFactory());
        factories.put(ConstantInfo.Type.MethodType, new ConstantMethodTypeFactory());
        factories.put(ConstantInfo.Type.InvokeDynamic, new ConstantInvokeDynamicInfoFactory());
    }

    public ConstantInfoFactory getFactory(ConstantInfo.Type tag){
        return factories.get(tag);
    }

    public ConstantInfoFactory getFactory(int tag){
        ConstantInfoFactory factory = null;
        if(tag >= 0 && tag < types.length){
            factory = getFactory(types[tag]);
        }
        return factory;
    }

    public ConstantInfo.Type getType(int tag){
        ConstantInfo.Type type = null;
        if(tag >= 0 && tag < types.length){
            type = types[tag];
        }
        return type;
    }
}
