package com.example.demo.Models;
import org.json.JSONObject;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Prediction {

    @NotEmpty(message="A prediction date is required.")
    private Date date;

    private double avgTemperature;
    private double avgHumidity;
    private double avgRainfall30;
    private double avgRainfall60;
    private double avgRainfall90;

    private String id;
    public static final String STATUS_WAITING = "waiting";
    public static final String STATUS_IN_PROGRESS = "in_progress";
    public static final String STATUS_FINISHED = "finished";
    private String status = STATUS_WAITING;
    public void setStatusInProgress(){
        this.status = STATUS_IN_PROGRESS;
    }
    public void setStatusFinished(){
        this.status= STATUS_FINISHED;
    }

    public Prediction toPrediction(){
        return new Prediction();
    }
}
