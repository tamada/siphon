package com.github.siphon;

public class Main {
    private Siphon siphon;

    public Main(String[] args){
        Options options = parseOptions(args);

        siphon = new Siphon(options);

        siphon.perform();
    }

    public Options parseOptions(String[] args){
        return Options.parse(args);
    }

    public static void main(String[] args){
        new Main(args);
    }
}
