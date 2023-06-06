package com.example.yehiaticketsystem;

import javafx.collections.ObservableList;

public class TrainSearchModel {
    String DepartureLoc, DepartureTime, ArrivalLoc, ArrivalTime;

    public TrainSearchModel(String departureLoc, String departureTime, String arrivalLoc, String arrivalTime) {
        DepartureLoc = departureLoc;
        DepartureTime = departureTime;
        ArrivalLoc = arrivalLoc;
        ArrivalTime = arrivalTime;
    }

    public String getDepartureLoc() {
        return DepartureLoc;
    }

    public void setDepartureLoc(String departureLoc) {
        DepartureLoc = departureLoc;
    }

    public String getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(String departureTime) {
        DepartureTime = departureTime;
    }

    public String getArrivalLoc() {
        return ArrivalLoc;
    }

    public void setArrivalLoc(String arrivalLoc) {
        ArrivalLoc = arrivalLoc;
    }

    public String getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }
}
