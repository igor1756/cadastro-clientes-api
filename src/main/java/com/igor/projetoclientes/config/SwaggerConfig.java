// src/main/java/com/seunome/projetoclientes/config/SwaggerConfig.java
package com.igor.projetoclientes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Cadastro de Clientes")
                        .version("1.0")
                        .description("API RESTful para gerenciar o cadastro de clientes."));
    }
}