package com.example.demo;

import com.example.demo.Models.Prediction;
import org.springframework.web.bind.annotation.*;
import org.json.*;

import java.util.*;
//import static java.util.Map.entry;


@RestController
public class Controller {

    private static Deque<Prediction> InboundQueue = new ArrayDeque<Prediction>() {};
    private static Map<String, JSONObject> OutboundResults = Map.ofEntries(
    );

    @GetMapping("/")
    public String index() {
        return "Please use one of the valid api endpoints to send a request";
    }

    // queue prediction request
    @PostMapping("/request")
    public Prediction postPredictionRequest(@RequestBody Prediction wfPredict) {
        Prediction newPredicition = new Prediction(wfPredict);
        newPredicition.setId(UUID.randomUUID().toString());
        System.out.println("ID: " + newPredicition.getId());
        InboundQueue.add(newPredicition);
        return newPredicition;
    }

    // retrieve prediction result status
    @GetMapping("/result")
    public String getPredictionResult() {
        return "Greetings from Spring Boot!";
    }

    // Check queue for next Prediction and returns JSON for item at the top of Stack
    @GetMapping(path = "/queue", produces = {"application/json"})
    public Prediction getQueue(){
        if(!InboundQueue.isEmpty()){
            return InboundQueue.element();
        }
        return new Prediction();
    }

}