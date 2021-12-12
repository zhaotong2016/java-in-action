package com.hunter.demo.config;

import com.hunter.demo.service.OrderService;
import com.hunter.demo.service.RemoteService;
import org.springframework.context.annotation.Bean;

/**
 * @author Hunter
 * @date 2021/12/12 00:09
 **/
//@Configuration
public class Config {

    @Bean
    public OrderService orderService(){
        return new OrderService();
    }

    @Bean
    public RemoteService remoteService() {
        return new RemoteService();
    }
}
