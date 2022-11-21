package com.example.demo;

import com.example.demo.Models.Prediction;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.slf4j.Logger;

@Configuration
public class LoadDatabase {

    private static final Logger log = (Logger) LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PredictionRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Prediction()));
        };
    }
}
