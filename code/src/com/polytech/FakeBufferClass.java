package com.polytech;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class FakeBufferClass extends BufferedInputStream {
    public FakeBufferClass(InputStream in) {
        super(in);
    }

    public FakeBufferClass(InputStream in, int size) {
        super(in, size);
    }
    public FakeBufferClass(){
        super(null);
    };
}
