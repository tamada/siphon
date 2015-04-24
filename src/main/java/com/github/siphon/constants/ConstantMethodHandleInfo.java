package com.github.siphon.constants;

import com.github.siphon.ConstantInfo;
import com.github.siphon.ConstantPool;

public class ConstantMethodHandleInfo extends ConstantInfo {
    public static enum Kind{
        REF_getField(1, ConstantInfo.Type.Fieldref), REF_getStatic(2, ConstantInfo.Type.Fieldref),
        REF_putField(3, ConstantInfo.Type.Fieldref), REF_putStatic(4, ConstantInfo.Type.Fieldref),
        REF_invokeVirtual(5, ConstantInfo.Type.Methodref),
        REF_invokeStatic(6, ConstantInfo.Type.Methodref),
        REF_invokeSpecial(7, ConstantInfo.Type.Methodref),
        REF_newInvokeSpecial(8, ConstantInfo.Type.Methodref),
        REF_invokeInterface(9, ConstantInfo.Type.InterfaceMethodref);

        int kind;
        ConstantInfo.Type type;

        Kind(int kind, ConstantInfo.Type type){
            this.kind = kind;
            this.type = type;
        }

        public ConstantInfo.Type getType(){
            return type;
        }

        public int getKind(){
            return kind;
        }
    };

    int referenceKind;
    int referenceIndex;
    Kind kind;
    ConstantInfo info;

    public ConstantMethodHandleInfo() {
        super(ConstantInfo.Type.MethodHandle);
    }

    public int getReferenceIndex(){
        return referenceIndex;
    }

    public int getReferenceKind(){
        return referenceKind;
    }

    public Kind getKind(){
        return kind;
    }

    public ConstantInfo getReferenceValue(){
        return info;
    }

    @Override
    public void resolve(ConstantPool pool) {
        for(Kind kind: Kind.values()){
            if(kind.getKind() == referenceKind){
                this.kind = kind;
                break;
            }
        }

        ConstantInfo info = pool.get(getReferenceIndex());
        if(info != null && info.getType() != kind.getType()){
            info = null;
        }
        if(info != null){
            info.resolve(pool);
        }

        if(kind == Kind.REF_invokeVirtual || kind == Kind.REF_invokeStatic ||
                kind == Kind.REF_invokeSpecial || kind == Kind.REF_invokeInterface){
            String name = ((ConstantRefInfo)info).getNameAndTypeInfo().getNameValue();
            // this kind does not accept a call of constructor and static initializer. 
            if(name.equals("<clinit>") || name.equals("<init>")){
                info = null;
            }
        }
        else if(kind == Kind.REF_newInvokeSpecial){
            String name = ((ConstantRefInfo)info).getNameAndTypeInfo().getNameValue();
            // this kind is only accept a call of constructor. 
            if(!name.equals("<init>")){
                info = null;
            }
        }
    }
}
