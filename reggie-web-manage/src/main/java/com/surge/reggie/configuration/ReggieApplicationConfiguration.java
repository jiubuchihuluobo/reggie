package com.surge.reggie.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.surge.mapper")
@ComponentScan(basePackages = "com.surge.service")
public class ReggieApplicationConfiguration {
}
