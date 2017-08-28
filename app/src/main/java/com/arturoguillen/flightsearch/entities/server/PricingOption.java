package com.arturoguillen.flightsearch.entities.server;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

public class PricingOption implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("Agents")
    private int[] agents;

    @SerializedName("QuoteAgeInMinutes")
    private int quoteAgeInMinutes;

    @SerializedName("Price")
    private Double price;

    @SerializedName("DeeplinkUrl")
    private String deeplinkUrl;
}
