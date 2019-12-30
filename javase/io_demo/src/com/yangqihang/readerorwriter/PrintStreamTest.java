package com.yangqihang.readerorwriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamTest {
    public static void main(String[] args) {
        PrintStream printStream = null;
        try {
            printStream = new PrintStream(System.out);
            printStream.write("hello mashibing".getBytes());
            printStream.println(true);
            //格式化输出 %s:字符串  %d:整数  %f:浮点数
            System.out.printf("%s--%d---%.2f", "abc", 123, 11.111);
            System.err.println("mashibing");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printStream.close();
        }
    }
}
