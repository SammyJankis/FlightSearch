package com.arturoguillen.flightsearch.entities.server;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agl on 28/08/2017.
 */

public class Query implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("OriginPlace")
    private int originPlace;

    @SerializedName("DestinationPlace")
    private int destinationPlace;

    @SerializedName("OutboundDate")
    private String outboundDate;

    @SerializedName("InboundDate")
    private String inboundDate;

    public int getOriginPlace() {
        return originPlace;
    }

    public int getDestinationPlace() {
        return destinationPlace;
    }

    public String getOutboundDate() {
        return outboundDate;
    }

    public String getInboundDate() {
        return inboundDate;
    }
}
