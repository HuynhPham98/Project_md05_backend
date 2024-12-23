package com.ra.module5_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Module5ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Module5ProjectApplication.class, args);
    }

}
