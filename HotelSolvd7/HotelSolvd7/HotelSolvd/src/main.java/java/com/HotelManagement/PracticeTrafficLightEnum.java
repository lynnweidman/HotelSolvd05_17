package com.HotelManagement;

import enums.TrafficLight;

public class PracticeTrafficLightEnum {
    private int trafficLightId;
    private TrafficLight trafficLight;
    String lightMeaning;

    PracticeTrafficLightEnum(int trafficLightId, TrafficLight trafficLight, String lightMeaning) {
        this.trafficLightId = trafficLightId;
        this.trafficLight = trafficLight;
        this.lightMeaning = lightMeaning;
    }

    public int getTrafficLightId() {
        return trafficLightId;
    }

    public void setTrafficLightId(int trafficLightId) {
        this.trafficLightId = trafficLightId;
    }

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }

    public void setTrafficLight(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    public String getLightMeaning() {
        return lightMeaning;
    }

    public void setLightMeaning(String lightMeaning) {
        this.lightMeaning = lightMeaning;
    }

    @Override
    public String toString() {
        return "PracticeEnums{" +
                "trafficLightId=" + trafficLightId +
                ", trafficLight='" + trafficLight + '\'' +
                ", lightMeaning='" + lightMeaning + '\'' +
                '}';
    }
}
