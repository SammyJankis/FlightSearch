package com.arturoguillen.flightsearch.entities.client;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

public class Search  implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("originplace")
    private String originPlace;

    @SerializedName("destinationplace")
    private String destinationPlace;

    @SerializedName("outbounddate")
    private String outboundDate;

    @SerializedName("inbounddate")
    private String inbounddate;

    @SerializedName("adults")
    private int adults;

    @SerializedName("children")
    private int children;

    @SerializedName("infants")
    private int infants;

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getDestinationPlace() {
        return destinationPlace;
    }

    public void setDestinationPlace(String destinationPlace) {
        this.destinationPlace = destinationPlace;
    }

    public String getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(String outboundDate) {
        this.outboundDate = outboundDate;
    }

    public String getInbounddate() {
        return inbounddate;
    }

    public void setInbounddate(String inbounddate) {
        this.inbounddate = inbounddate;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getInfants() {
        return infants;
    }

    public void setInfants(int infants) {
        this.infants = infants;
    }
}
