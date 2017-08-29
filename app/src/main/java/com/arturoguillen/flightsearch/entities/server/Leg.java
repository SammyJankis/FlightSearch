package com.arturoguillen.flightsearch.entities.server;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

public class Leg {

    @SerializedName("Id")
    private String id;

    @SerializedName("OriginStation")
    private int originStation;

    @SerializedName("DestinationStation")
    private int destinationStation;

    @SerializedName("Departure")
    private String departure;

    @SerializedName("Arrival")
    private String arrival;

    @SerializedName("Duration")
    private int duration;

    @SerializedName("Stops")
    private int[] stops;

    @SerializedName("Carriers")
    private int[] carriers;

    public String getId() {
        return id;
    }

    public int getOriginStation() {
        return originStation;
    }

    public int getDestinationStation() {
        return destinationStation;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public int getDuration() {
        return duration;
    }

    public int[] getStops() {
        return stops;
    }

    public int[] getCarriers() {
        return carriers;
    }
}
