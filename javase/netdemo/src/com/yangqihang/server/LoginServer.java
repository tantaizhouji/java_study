package com.yangqihang.server;

import com.yangqihang.client.User;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginServer {
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(10000);
        Socket server = serverSocket.accept();
        //获取输入流对象
        InputStream inputStream = server.getInputStream();
        //需要使用ObjectInputStream
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        User user = (User) objectInputStream.readObject();
        String str = "";
        if (user.getUsername().equals("msb") && user.getPassword().equals("msb")) {
            str = "登录成功";
        } else {
            str = "登录失败";
        }
        //截断输入流
        server.shutdownInput();

        //给客户端响应
        DataOutputStream outputStream = new DataOutputStream(server.getOutputStream());
        outputStream.writeUTF(str);

        outputStream.close();
        objectInputStream.close();
        inputStream.close();
        server.close();
        serverSocket.close();
    }
}
