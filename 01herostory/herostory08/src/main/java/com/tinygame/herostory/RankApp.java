package com.tinygame.herostory;

import com.tinygame.herostory.mq.MQConsumer;
import com.tinygame.herostory.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 排行榜应用程序
 */
public class RankApp {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RankApp.class);

    /**
     * 应用程序入口
     *
     * @param args
     */
    public static void main(String[] args) {
        RedisUtil.init();
        MQConsumer.init();

        LOGGER.info("排行榜应用程序启动成功!");
    }
}
