package com.arturoguillen.flightsearch.entities.client;

import java.io.Serializable;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

public class Trip implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double price;

    private String outboundOrigin;

    private String outboundDestination;

    private String outboundDepartureTime;

    private String outboundArrivalTime;

    private int outboundStops;

    private int outboundDuration;

    private String outboundCarrierName;

    private String outboundCarrierImage;

    private String inboundOrigin;

    private String inboundDestination;

    private String inboundDepartureTime;

    private String inboundArrivalTime;

    private int inboundStops;

    private int inboundDuration;

    private String inboundCarrierName;

    private String inboundCarrierImage;

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setOutboundOrigin(String outboundOrigin) {
        this.outboundOrigin = outboundOrigin;
    }

    public void setOutboundDestination(String outboundDestination) {
        this.outboundDestination = outboundDestination;
    }

    public void setOutboundDepartureTime(String outboundDepartureTime) {
        this.outboundDepartureTime = outboundDepartureTime;
    }

    public void setOutboundArrivalTime(String outboundArrivalTime) {
        this.outboundArrivalTime = outboundArrivalTime;
    }

    public void setOutboundStops(int outboundStops) {
        this.outboundStops = outboundStops;
    }

    public void setOutboundDuration(int outboundDuration) {
        this.outboundDuration = outboundDuration;
    }

    public void setOutboundCarrierName(String outboundCarrierName) {
        this.outboundCarrierName = outboundCarrierName;
    }

    public void setOutboundCarrierImage(String outboundCarrierImage) {
        this.outboundCarrierImage = outboundCarrierImage;
    }

    public void setInboundOrigin(String inboundOrigin) {
        this.inboundOrigin = inboundOrigin;
    }

    public void setInboundDestination(String inboundDestination) {
        this.inboundDestination = inboundDestination;
    }

    public void setInboundDepartureTime(String inboundDepartureTime) {
        this.inboundDepartureTime = inboundDepartureTime;
    }

    public void setInboundArrivalTime(String inboundArrivalTime) {
        this.inboundArrivalTime = inboundArrivalTime;
    }

    public void setInboundStops(int inboundStops) {
        this.inboundStops = inboundStops;
    }

    public void setInboundDuration(int inboundDuration) {
        this.inboundDuration = inboundDuration;
    }

    public void setInboundCarrierName(String inboundCarrierName) {
        this.inboundCarrierName = inboundCarrierName;
    }

    public void setInboundCarrierImage(String inboundCarrierImage) {
        this.inboundCarrierImage = inboundCarrierImage;
    }
}
