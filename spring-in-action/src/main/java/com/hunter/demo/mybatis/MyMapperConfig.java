package com.hunter.demo.mybatis;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Hunter
 * @date 2021/11/06 12:08
 **/
@Configuration
@Import({MyMapperScannerRegistrar.class})
public class MyMapperConfig {
}
