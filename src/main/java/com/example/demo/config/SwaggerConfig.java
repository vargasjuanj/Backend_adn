package com.example.demo.config;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo")) //paquete donde está el main
                .paths(regex("/api/v1/persona.*"))  //debe llevar la barra delantera
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "MUTANT API REST",
                "UTN  Prof.: Alberto Cortez",
                "1.0",
                "https://github.com/vargasjuanj",
                new Contact("Juan José Vargas", "",
                        "juanjovargas443377@gmail.com"),
                "",
                "", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }

}
