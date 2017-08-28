package com.arturoguillen.flightsearch.entities.server;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

public class Leg implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("Id")
    private String id;

    @SerializedName("SegmentIds")
    private int[] segmentsIds;

    @SerializedName("OriginStation")
    private int originStation;

    @SerializedName("DestinationStation")
    private int destinationStation;

    @SerializedName("Departure")
    private String departure;

    @SerializedName("Arrival")
    private int arrival;

    @SerializedName("Duration")
    private int duration;

    @SerializedName("JourneyMode")
    private String journeyMode;

    @SerializedName("Stops")
    private int[] stops;

    @SerializedName("Carriers")
    private int[] carriers;

    @SerializedName("OperatingCarriers")
    private int[] operatingCarriers;

    @SerializedName("Directionality")
    private String directionality;

    @SerializedName("FlightNumbers")
    private List<FlightNumber> flightNumbers;
}
