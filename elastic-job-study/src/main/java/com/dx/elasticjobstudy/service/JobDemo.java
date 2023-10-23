//package com.dx.elasticjobstudy.service;
//
//import org.apache.shardingsphere.elasticjob.lite.internal.schedule.JobScheduler;
//import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
//import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
//import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
//
//public class JobDemo {
//    public static void main(String[] args) {
//        new JobScheduler(createRegistryCenter(), createJobConfiguration()).init();
//    }
//
//    private static CoordinatorRegistryCenter createRegistryCenter() {
//        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("zk_host:2181", "elastic-job-demo"));
//        regCenter.init();
//        return regCenter;
//    }
//    private static LiteJobConfiguration createJobConfiguration() {
//        // 创建作业配置
//        // 定义作业核心配置
//        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder("demoSimpleJob", "0/15 * * * * ?", 10).build();
//        // 定义SIMPLE类型配置
//        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, SimpleDemoJob.class.getCanonicalName());
//        // 定义Lite作业根配置
//        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).build();
//        return simpleJobRootConfig;
//    }
//}
