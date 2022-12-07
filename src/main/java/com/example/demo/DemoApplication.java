package com.example.demo;

import com.example.demo.Models.WildfirePrediction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException {
		WildfirePrediction.loadJsonToJsonObject();
		SpringApplication.run(DemoApplication.class, args);
	}

}
