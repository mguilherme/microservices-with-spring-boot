package com.guilherme.miguel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Lab4VerbApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab4VerbApplication.class, args);
    }
}
