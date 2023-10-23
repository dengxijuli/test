package com.dx.elasticjobstudy.service;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyJob implements SimpleJob {

    @Override
    public void execute(ShardingContext context) {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//        switch (context.getShardingItem()) {
//            case 0:
//                // do something by sharding item 0
//                System.out.println("这是item0");
//                break;
//            case 1:
//                // do something by sharding item 1
//                System.out.println("这是item1");
//                break;
//            case 2:
//                // do something by sharding item 2
//                System.out.println("这是item2");
//                break;
//            // case n: ...
//        }
    }
}
