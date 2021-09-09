package com.hunter.demo.autoconfigure;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnClass(Hunter.class)
@EnableConfigurationProperties(HunterProperties.class)
public class HunterAutoConfiguration {


    static {
        System.out.println("static 。。。。。。。。。。。。。。。。。。。。。");
    }

    public HunterAutoConfiguration() {
      System.out.println("HunterAutoConfiguration ==============================================>");
    }

    @Autowired
    private HunterProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public Hunter hunter() {
        HunterService hunterService = buildService(properties.getClientAddress());
        log.info("Building service for endpoint: " + properties.getClientAddress());
        return Hunter.build(hunterService);
    }

    @Bean
    @ConditionalOnProperty(
            prefix = HunterProperties.HUNTER_PREFIX, name = "admin-client", havingValue = "true")
    public Hunter admin() {
        HunterService hunterService = buildService(properties.getClientAddress());
        log.info("Building service for endpoint: " + properties.getClientAddress());
        return Hunter.build(hunterService);
    }

    private HunterService buildService(String clientAddress) {
        HunterService hunterService = null;

        if (clientAddress == null || clientAddress.equals("")) {
            hunterService = new HunterService(clientAddress);
        } else if (clientAddress.startsWith("http")) {
            hunterService = new HunterService(clientAddress);
        }
        return hunterService;
    }



    @Bean
    @ConditionalOnBean(Hunter.class)
    HunterHealthIndicator hunterHealthIndicator(Hunter hunter) {
        return new HunterHealthIndicator(hunter);
    }

}
