package com.github.siphon.visitor;

import com.github.siphon.constants.ConstantClassInfo;
import com.github.siphon.constants.ConstantInvokeDynamicInfo;
import com.github.siphon.constants.ConstantMethodHandleInfo;
import com.github.siphon.constants.ConstantMethodTypeInfo;
import com.github.siphon.constants.ConstantNameAndTypeInfo;
import com.github.siphon.constants.ConstantNumberInfo;
import com.github.siphon.constants.ConstantRefInfo;
import com.github.siphon.constants.ConstantStringInfo;
import com.github.siphon.constants.ConstantUtf8Info;

public class ConstantPoolVisitor {
    private ConstantPoolVisitor visitor;

    public ConstantPoolVisitor(ConstantPoolVisitor visitor){
        this.visitor = visitor;
    }

    public ConstantPoolVisitor() {
    }

    public void visitEnd(){
        if(visitor != null){
            visitor.visitEnd();
        }
    }

    public void visitUtf8Info(int index, ConstantUtf8Info info){
        if(visitor != null){
            visitor.visitUtf8Info(index, info);
        }
    }

    public void visitStringInfo(int index, ConstantStringInfo info){
        if(visitor != null){
            visitor.visitStringInfo(index, info);
        }
    }

    public void visitIntegerInfo(int index, ConstantNumberInfo info){
        if(visitor != null){
            visitor.visitIntegerInfo(index, info);
        }
    }

    public void visitFloatInfo(int index, ConstantNumberInfo info){
        if(visitor != null){
            visitor.visitFloatInfo(index, info);
        }
    }
    
    public void visitLongInfo(int index, ConstantNumberInfo info){
        if(visitor != null){
            visitor.visitLongInfo(index, info);
        }
    }

    public void visitDoubleInfo(int index, ConstantNumberInfo info){
        if(visitor != null){
            visitor.visitDoubleInfo(index, info);
        }
    }

    public void visitClassInfo(int index, ConstantClassInfo info){
        if(visitor != null){
            visitor.visitClassInfo(index, info);
        }
    }

    public void visitFieldrefInfo(int index, ConstantRefInfo info){
        if(visitor != null){
            visitor.visitFieldrefInfo(index, info);
        }
    }

    public void visitMethodrefInfo(int index, ConstantRefInfo info){
        if(visitor != null){
            visitor.visitMethodrefInfo(index, info);
        }
    }

    public void visitInterfaceMethodrefInfo(int index, ConstantRefInfo info){
        if(visitor != null){
            visitor.visitInterfaceMethodrefInfo(index, info);
        }
    }

    public void visitNameAndTypeInfo(int index, ConstantNameAndTypeInfo info){
        if(visitor != null){
            visitor.visitNameAndTypeInfo(index, info);
        }
    }

    public void visitMethodHandleInfo(int index, ConstantMethodHandleInfo info){
        if(visitor != null){
            visitor.visitMethodHandleInfo(index, info);
        }
    }

    public void visitMethodTypeInfo(int index, ConstantMethodTypeInfo info){
        if(visitor != null){
            visitor.visitMethodTypeInfo(index, info);
        }
    }

    public void visitInvokeDynamicInfo(int index, ConstantInvokeDynamicInfo info){
        if(visitor != null){
            visitor.visitInvokeDynamicInfo(index, info);
        }
    }
}
