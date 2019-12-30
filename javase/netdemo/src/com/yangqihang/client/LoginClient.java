package com.yangqihang.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class LoginClient {
    public static void main(String[] args) throws IOException {

        Socket client = new Socket("localHost", 10000);
        OutputStream outputStream = client.getOutputStream();

        //完成登录功能,需要传输一个User对象
        User user = getUser();
        //传输对象需要ObjectOutputStream
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(user);
        //调用shutdown方法告诉对方传输完成
        client.shutdownOutput();

        DataInputStream inputStream = new DataInputStream(client.getInputStream());
        String str = inputStream.readUTF();
        System.out.println(str);
        client.shutdownInput();

        inputStream.close();
        objectOutputStream.close();
        outputStream.close();
        client.close();
    }

    public static User getUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入用户名: ");
        String username = scanner.nextLine();
        System.out.print("请输入密码: ");
        String password = scanner.nextLine();
        return new User(username, password);
    }
}
