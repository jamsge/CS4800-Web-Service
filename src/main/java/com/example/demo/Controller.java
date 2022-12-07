package com.example.demo;

import com.example.demo.Models.Prediction;
import com.example.demo.Models.WildfirePrediction;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class Controller {
    private static Map<String, Prediction> OutboundResults = new HashMap<>();

    @GetMapping("/")
    public String index() {
        return "Please use one of the valid api endpoints to send a request";
    }

    // queue prediction request
    @PostMapping("/request")
    public Prediction postPredictionRequest(@RequestBody Prediction wfPredict) {
        Prediction newPredicition = new Prediction(wfPredict);
        OutboundResults.put((String) newPredicition.getId(), newPredicition);
        return newPredicition;
    }

    //For the AI to submit its computed Prediction result.
    @GetMapping(path = "/predictions/{id}", produces = {"application/json"})
    public ArrayList<WildfirePrediction> getPredictionResult(@PathVariable String id) {
        if(!OutboundResults.isEmpty()){
            ArrayList<WildfirePrediction> one = WildfirePrediction.gettingPred(OutboundResults.get(id));
            return one;
        }
        return null;
    }
}