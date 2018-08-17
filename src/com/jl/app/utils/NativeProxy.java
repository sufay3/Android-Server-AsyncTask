package com.s.demo2.utils;

public class NativeProxy {
    public static native String genCryptValue(String source);

    static
    {
        System.loadLibrary("crypt");
    }
}
