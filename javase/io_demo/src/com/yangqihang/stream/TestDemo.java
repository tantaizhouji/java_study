package com.yangqihang.stream;

import java.io.*;

public class TestDemo {

    public static void copy(String str1, String str2) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        File src = new File(str1);
        File dest = new File(str2);
        try {
            inputStream = new FileInputStream(src);
            outputStream = new FileOutputStream(dest);
            int length = 0;
            byte[] buffer = new byte[1024];
            while ((length = inputStream.read(buffer)) != -1) {
                String str = new String(buffer,0,length);
                outputStream.write(str.getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String str1 = "abc.txt";
        String str2 = "aaa.txt";
        copy(str1, str2);
    }
}
