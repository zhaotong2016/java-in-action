package com.hunter.demo.config;

import com.hunter.demo.bean.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(User.class)
@Configuration
public class AppConfig {
}
