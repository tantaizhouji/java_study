package com.yangqihang.handlerstream;

import java.io.*;

public class InputStreamReaderDemo {
    public static void main(String[] args) {
        File file = new File("abc.txt");
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        try {
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream);

            char[] chars = new char[1024];
            int length = inputStreamReader.read(chars);
            System.out.println(new String(chars, 0, length));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
