package com.powernode;

import com.powernode.domain.MsgModel;
import lombok.Setter;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: DLJD
 * @Date: 2023/4/24
 */
@Controller
public class A {

    @Setter(onMethod_ = @Autowired)
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("/test")
    public void send(){
        MsgModel msgModel = new MsgModel();
        msgModel.setDesc("这是一个测试订购");
        msgModel.setUserId(1);
        msgModel.setOrderSn("01");
        Message<MsgModel> msg = MessageBuilder.withPayload(msgModel).build();
        rocketMQTemplate.syncSend("dx_topic", msg);
    }

}
