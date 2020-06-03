package com.acme.interlab.config.Api;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.ToString;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ToString
@Configuration
public class config {

@Bean(name = "interlabOpenApi")
    public OpenAPI interlabOpenAPI(){
    //Available at

    return new OpenAPI()
            .components(new Components())
            .info(new Info().title("InterLab Application API").description(
                    "InterLab API implemented with Spring Boot Restful service and documented using springdoc-openapi and OpenApi 3. "
            ));
    }
}

