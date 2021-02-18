package com.yohann.consumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import java.net.UnknownHostException;

/**
 * @author Yohann
 * @since 2021/2/6 17:33
 */
@SpringBootApplication()
@ComponentScan(basePackages = {"com.yohann"})
@EnableDiscoveryClient
@MapperScan("com.yohann.*.mapper")
@EnableFeignClients(basePackages = "com.yohann.common.feign")
@EnableCaching
public class ConsumerApplication {
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}