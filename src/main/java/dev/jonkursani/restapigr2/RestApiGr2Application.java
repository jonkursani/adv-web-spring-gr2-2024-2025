package dev.jonkursani.restapigr2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RestApiGr2Application {

    public static void main(String[] args) {
        SpringApplication.run(RestApiGr2Application.class, args);
    }

}
