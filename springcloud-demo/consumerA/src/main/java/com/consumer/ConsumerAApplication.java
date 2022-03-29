package com.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-23
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class ConsumerAApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerAApplication.class, args);
    }

}
