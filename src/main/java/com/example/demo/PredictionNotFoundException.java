package com.example.demo;

public class PredictionNotFoundException extends RuntimeException {
    PredictionNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
