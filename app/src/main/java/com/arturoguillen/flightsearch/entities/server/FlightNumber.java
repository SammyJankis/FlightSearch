package com.arturoguillen.flightsearch.entities.server;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

public class FlightNumber implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("FlightNumber")
    private String flightNumber;

    @SerializedName("CarrierId")
    private int carrierId;
}
