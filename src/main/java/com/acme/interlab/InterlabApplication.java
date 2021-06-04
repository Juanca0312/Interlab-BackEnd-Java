package com.acme.interlab;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

//Comment @EnableJpaAuditing in case you wanna try your unit tests
@SpringBootApplication
@EnableJpaAuditing
@Configuration
@EnableSwagger2WebFlux
@Import(SpringDataRestConfiguration.class)
public class InterlabApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterlabApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() { return new ModelMapper(); }
}
