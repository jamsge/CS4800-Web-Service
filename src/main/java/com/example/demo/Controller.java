package com.example.demo;

import com.example.demo.Models.Prediction;
import org.springframework.web.bind.annotation.*;

import java.util.*;
//import static java.util.Map.entry;


@RestController
public class Controller {

    private final PredictionRepository repository;

    private static Deque<Prediction> InboundQueue = new ArrayDeque<Prediction>() {};

    private static Map<String, Prediction> OutboundResults = Map.ofEntries();

    public Controller(PredictionRepository repository) {
        this.repository = repository;
    }

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

//    Most Likely not needed: retrieve prediction result status
//    @PostMapping("/submitResult")
//    public String submitPredictionResult(@RequestBody Prediction wfPredict) {
//        OutboundResults.put(wfPredict.getId(), wfPredict);
//        return "";
//    }

//  For the AI to submit its computed Prediction result.
    @GetMapping(path = "/predictions/{id}", produces = {"application/json"})
    public Prediction submitPredictionResult(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PredictionNotFoundException(id));
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