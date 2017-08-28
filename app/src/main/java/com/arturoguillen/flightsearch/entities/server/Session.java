package com.arturoguillen.flightsearch.entities.server;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

public class Session implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("Query")
    private Query query;

    @SerializedName("Itineraries")
    private List<Itinerary> itineraries;

    @SerializedName("Legs")
    private List<Leg> legs;

    @SerializedName("Places")
    private List<Place> places;


    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public Query getQuery() {
        return query;
    }
}
