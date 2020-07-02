package com.acme.interlab;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.ui.Model;

//Comment @EnableJpaAuditing in case you wanna try your unit tests
@SpringBootApplication
@EnableJpaAuditing
public class InterlabApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterlabApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() { return new ModelMapper(); }

}
