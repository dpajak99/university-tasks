package com.tarbus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  private ApiInfo apiInfo() {
    return new ApiInfo("Hacknarok backend template",
      "APIs for MyApp.",
      "1.0",
      "Terms of service",
      new Contact("VeryBadCode", "www.verybadcode.com", "dominik00801@gmail.com"),
      "License of API",
      "API license URL",
      Collections.emptyList());
  }


  @Bean
  public Docket api() {
    return new Docket(DocumentationType.OAS_30)
      .apiInfo(apiInfo())
      .select()
      .apis(RequestHandlerSelectors.any())
      .paths(PathSelectors.any())
      .build();
  }
}