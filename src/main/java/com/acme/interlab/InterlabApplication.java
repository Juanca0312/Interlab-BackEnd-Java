package com.acme.interlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InterlabApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterlabApplication.class, args);
    }

}
