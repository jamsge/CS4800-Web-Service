package com.example.demo;

import com.example.demo.Models.Prediction;
import com.example.demo.Models.WildfirePrediction;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

        ArrayList<WildfirePrediction> one = new ArrayList<>();

        one.add(new WildfirePrediction("Old Station, California",40.6486955, -121.5920971, 15000, 0.3));
        one.add(new WildfirePrediction("Ruth, California",40.2822730, -123.3334177, 900, 0.05));
        one.add(new WildfirePrediction("Banning, California",34.0362711, -116.8057220, 1200, 0.15));
        one.add(new WildfirePrediction("Anza, CA",33.6261821, -116.7585828, 7000, 0.2));

        return one;

//        if(!OutboundResults.isEmpty()){
//
//            ArrayList<WildfirePrediction> one = WildfirePrediction.gettingPred(OutboundResults.get(id));
//            return one;
//        }
    }
}