package dev.jonkursani.restapigr2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableScheduling
@EnableMethodSecurity // per annotation based authorization e shtoni kete annotation
public class RestApiGr2Application {

    public static void main(String[] args) {
        SpringApplication.run(RestApiGr2Application.class, args);
    }

}
