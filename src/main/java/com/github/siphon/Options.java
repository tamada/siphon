package com.github.siphon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Options implements Iterable<String>, Serializable{
    private static final long serialVersionUID = -387850128428595889L;

    private List<String> args;

    public Options(){
        args = new ArrayList<>();
    }

    public static Options parse(String[] args){
        Options options = new Options();

        for(String arg: args){
            options.args.add(arg);
        }
        return options;
    }

    public Iterator<String> iterator() {
        return args.iterator();
    }
}
