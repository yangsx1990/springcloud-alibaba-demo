package com.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-23
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ProviderAApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderAApplication.class, args);
    }

}
