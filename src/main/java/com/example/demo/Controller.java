package com.example.demo;

import com.example.demo.Models.Prediction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.json.*;
import java.util.Map;
//import static java.util.Map.entry;
import java.util.ArrayList;
import java.util.UUID;

@RestController
public class Controller {

    private static ArrayList<Prediction> InboundQueue = new ArrayList<Prediction>();
    private static Map<String, JSONObject> OutboundResults = Map.ofEntries(
    );

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    // queue prediction request
    @PostMapping("/request")
    public Prediction postPredictionRequest(@RequestBody Prediction wfPredict) {
        Prediction newPredicition = new Prediction(wfPredict);
        newPredicition.setId(UUID.randomUUID().toString());
        System.out.println(newPredicition.getAvgHumidity());
        return newPredicition;
    }

    // retrieve prediction result status
    @GetMapping("/result")
    public String getPredictionResult() {
        return "Greetings from Spring Boot!";
    }

    // check queue (for AI)
    @GetMapping("/queue")
    public String getQueue(){
        return "";
    }

}