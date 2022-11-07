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

    public Prediction(double avgTemperature, double avgHumidity, double avgRainfall30, double avgRainfall60, double avgRainfall90){
        this.avgTemperature = avgTemperature;
        this.avgHumidity = avgHumidity;
        this.avgRainfall30 = avgRainfall30;
        this.avgRainfall60 = avgRainfall60;
        this.avgRainfall90 = avgRainfall90;
        date = getDate();
    }

    public Prediction(Prediction pr){
        pr.avgTemperature = avgTemperature;
        pr.avgHumidity = avgHumidity;
        pr.avgRainfall30 = avgRainfall30;
        pr.avgRainfall60 = avgRainfall60;
        pr.avgRainfall90 = avgRainfall90;
        date = getDate();
    }

    public void setStatusInProgress(){
        this.status = STATUS_IN_PROGRESS;
    }
    public void setStatusFinished(){
        this.status = STATUS_FINISHED;
    }

    public String getStatus() { return toString(); }

    @Override
    public String toString(){
        return "ID: " + this.getId() + " Status: " + this.getStatus();
    }
}
