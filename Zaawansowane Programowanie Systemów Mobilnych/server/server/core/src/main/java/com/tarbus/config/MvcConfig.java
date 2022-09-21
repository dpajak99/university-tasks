package com.tarbus.config;

import com.tarbus.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

  private final AppConfig appConfig;

  @Autowired
  public MvcConfig(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    String path = "file:" + appConfig.STORAGE_ABSOLUTE_PATH + "/";
    System.out.println(path);

    registry.addResourceHandler("/**").addResourceLocations("classpath:/public/");

    registry.addResourceHandler("/static/**")
      .addResourceLocations(path);
  }


  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/static/**")
      .allowedOrigins("http://localhost:8080", "https://dev-panel.tarbus.pl", "https://panel.tarbus.pl", "https://gkp.tarnow.pl/");
  }
}

// http://localhost:8082/swagger-ui/index.html#/