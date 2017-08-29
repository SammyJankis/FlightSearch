package com.arturoguillen.flightsearch.entities.server;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

public class PricingOption {

    @SerializedName("Price")
    private Double price;

    public Double getPrice() {
        return price;
    }
}
