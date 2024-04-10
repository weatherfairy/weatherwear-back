package com.weatherfairy.weatherwearback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    @Autowired
    static ApplicationContext appContext;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
