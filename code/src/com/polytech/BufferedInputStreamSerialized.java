package com.polytech;
import java.io.*;

public class BufferedInputStreamSerialized extends FakeBufferClass implements java.io.Serializable {
    public BufferedInputStreamSerialized(InputStream in) {
        super(in);
    }
    public BufferedInputStreamSerialized(InputStream in, int size)
    {
        super(in, size);
    }
}
