package com.yangqihang.readerorwriter;

import java.io.*;

public class CopyPicture {
    public static void main(String[] args) {
//        FileReader reader = null;
//        FileWriter writer = null;
//        try {
//            reader = new FileReader("1.jpg");
//            writer = new FileWriter("2.jpg");
//
//            int length = 0;
//            char[] chars = new char[1024];
//            while ((length = reader.read(chars)) != -1) {
//                writer.write(chars);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                writer.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream("1.jpg");
            fileOutputStream = new FileOutputStream("2.jpg");

            int length = 0;
            byte[] bytes = new byte[1024];
            while ((length = fileInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
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
