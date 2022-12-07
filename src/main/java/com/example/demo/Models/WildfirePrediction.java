package com.example.demo.Models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class WildfirePrediction {

    private int stationId;
    private double temperature;
    private double humidity;
    private double rainfall;

    public static JSONArray obj;

    public static JSONArray objTwo;


    public WildfirePrediction(int stationId, double temperature, double humidity, double rainfall) {
        this.stationId = stationId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.rainfall = rainfall;
    }

    public double getRainfall() {
        return rainfall;
    }

    public void setRainfall(double rainfall) {
        this.rainfall = rainfall;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public static void loadJsonToJsonObject() throws IOException {
        File f = new File("./cimis_data.json");
        if (f.exists()) {
            InputStream is = new FileInputStream("./cimis_data.json");
            String jsonTxt = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            obj = (new JSONObject(jsonTxt)).getJSONArray("hello");
        }
    }


    static class predict implements Comparable {
        public double dist;
        public int distidx;

        predict(double dist, int distidx) {
            this.dist = dist;
            this.distidx = distidx;
        }

        @Override
        public int compareTo(Object o) {
            predict s1 = (predict) o;
            if (s1.dist < this.dist)
                return 1;
            else if (s1.dist > this.dist) {
                return -1;
            }
            return 0;
        }
    }

    public static ArrayList<WildfirePrediction> gettingPred(Prediction one) {
        PriorityQueue<predict> queue = new PriorityQueue<>(60000);
        ArrayList<WildfirePrediction> procPrediction = new ArrayList<>();

        for (int i = 0; i < obj.length(); i++) {
            try {
                double temperature = obj.getJSONObject(i).getDouble("average_relative_humidity");
                double hum = obj.getJSONObject(i).getDouble("average_air_temp");
                double rain = obj.getJSONObject(i).getDouble("precipitation");
                double temp = dist2(temperature, one.getAvgTemperature(), hum, one.getAvgHumidity(), rain, one.getAvgRainfall30() / 30);
                predict tempPred = new predict(temp, i);
                queue.offer(tempPred);
            } catch (Exception er) {
                continue;
            }
        }

        for (int i = 0; i < 5; i++) {
            predict oney = queue.poll();
            WildfirePrediction teemp = new WildfirePrediction(
                    obj.getJSONObject(oney.distidx).getInt("station_id"),
                    obj.getJSONObject(oney.distidx).getDouble("average_air_temp"),
                    obj.getJSONObject(oney.distidx).getDouble("average_relative_humidity"),
                    obj.getJSONObject(oney.distidx).getDouble("precipitation")
                    );
            procPrediction.add(teemp);
        }
        return procPrediction;
    }

    public static double dist2(double xOne, double xTwo, double yOne, double yTwo, double xThree, double yThree) {
        return (xTwo - xOne) * (xTwo - xOne) + (yTwo - yOne) * (yTwo - yOne) + (yThree - xThree) * (yThree - xThree);
    }

    @Override
    public String toString() {
        return "WildfirePrediction{" +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                '}';
    }
}
