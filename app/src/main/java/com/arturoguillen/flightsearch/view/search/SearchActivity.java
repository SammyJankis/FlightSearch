package com.arturoguillen.flightsearch.view.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.arturoguillen.flightsearch.di.component.FlightComponent;
import com.arturoguillen.flightsearch.entities.client.FlightsResult;
import com.arturoguillen.flightsearch.entities.client.Search;
import com.arturoguillen.flightsearch.model.SearchModel;
import com.arturoguillen.flightsearch.view.InjectedActivity;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

public class SearchActivity extends InjectedActivity {

    @Inject
    SearchModel searchModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Search search = new Search();
        search.setOriginPlace("EDI");
        search.setDestinationPlace("LHR");
        search.setOutboundDate("2017-10-30");
        search.setInbounddate("2017-11-15");
        search.setAdults(1);
        search.setChildren(0);
        search.setInfants(0);

        searchModel.getFlightsInfo(search, new DisposableObserver<FlightsResult>() {
            @Override
            public void onNext(FlightsResult flightsResult) {
                Log.i("MIERDA", flightsResult.getFligths().toString());
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    protected void injectComponent(FlightComponent component) {
        component.inject(this);
    }
}
