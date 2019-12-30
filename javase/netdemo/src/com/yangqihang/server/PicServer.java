package com.yangqihang.server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PicServer {
    public static void main(String[] args) throws IOException {
        //创建服务端对象,开放端口
        ServerSocket serverSocket = new ServerSocket(10086);
        //创建服务端Socket
        Socket server = serverSocket.accept();
        //获取输入流对象
        InputStream inputStream = server.getInputStream();
        //创建文件输出流对象
        FileOutputStream fileOutputStream = new FileOutputStream("2.jpg");
        int temp = 0;
        while ((temp = inputStream.read()) != -1) {
            fileOutputStream.write(temp);
        }
        server.shutdownInput();

        //上传图片结束之后给予客户端响应
        OutputStream outputStream = server.getOutputStream();
        outputStream.write("服务端已收到".getBytes());
        server.shutdownOutput();

        //关闭操作
        outputStream.close();
        fileOutputStream.close();
        inputStream.close();
        server.close();
        serverSocket.close();
    }
}
