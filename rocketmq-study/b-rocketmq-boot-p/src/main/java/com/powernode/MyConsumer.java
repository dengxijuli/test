package com.powernode;

import com.alibaba.fastjson.JSON;
import com.powernode.domain.MsgModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "dx_topic", consumerGroup = "your_consumer_group_name")
public class MyConsumer implements RocketMQListener<MsgModel> {

    @Override
    public void onMessage(MsgModel message) {
        // 处理消息的逻辑
        System.out.println("Received message: " +message.toString());

    }
}
