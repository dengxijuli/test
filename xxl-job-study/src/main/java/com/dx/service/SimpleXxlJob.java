package com.dx.service;

import com.dx.Mapper.UserMobilePlanMapper;
import com.dx.entity.UserMobilePlan;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class SimpleXxlJob {

    @Autowired
    private UserMobilePlanMapper userMobilePlanMapper;


    /**
     * 首次测试
     */

    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception {
        System.out.println("执行定时任务,执行时间:" + new Date());
    }


    @XxlJob("sendMsgHandler")
    public void sendMsgHandler() throws Exception {
        List<UserMobilePlan> userMobilePlans = userMobilePlanMapper.selectAll();
        System.out.println("任务开始时间:" + new Date() + ",处理任务数量:" + userMobilePlans.size());
        Long startTime = System.currentTimeMillis();
        userMobilePlans.forEach(item -> {
            try {
                //模拟发送短信动作
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("任务结束时间:" + new Date());
        System.out.println("任务耗时:" + (System.currentTimeMillis() - startTime) + "毫秒");
    }

}