package com.hunter.demo.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;
@EnableAspectJAutoProxy
@Configuration
public class AutoConfig {

    /**
     * //负载均衡，默认使用轮询路由策略
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
