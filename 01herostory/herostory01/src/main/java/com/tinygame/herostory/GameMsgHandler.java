package com.tinygame.herostory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

public class GameMsgHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到的客户端信息为 msg = " + msg);

        BinaryWebSocketFrame frame = (BinaryWebSocketFrame) msg;
        ByteBuf byteBuf = frame.content(); //frame的类型转换为ByteBuf类型
        byte[] byteArray = new byte[byteBuf.readableBytes()];//读取消息内容

        byteBuf.readBytes(byteArray);//移动指针

        System.out.print("收到的字节 = ");

        for (byte b : byteArray) {
            System.out.print(b);
            System.out.print(" ,");
        }

        System.out.println();
    }
}
