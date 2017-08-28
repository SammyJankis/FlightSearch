package com.arturoguillen.flightsearch.entities.server;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by agl on 28/08/2017.
 */

public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("Id")
    private int id;

    @SerializedName("Name")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
