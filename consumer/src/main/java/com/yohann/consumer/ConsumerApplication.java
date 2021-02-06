package com.yohann.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.net.UnknownHostException;

/**
 * @author Yohann
 * @since 2021/2/6 17:33
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ConsumerApplication {
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}