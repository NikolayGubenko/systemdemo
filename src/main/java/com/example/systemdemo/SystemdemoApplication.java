package com.example.systemdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SystemdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemdemoApplication.class, args);
    }

}
