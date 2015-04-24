package com.github.siphon;

import java.io.DataInputStream;
import java.io.IOException;

public abstract class ConstantInfoFactory {
    public abstract ConstantInfo create(DataInputStream in) throws IOException;

    public abstract ConstantInfo.Type getType();
}
