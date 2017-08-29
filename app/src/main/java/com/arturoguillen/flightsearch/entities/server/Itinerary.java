package com.arturoguillen.flightsearch.entities.server;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

public class Itinerary {

    @SerializedName("OutboundLegId")
    private String OutboundLegId;

    @SerializedName("InboundLegId")
    private String InboundLegId;

    @SerializedName("PricingOptions")
    private List<PricingOption> pricingOptions;

    public String getOutboundLegId() {
        return OutboundLegId;
    }

    public String getInboundLegId() {
        return InboundLegId;
    }

    public List<PricingOption> getPricingOptions() {
        return pricingOptions;
    }
}
