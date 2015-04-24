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

    public void visitUtf8Info(ConstantUtf8Info info){
        if(visitor != null){
            visitor.visitUtf8Info(info);
        }
    }

    public void visitStringInfo(ConstantStringInfo info){
        if(visitor != null){
            visitor.visitStringInfo(info);
        }
    }

    public void visitIntegerInfo(ConstantNumberInfo info){
        if(visitor != null){
            visitor.visitIntegerInfo(info);
        }
    }

    public void visitFloatInfo(ConstantNumberInfo info){
        if(visitor != null){
            visitor.visitFloatInfo(info);
        }
    }
    
    public void visitLongInfo(ConstantNumberInfo info){
        if(visitor != null){
            visitor.visitLongInfo(info);
        }
    }

    public void visitDoubleInfo(ConstantNumberInfo info){
        if(visitor != null){
            visitor.visitDoubleInfo(info);
        }
    }

    public void visitClassInfo(ConstantClassInfo info){
        if(visitor != null){
            visitor.visitClassInfo(info);
        }
    }

    public void visitFieldrefInfo(ConstantRefInfo info){
        if(visitor != null){
            visitor.visitFieldrefInfo(info);
        }
    }

    public void visitMethodrefInfo(ConstantRefInfo info){
        if(visitor != null){
            visitor.visitMethodrefInfo(info);
        }
    }

    public void visitInterfaceMethodrefInfo(ConstantRefInfo info){
        if(visitor != null){
            visitor.visitInterfaceMethodrefInfo(info);
        }
    }

    public void visitNameAndTypeInfo(ConstantNameAndTypeInfo info){
        if(visitor != null){
            visitor.visitNameAndTypeInfo(info);
        }
    }

    public void visitMethodHandleInfo(ConstantMethodHandleInfo info){
        if(visitor != null){
            visitor.visitMethodHandleInfo(info);
        }
    }

    public void visitMethodTypeInfo(ConstantMethodTypeInfo info){
        if(visitor != null){
            visitor.visitMethodTypeInfo(info);
        }
    }

    public void visitInvokeDynamicInfo(ConstantInvokeDynamicInfo info){
        if(visitor != null){
            visitor.visitInvokeDynamicInfo(info);
        }
    }
}
