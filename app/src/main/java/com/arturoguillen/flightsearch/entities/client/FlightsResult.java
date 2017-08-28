package com.arturoguillen.flightsearch.entities.client;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by agl on 28/08/2017.
 */

public class FlightsResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("originPlaceId")
    private int originPlaceId;

    @SerializedName("originPlace")
    private String originPlace;

    @SerializedName("destinationPlaceId")
    private int destinationPlaceId;

    @SerializedName("destinationPlace")
    private String destinationPlace;

    @SerializedName("fligths")
    private List<Flight> fligths;

    @SerializedName("outboundDate")
    private String outboundDate;

    @SerializedName("inboundDate")
    private String inboundDate;

    @SerializedName("numberOfResults")
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

    public List<Flight> getFligths() {
        return fligths;
    }

    public void setFligths(List<Flight> fligths) {
        this.fligths = fligths;
    }


    public void setNumberOfResults(int numberOfResults) {
        this.numberOfResults = numberOfResults;
    }
}
