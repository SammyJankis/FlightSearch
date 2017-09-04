package com.arturoguillen.flightsearch.entities.client;

import java.io.Serializable;
import java.util.List;

/**
 * Created by agl on 28/08/2017.
 */

public class FlightsResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private int originPlaceId;

    private String originPlace;

    private int destinationPlaceId;

    private String destinationPlace;

    private List<Trip> trips;

    private String outboundDate;

    private String inboundDate;

    private int numberOfResults;

    public void setOriginPlaceId(int originPlaceId) {
        this.originPlaceId = originPlaceId;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public void setDestinationPlaceId(int destinationPlaceId) {
        this.destinationPlaceId = destinationPlaceId;
    }

    public void setDestinationPlace(String destinationPlace) {
        this.destinationPlace = destinationPlace;
    }

    public void setOutboundDate(String outboundDate) {
        this.outboundDate = outboundDate;
    }

    public void setInboundDate(String inboundDate) {
        this.inboundDate = inboundDate;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public void setNumberOfResults(int numberOfResults) {
        this.numberOfResults = numberOfResults;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public String getDestinationPlace() {
        return destinationPlace;
    }

    public String getOutboundDate() {
        return outboundDate;
    }

    public String getInboundDate() {
        return inboundDate;
    }

    public int getNumberOfResults() {
        return numberOfResults;
    }

    public List<Trip> getTrips() {
        return trips;
    }
}
