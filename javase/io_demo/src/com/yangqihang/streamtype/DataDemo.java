package com.yangqihang.streamtype;

import java.io.*;

public class DataDemo {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;

        try {
            //向文件中写入数据流
            fileOutputStream = new FileOutputStream("abc.txt");
            dataOutputStream = new DataOutputStream(fileOutputStream);
            dataOutputStream.writeBoolean(true);
            dataOutputStream.writeInt(123);
            dataOutputStream.writeChar('a');
            dataOutputStream.writeDouble(3.14);
            dataOutputStream.writeUTF("www.mashibing.com马士兵教育");

            //从文件读取数据流
            fileInputStream = new FileInputStream("abc.txt");
            dataInputStream = new DataInputStream(fileInputStream);
            System.out.println(dataInputStream.readBoolean());
            System.out.println(dataInputStream.readInt());
            System.out.println(dataInputStream.readChar());
            System.out.println(dataInputStream.readDouble());
            System.out.println(dataInputStream.readUTF());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileInputStream.close();
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
