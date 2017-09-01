package com.arturoguillen.flightsearch;

import android.app.Application;

import com.arturoguillen.flightsearch.di.component.DaggerFlightComponent;
import com.arturoguillen.flightsearch.di.component.FlightComponent;
import com.arturoguillen.flightsearch.di.module.SearchModule;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

public class App extends Application {

    private FlightComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = createComponent();
    }

    protected FlightComponent createComponent() {
        return DaggerFlightComponent.builder()
                .searchModule(new SearchModule(this))
                .build();

    }

    public FlightComponent getComponent() {
        return component;
    }
}
