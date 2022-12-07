package com.example.demo.Models;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Prediction {

    public String id;
    private double avgTemperature;
    private double avgHumidity;
    private double avgRainfall30;
    private double avgRainfall60;
    private double avgRainfall90;

    public static final String STATUS_WAITING = "waiting";
    public static final String STATUS_IN_PROGRESS = "in_progress";
    public static final String STATUS_FINISHED = "finished";
    private String status = STATUS_WAITING;

//    public Prediction() {
//    }

    public Prediction(double avgTemperature, double avgHumidity, double avgRainfall30, double avgRainfall60, double avgRainfall90){
        this.id = UUID.randomUUID().toString();
        this.avgTemperature = avgTemperature;
        this.avgHumidity = avgHumidity;
        this.avgRainfall30 = avgRainfall30;
        this.avgRainfall60 = avgRainfall60;
        this.avgRainfall90 = avgRainfall90;
    }

    public Prediction(Prediction pr){
        this.id = pr.id;
        this.avgTemperature = pr.avgTemperature;
        this.avgHumidity = pr.avgHumidity;
        this.avgRainfall30 = pr.avgRainfall30;
        this.avgRainfall60 = pr.avgRainfall60;
        this.avgRainfall90 = pr.avgRainfall90;
    }

    public void setStatusInProgress(){
        this.status = STATUS_IN_PROGRESS;
    }
    public void setStatusFinished(){
        this.status = STATUS_FINISHED;
    }

    public double getAvgTemperature() {
        return avgTemperature;
    }

    public void setAvgTemperature(double avgTemperature) {
        this.avgTemperature = avgTemperature;
    }

    public double getAvgHumidity() {
        return avgHumidity;
    }

    public void setAvgHumidity(double avgHumidity) {
        this.avgHumidity = avgHumidity;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    @Override
    public String toString() {
        return "Prediction{" +
                "id=" + id +
                ", avgTemperature=" + avgTemperature +
                ", avgHumidity=" + avgHumidity +
                ", avgRainfall30=" + avgRainfall30 +
                ", avgRainfall60=" + avgRainfall60 +
                ", avgRainfall90=" + avgRainfall90 +
                ", status='" + status + '\'' +
                '}';
    }
}
