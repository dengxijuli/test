package com.example.rocketmqstudy;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RocketmqStudyApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    public void testProducer() throws Exception {
        // 创建默认的生产者
        DefaultMQProducer producer = new DefaultMQProducer("test-group");
        // 设置nameServer地址
        producer.setNamesrvAddr("192.168.88.128:9876");
        // 启动实例
        producer.start();
        for (int i = 0; i < 10; i++) {
            // 创建消息
            // 第一个参数：主题的名字
            // 第二个参数：消息内容
            Message msg = new Message("TopicTest", ("Hello RocketMQ " + i).getBytes());
            SendResult send = producer.send(msg);
            System.out.println(send);
        }
        // 关闭实例
        producer.shutdown();
    }


    /**
     * 测试消费者
     *
     * @throws Exception
     */
    @Test
    public void testConsumer() throws Exception {
        // 创建默认消费者组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer-group");
        // 设置nameServer地址
        consumer.setNamesrvAddr("localhost:9876");
        // 订阅一个主题来消费   *表示没有过滤参数 表示这个主题的任何消息
        consumer.subscribe("TopicTest", "*");
        // 注册一个消费监听 MessageListenerConcurrently 是多线程消费，默认20个线程，可以参看consumer.setConsumeThreadMax()
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                System.out.println(Thread.currentThread().getName() + "----" + msgs);
                // 返回消费的状态 如果是CONSUME_SUCCESS 则成功，若为RECONSUME_LATER则该条消息会被重回队列，重新被投递
                // 重试的时间为messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
                // 也就是第一次1s 第二次5s 第三次10s  ....  如果重试了18次 那么这个消息就会被终止发送给消费者
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        // 这个start一定要写在registerMessageListener下面
        consumer.start();
        System.in.read();
    }
}
