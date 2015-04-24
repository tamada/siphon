package com.github.siphon.util;

import java.io.PrintWriter;

import com.github.siphon.AccessFlags;
import com.github.siphon.ConstantInfo;
import com.github.siphon.Descriptor;
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

public class ClassFilePrinter extends ClassFileVisitor{
    private PrintWriter out;

    public ClassFilePrinter(){
        this(new PrintWriter(System.out));
    }

    public ClassFilePrinter(PrintWriter out){
        this.out = out;
    }

    @Override
    public void visitHeader(int magic, int minor, int major) {
        out.println("magic: " + Integer.toHexString(magic));
        out.println("minor: " + minor);
        out.println("major: " + major);
    }

    @Override
    public ConstantPoolVisitor visitConstantPool() {
        return new ConstantPoolPrinter(out);
    }

    @Override
    public void visitBody(int access, String className, String superName) {
        out.println(AccessFlags.toStringClass(access));
        out.println(" " + className + " extends " + superName);
    }

    private boolean firstInterface = true;
    @Override
    public void visitInterface(String interfaceName) {
        if(firstInterface){
            out.print("          implements ");
            firstInterface = false;
        }
        else{
            out.println(",");
            out.print("                     ");
        }
        out.print(interfaceName);
    }

    public void visitEndInterface(){
        out.println(" {");
    }

    @Override
    public FieldInfoVisitor visitFieldInfo(int access, String name, String descriptor) {
        out.print("    ");
        out.print(AccessFlags.toStringField(access));
        out.print(" ");
        Descriptor desc = new Descriptor.Field(descriptor);
        out.print(desc.format(name));
        out.println(";");

        return super.visitFieldInfo(access, name, descriptor);
    }

    @Override
    public MethodInfoVisitor visitMethodInfo(int access, String name, String descriptor) {
        out.print("    ");
        out.print(AccessFlags.toStringMethod(access));
        out.print(" ");
        Descriptor desc = new Descriptor.Method(descriptor);
        out.print(desc.format(name));
        out.println();

        return super.visitMethodInfo(access, name, descriptor);
    }

    @Override
    public AttributeInfoVisitor visitAttributeInfo(String name) {
        return super.visitAttributeInfo(name);
    }

    @Override
    public void visitEnd(){
        out.println("}");
        out.flush();
    }

    private static class ConstantPoolPrinter extends ConstantPoolVisitor{
        private PrintWriter out;

        public ConstantPoolPrinter(PrintWriter out){
            this.out = out;
        }

        private String format(int index, ConstantInfo info, String value){
            return String.format("#%3d %s %s", index, info.getClass().getName(), value);
        }

        @Override
        public void visitUtf8Info(int index, ConstantUtf8Info info) {
            out.println(format(index, info, info.getValue()));
        }

        @Override
        public void visitStringInfo(int index, ConstantStringInfo info) {
            out.println(format(index, info, String.format("#%3d // -> %s", info.getStringIndex(), info.getStringValue())));
        }

        @Override
        public void visitIntegerInfo(int index, ConstantNumberInfo info) {
            out.println(format(index, info, info.getValue().toString()));
        }

        @Override
        public void visitFloatInfo(int index, ConstantNumberInfo info) {
            out.println(format(index, info, info.getValue().toString()));
        }

        @Override
        public void visitLongInfo(int index, ConstantNumberInfo info) {
            out.println(format(index, info, info.getValue().toString()));
        }

        @Override
        public void visitDoubleInfo(int index, ConstantNumberInfo info) {
            out.println(format(index, info, info.getValue().toString()));
        }

        @Override
        public void visitClassInfo(int index, ConstantClassInfo info) {
            out.println(format(index, info, String.format("name: #%3d // -> %s", info.getNameIndex(), info.getNameValue())));
        }

        @Override
        public void visitFieldrefInfo(int index, ConstantRefInfo info) {
            int classIndex = info.getClassIndex();
            int nameIndex = info.getNameAndTypeIndex();
            
            out.println(format(index, info, String.format("class: #%3d, name and type: %3d // -> %s", classIndex, nameIndex, info)));
        }

        @Override
        public void visitMethodrefInfo(int index, ConstantRefInfo info) {
            int classIndex = info.getClassIndex();
            int nameIndex = info.getNameAndTypeIndex();
            
            out.println(format(index, info, String.format("class: #%3d, name and type: %3d // -> %s", classIndex, nameIndex, info)));
        }

        @Override
        public void visitInterfaceMethodrefInfo(int index, ConstantRefInfo info) {
            int classIndex = info.getClassIndex();
            int nameIndex = info.getNameAndTypeIndex();
            
            out.println(format(index, info, String.format("class: #%3d, name and type: %3d // -> %s", classIndex, nameIndex, info)));
        }

        @Override
        public void visitNameAndTypeInfo(int index, ConstantNameAndTypeInfo info) {
            int nameIndex = info.getNameIndex();
            int descIndex = info.getDescriptorIndex();
            
            out.println(format(index, info, String.format("name: #%3d, desc: %3d // -> %s", nameIndex, descIndex, info)));
        }

        @Override
        public void visitMethodHandleInfo(int index, ConstantMethodHandleInfo info) {
        }

        @Override
        public void visitMethodTypeInfo(int index, ConstantMethodTypeInfo info) {
        }

        @Override
        public void visitInvokeDynamicInfo(int index, ConstantInvokeDynamicInfo info) {
        }
    }
}
