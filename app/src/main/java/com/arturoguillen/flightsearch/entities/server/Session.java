package com.arturoguillen.flightsearch.entities.server;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

public class Session implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("Itineraries")
    private List<Itinerary> itineraries;

    @SerializedName("Legs")
    private List<Leg> legs;
}
