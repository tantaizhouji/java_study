package com.tinygame.herostory.mq;

import com.alibaba.fastjson.JSONObject;
import com.tinygame.herostory.rank.RankService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 消息队列消费者
 */
public final class MQConsumer {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MQConsumer.class);

    /**
     * 私有化类默认构造器
     */
    private MQConsumer() {
    }

    /**
     * 初始化
     */
    public static void init() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("herostory");
        consumer.setNamesrvAddr("192.168.74.111:9876");

        try {
            consumer.subscribe("Victor", "*");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgExtList, ConsumeConcurrentlyContext ctx) {
                    for (MessageExt msgExt : msgExtList) {
                        //解析战斗结果
                        VictorMsg mqMsg = JSONObject.parseObject(msgExt.getBody(), VictorMsg.class);

                        LOGGER.info("从消息队列中收到战斗结果, winnerId = {}, loserId = {}", mqMsg.winnerId, mqMsg.loserId);

                        //刷新排行榜
                        RankService.getInstance().refreshRank(mqMsg.winnerId, mqMsg.loserId);
                    }

                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            consumer.start();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
