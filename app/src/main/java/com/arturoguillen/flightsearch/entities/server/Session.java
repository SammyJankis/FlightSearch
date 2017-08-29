package com.arturoguillen.flightsearch.entities.server;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

public class Session {

    @SerializedName("Query")
    private Query query;

    @SerializedName("Itineraries")
    private List<Itinerary> itineraries;

    @SerializedName("Legs")
    private List<Leg> legs;

    @SerializedName("Places")
    private List<Place> places;

    @SerializedName("Carriers")
    private List<Carrier> carriers;


    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public Query getQuery() {
        return query;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public List<Carrier> getCarriers() {
        return carriers;
    }
}
