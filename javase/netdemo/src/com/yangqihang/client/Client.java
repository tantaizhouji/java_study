package com.yangqihang.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/*
* 客户端向服务端发送数据
* */

public class Client {
    public static void main(String[] args) throws IOException {

        //创建Socket对象,其实是开始实行IO的虚拟接口(此接口不是Java中的接口,而是类似于网线中的插槽)
        //需要指定数据接收方的IP地址和端口号
        Socket client = new Socket("localHost", 10086);
        //获取输出流对象,向服务端发送数据
        OutputStream outputStream = client.getOutputStream();
        //将输出流进行包装
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        //传输数据
        dataOutputStream.writeUTF("hello,你好");
        //关闭流操作
        dataOutputStream.close();
        outputStream.close();
        client.close();
    }
}
