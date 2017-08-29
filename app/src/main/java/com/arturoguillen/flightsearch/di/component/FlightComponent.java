package com.arturoguillen.flightsearch.di.component;

import com.arturoguillen.flightsearch.di.module.SearchModule;
import com.arturoguillen.flightsearch.view.search.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

@Singleton
@Component(modules = {SearchModule.class})
public interface FlightComponent {
    void inject(SearchActivity searchActivity);
}
