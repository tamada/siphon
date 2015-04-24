package com.github.siphon.visitor;

/**
 * visitHeader
 * visitConstantPool
 * visitBody
 * visitInterface *
 * visitField *
 * visitMethod *
 * visitAttributeInfo *
 * 
 * @author Haruaki Tamada
 */
public class ClassFileVisitor {
    private ClassFileVisitor visitor;

    public ClassFileVisitor(ClassFileVisitor visitor){
        this.visitor = visitor;
    }

    public ClassFileVisitor(){
    }

    public void visitHeader(int magic, int minor, int major){
        if(visitor != null){
            visitor.visitHeader(magic, minor, major);
        }
    }

    public ConstantPoolVisitor visitConstantPool(){
        ConstantPoolVisitor cpVisitor;
        if(visitor != null){
            cpVisitor = visitor.visitConstantPool();
        }
        else{
            cpVisitor = new ConstantPoolVisitor();
        }
        return cpVisitor;
    }

    public void visitBody(int access, String className, String superName){
        if(visitor != null){
            visitor.visitBody(access, className, superName);
        }
    }

    public void visitInterface(String interfaceName){
        if(visitor != null){
            visitor.visitInterface(interfaceName);
        }
    }

    public void visitEndInterface(){
        if(visitor != null){
            visitor.visitEndInterface();
        }
    }

    public FieldInfoVisitor visitFieldInfo(int access, String name, String descriptor){
        FieldInfoVisitor fiVisitor;
        if(visitor != null){
            fiVisitor = visitor.visitFieldInfo(access, name, descriptor);
        }
        else{
            fiVisitor = new FieldInfoVisitor(access, name, descriptor);
        }
        return fiVisitor;
    }

    public MethodInfoVisitor visitMethodInfo(int access, String name, String descriptor){
        MethodInfoVisitor miVisitor;
        if(visitor != null){
            miVisitor = visitor.visitMethodInfo(access, name, descriptor);
        }
        else{
            miVisitor = new MethodInfoVisitor(access, name, descriptor);
        }
        return miVisitor;
    }

    public AttributeInfoVisitor visitAttributeInfo(String name){
        return new AttributeInfoVisitor(name);
    }

    public void visitEnd() {
        if(visitor != null){
            visitor.visitEnd();
        }
    }
}
