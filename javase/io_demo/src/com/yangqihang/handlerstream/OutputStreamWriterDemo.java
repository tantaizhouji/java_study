package com.yangqihang.handlerstream;

import java.io.*;

public class OutputStreamWriterDemo {
    public static void main(String[] args) {
        File file = new File("abc.txt");
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(99);
            outputStreamWriter.write("马士兵");
            outputStreamWriter.write("abcdefg",0,5);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
