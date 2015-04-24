package com.github.siphon;

import java.io.IOException;

import com.github.siphon.util.ClassFilePrinter;

public class Siphon {
    private Options options;

    public Siphon(Options options){
        this.options = options;
    }

    public void perform(){
        for(String file: options){
            perform(file);
        }
    }

    public void perform(String file){
        try {
            ClassFile cf = ClassFileBuilder.getInstance().build(file);
            ClassFileAcceptor acceptor = new ClassFileAcceptor(cf);
            acceptor.accept(new ClassFilePrinter());
        } catch (IOException | SiphonException e) {
            e.printStackTrace();
        }
    }
}
