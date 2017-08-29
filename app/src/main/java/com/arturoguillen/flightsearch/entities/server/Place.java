package com.arturoguillen.flightsearch.entities.server;

import com.google.gson.annotations.SerializedName;

/**
 * Created by agl on 28/08/2017.
 */

public class Place {

    @SerializedName("Id")
    private int id;

    @SerializedName("Name")
    private String name;

    @SerializedName("Code")
    private String code;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
