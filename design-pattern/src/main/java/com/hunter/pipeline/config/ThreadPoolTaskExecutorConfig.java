package com.hunter.pipeline.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolTaskExecutorConfig {

    @Bean
    public ThreadPoolTaskExecutor pipelineThreadPool(){
        Thread
        ThreadPoolTaskExecutor threadPoolTaskExecutor  = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(4);// 核心线程数
        threadPoolTaskExecutor.setMaxPoolSize(8); //最大线程数
        threadPoolTaskExecutor.setKeepAliveSeconds(960); //线程最大空闲时间/秒（根据管道使用情况指定）
        threadPoolTaskExecutor.setQueueCapacity(256); //任务队列大小（根据管道使用情况指定）
        threadPoolTaskExecutor.setThreadNamePrefix("pipelineThreadPool");
        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        threadPoolTaskExecutor.setRejectedExecutionHandler(callerRunsPolicy);
        return threadPoolTaskExecutor;
    }
}
