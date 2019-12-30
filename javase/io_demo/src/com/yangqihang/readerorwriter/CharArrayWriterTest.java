package com.yangqihang.readerorwriter;

import java.io.CharArrayWriter;
import java.io.IOException;

public class CharArrayWriterTest {
    public static void main(String[] args) {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        charArrayWriter.write(97);
        charArrayWriter.write(98);
        charArrayWriter.write(99);
        charArrayWriter.write(100);
        System.out.println(charArrayWriter);
        charArrayWriter.close();
    }
}
