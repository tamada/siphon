package com.github.siphon;

import com.github.siphon.constants.ConstantClassInfo;
import com.github.siphon.constants.ConstantInvokeDynamicInfo;
import com.github.siphon.constants.ConstantMethodHandleInfo;
import com.github.siphon.constants.ConstantMethodTypeInfo;
import com.github.siphon.constants.ConstantNameAndTypeInfo;
import com.github.siphon.constants.ConstantNumberInfo;
import com.github.siphon.constants.ConstantRefInfo;
import com.github.siphon.constants.ConstantStringInfo;
import com.github.siphon.constants.ConstantUtf8Info;
import com.github.siphon.visitor.AttributeInfoVisitor;
import com.github.siphon.visitor.ClassFileVisitor;
import com.github.siphon.visitor.ConstantPoolVisitor;
import com.github.siphon.visitor.FieldInfoVisitor;
import com.github.siphon.visitor.MethodInfoVisitor;

public class ClassFileAcceptor {
    private static final String NOT_CLASS_INFO = "<not class info: %d>";

    private ClassFile cf;

    public ClassFileAcceptor(ClassFile cf) {
        this.cf = cf;
    }

    public void accept(ClassFileVisitor visitor) {
        visitor.visitHeader(cf.magic, cf.minor, cf.major);
        ConstantPool pool = cf.getConstantPool();

        ConstantPoolVisitor cpVisitor = visitor.visitConstantPool();
        for(ConstantInfo info: pool){
            if(info != null){
                info.resolve(pool);
                acceptConstantInfo(cpVisitor, info);
            }
        }
        cpVisitor.visitEnd();

        String thisClassName = getClassName(cf.thisClass, pool);
        String superClassName = getClassName(cf.superClass, pool);

        visitor.visitBody(cf.accessFlag, thisClassName, superClassName);

        for(int interfaceIndex: cf.interfaces){
            String interfaceName = getClassName(interfaceIndex, pool);
            visitor.visitInterface(interfaceName);
        }
        visitor.visitEndInterface();

        for(FieldInfo info: cf.fields){
            String name = pool.getUtf8(info.nameIndex);
            String descriptor = pool.getUtf8(info.descriptorIndex);
            FieldInfoVisitor fiVisitor = visitor.visitFieldInfo(info.accessFlags, name, descriptor);
            acceptFieldInfo(fiVisitor);
        }
        for(MethodInfo info: cf.methods){
            String name = pool.getUtf8(info.nameIndex);
            String descriptor = pool.getUtf8(info.descriptorIndex);
            MethodInfoVisitor miVisitor = visitor.visitMethodInfo(info.accessFlags, name, descriptor);
            acceptMethodInfo(miVisitor);
        }
        for(AttributeInfo info: cf.attributes){
            String name = pool.getUtf8(info.attributeNameIndex);
            AttributeInfoVisitor aiVisitor = visitor.visitAttributeInfo(name);
            acceptAttributeInfo(aiVisitor);
        }
        visitor.visitEnd();
    }

    private String getClassName(int index, ConstantPool pool){
        ConstantClassInfo ccInfo = pool.getClassInfo(index);
        if(ccInfo != null){
            ccInfo.getNameValue();
        }
        return String.format(NOT_CLASS_INFO, index);
    }

    private void acceptAttributeInfo(AttributeInfoVisitor visitor){
        
    }

    private void acceptMethodInfo(MethodInfoVisitor visitor){
        
    }

    private void acceptFieldInfo(FieldInfoVisitor visitor){
        
    }

    private void acceptConstantInfo(ConstantPoolVisitor visitor, ConstantInfo info){
        int index = 1;
        switch(info.getType()){
        case Utf8:
            visitor.visitUtf8Info(index, (ConstantUtf8Info)info);
            break;
        case Class:
            visitor.visitClassInfo(index, (ConstantClassInfo)info);
            break;
        case Double:
            visitor.visitDoubleInfo(index, (ConstantNumberInfo)info);
            index++;
            break;
        case Fieldref:
            visitor.visitFieldrefInfo(index, (ConstantRefInfo)info);
            break;
        case Float:
            visitor.visitFloatInfo(index, (ConstantNumberInfo)info);
            break;
        case Integer:
            visitor.visitIntegerInfo(index, (ConstantNumberInfo)info);
            break;
        case InterfaceMethodref:
            visitor.visitInterfaceMethodrefInfo(index, (ConstantRefInfo)info);
            break;
        case InvokeDynamic:
            visitor.visitInvokeDynamicInfo(index, (ConstantInvokeDynamicInfo)info);
            break;
        case Long:
            visitor.visitLongInfo(index, (ConstantNumberInfo)info);
            index++;
            break;
        case MethodHandle:
            visitor.visitMethodHandleInfo(index, (ConstantMethodHandleInfo)info);
            break;
        case Methodref:
            visitor.visitMethodrefInfo(index, (ConstantRefInfo)info);
            break;
        case MethodType:
            visitor.visitMethodTypeInfo(index, (ConstantMethodTypeInfo)info);
            break;
        case NameAndType:
            visitor.visitNameAndTypeInfo(index, (ConstantNameAndTypeInfo)info);
            break;
        case String:
            visitor.visitStringInfo(index, (ConstantStringInfo)info);
            break;
        default:
        }
    }
}
