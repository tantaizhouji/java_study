package com.tinygame.herostory;

import com.google.protobuf.GeneratedMessageV3;
import com.tinygame.herostory.cmdHandler.CmdHandler;
import com.tinygame.herostory.cmdHandler.CmdHandlerFactory;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 主线程处理器
 */
public final class MainThreadProcessor {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MainThreadProcessor.class);

    /**
     * 单例对象
     */
    private static final MainThreadProcessor _instance = new MainThreadProcessor();

    private final ExecutorService _es = Executors.newSingleThreadExecutor((r) -> {
        Thread newThread = new Thread(r);
        newThread.setName("MainThreadProcessor");
        return newThread;
    });

    /**
     * 私有化类默认构造器
     */
    private MainThreadProcessor() {
    }

    /**
     * 获取单例对象
     *
     * @return 主线程处理器
     */
    public static MainThreadProcessor getInstance() {
        return _instance;
    }

    /**
     * 处理消息
     *
     * @param ctx 客户端信道
     * @param msg 消息对象
     */
    public void process(ChannelHandlerContext ctx, GeneratedMessageV3 msg) {
        if (null == ctx || null == msg) {
            return;
        }

        this._es.submit(() -> {

            //获取消息类
            Class<?> msgClazz = msg.getClass();

            LOGGER.info("收到客户端消息,msgClazz = " + msgClazz.getName() + ", msg = " + msg);

            //获取指令处理器
            CmdHandler<? extends GeneratedMessageV3> cmdHandler = CmdHandlerFactory.create(msgClazz);

            if (null == cmdHandler) {
                LOGGER.error("未找到相对应的指令处理器, msgClazz = ", msgClazz.getName());
                return;
            }

            try {
                //处理指令
                cmdHandler.handle(ctx, cast(msg));
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        });
    }

    /**
     * 处理消息
     *
     * @param r Runnable实例
     */
    public void process(Runnable r) {
        if (null != r) {
            _es.submit(r);
        }
    }

    /**
     * 转型消息对象
     *
     * @param msg
     * @param <TCmd>
     * @return
     */
    private static <TCmd extends GeneratedMessageV3> TCmd cast(Object msg) {
        if (null == msg) {
            return null;
        } else {
            return (TCmd) msg;
        }
    }
}
