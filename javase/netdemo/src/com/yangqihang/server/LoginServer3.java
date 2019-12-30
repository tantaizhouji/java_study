package com.yangqihang.server;

import com.yangqihang.client.User;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginServer3 {
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(10000);
        while (true) {
            Socket socket = serverSocket.accept();
            LoginThread loginThread = new LoginThread(socket);
            new Thread(loginThread).start();
        }
    }
}
