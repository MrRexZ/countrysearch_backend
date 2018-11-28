package com.mrrexz.countrysearch_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CountrysearchBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountrysearchBackendApplication.class, args);
    }
}
