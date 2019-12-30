package com.tinygame.herostory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerMain.class);

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup);
        b.channel(NioServerSocketChannel.class);
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(
                        new HttpServerCodec(), //Http服务器编码
                        new HttpObjectAggregator(65535), //内容长度限制
                        new WebSocketServerProtocolHandler("/websocket"), //WebSocket协议处理器,在这里处理握手,ping、pong等消息
                        new GameMsgDecoder(), //自定义的消息解码器
                        new GameMsgEncoder(), //自定义的消息编码器
                        new GameMsgHandler() //自定义的消息处理器
                );
            }
        });

        try {
            //绑定12345端口
            //注意:实际项目中会使用argArray中的参数来指定端口号
            ChannelFuture f = b.bind(12345).sync();

            if (f.isSuccess()) {
                LOGGER.info("服务器启动成功!");
            }

            //等待服务器信道关闭
            //也就是不要退出应用程序
            //让应用程序可以一直提供服务
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
