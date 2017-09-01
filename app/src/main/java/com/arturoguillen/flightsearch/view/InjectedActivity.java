package com.arturoguillen.flightsearch.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.arturoguillen.flightsearch.App;
import com.arturoguillen.flightsearch.di.component.FlightComponent;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

public abstract class InjectedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectComponent(((App) getApplication())
                .getComponent());
    }

    protected abstract void injectComponent(FlightComponent component);

}
