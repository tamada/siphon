package com.github.siphon.attributes;

import java.io.DataInputStream;
import java.io.IOException;

import com.github.siphon.AttributeInfo;

public interface AttributeInfoFactory {
    AttributeInfo create(DataInputStream in) throws IOException;
}
