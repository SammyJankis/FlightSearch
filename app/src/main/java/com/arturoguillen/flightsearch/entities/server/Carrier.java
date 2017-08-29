package com.arturoguillen.flightsearch.entities.server;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arturo.guillen on 29/08/2017.
 */

public class Carrier {

    @SerializedName("Id")
    private int id;

    @SerializedName("Name")
    private String name;

    @SerializedName("ImageUrl")
    private String imageUrl;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
