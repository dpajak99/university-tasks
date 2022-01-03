package pl.pwsztar.kolokwium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@SpringBootApplication
public class KolokwiumApplication {
    public static void main(String[] args) {
        SpringApplication.run(KolokwiumApplication.class, args);
    }

}
