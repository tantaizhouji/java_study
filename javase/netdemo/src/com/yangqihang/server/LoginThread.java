package com.yangqihang.server;

import com.yangqihang.client.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class LoginThread implements Runnable {

    private Socket socket;

    public LoginThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        ObjectInputStream objectInputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            User user = (User) objectInputStream.readObject();
            String str = "";
            if (user.getUsername().equals("msb") && user.getPassword().equals("msb")) {
                str = "登录成功";
                System.out.println("欢迎" + user.getUsername());
            } else {
                str = "登录失败";
            }
            socket.shutdownInput();

            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(str);
            socket.shutdownOutput();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
