package com.github.siphon.util;

import java.io.PrintWriter;

import com.github.siphon.ConstantInfo;
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
        System.out.printf("class access: %s%n", Integer.toHexString(access));
        out.print(AccessFlags.toStringClass(access));
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

        private String format(ConstantInfo info, String value){
            String className = info.getClass().getName();
            int index = className.lastIndexOf("Constant");
            className = className.substring(index + "Constant".length(), className.length() - 4);
            return String.format("#%3d %-11s %s", info.getIndex(), className, value);
        }

        private String format(ConstantInfo info, String className, String value){
            return String.format("#%3d %-11s %s", info.getIndex(), className, value);
        }

        @Override
        public void visitUtf8Info(ConstantUtf8Info info) {
            out.println(format(info, info.getValue()));
        }

        @Override
        public void visitStringInfo(ConstantStringInfo info) {
            out.println(format(info, String.format("#%3d // -> %s", info.getStringIndex(), info.getStringValue())));
        }

        @Override
        public void visitIntegerInfo(ConstantNumberInfo info) {
            out.println(format(info, info.getValue().toString()));
        }

        @Override
        public void visitFloatInfo(ConstantNumberInfo info) {
            out.println(format(info, info.getValue().toString()));
        }

        @Override
        public void visitLongInfo(ConstantNumberInfo info) {
            out.println(format(info, info.getValue().toString()));
        }

        @Override
        public void visitDoubleInfo(ConstantNumberInfo info) {
            out.println(format(info, info.getValue().toString()));
        }

        @Override
        public void visitClassInfo(ConstantClassInfo info) {
            out.println(format(info, String.format("name: #%3d // -> %s", info.getNameIndex(), info.getNameValue())));
        }

        @Override
        public void visitFieldrefInfo(ConstantRefInfo info) {
            int classIndex = info.getClassIndex();
            int nameIndex = info.getNameAndTypeIndex();

            String className = info.getClassInfo().getNameValue();
            String nameValue = info.getNameAndTypeInfo().getNameValue();
            String descValue = info.getNameAndTypeInfo().getDescriptorValue();

            String infoString = String.format("%s.%s %s", className, nameValue, descValue);

            out.println(format(info, "Fieldref",
                    String.format("class: #%3d, name and type: %3d // -> %s", classIndex, nameIndex, infoString)));
        }

        @Override
        public void visitMethodrefInfo(ConstantRefInfo info) {
            int classIndex = info.getClassIndex();
            int nameIndex = info.getNameAndTypeIndex();

            String className = info.getClassInfo().getNameValue();
            String nameValue = info.getNameAndTypeInfo().getNameValue();
            String descValue = info.getNameAndTypeInfo().getDescriptorValue();

            String infoString = String.format("%s.%s %s", className, nameValue, descValue);

            out.println(format(info, "Methodref",
                    String.format("class: #%3d, name and type: %3d // -> %s", classIndex, nameIndex, infoString)));
        }

        @Override
        public void visitInterfaceMethodrefInfo(ConstantRefInfo info) {
            int classIndex = info.getClassIndex();
            int nameIndex = info.getNameAndTypeIndex();
            
            String className = info.getClassInfo().getNameValue();
            String nameValue = info.getNameAndTypeInfo().getNameValue();
            String descValue = info.getNameAndTypeInfo().getDescriptorValue();

            String infoString = String.format("%s.%s %s", className, nameValue, descValue);

            out.println(format(info, "InterfaceMethodRef",
                    String.format("class: #%3d, name and type: %3d // -> %s", classIndex, nameIndex, infoString)));
        }

        @Override
        public void visitNameAndTypeInfo(ConstantNameAndTypeInfo info) {
            int nameIndex = info.getNameIndex();
            int descIndex = info.getDescriptorIndex();
            
            out.println(format(info, String.format("name: #%3d, desc: %3d", nameIndex, descIndex)));
        }

        @Override
        public void visitMethodHandleInfo(ConstantMethodHandleInfo info) {
        }

        @Override
        public void visitMethodTypeInfo(ConstantMethodTypeInfo info) {
        }

        @Override
        public void visitInvokeDynamicInfo(ConstantInvokeDynamicInfo info) {
        }
    }
}
