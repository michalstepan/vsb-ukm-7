package com.xxx.zds.backend;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App {

    private static final Logger logger = Logger.getLogger(App.class);

    public static void main(final String[] args) {

        SpringApplication.run(new Object[]{App.class}, args);
// Hello world
    }
}
