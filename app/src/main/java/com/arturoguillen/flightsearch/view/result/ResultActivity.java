package com.arturoguillen.flightsearch.view.result;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.widget.TextView;

import com.arturoguillen.flightsearch.R;
import com.arturoguillen.flightsearch.di.component.FlightComponent;
import com.arturoguillen.flightsearch.entities.client.FlightsResult;
import com.arturoguillen.flightsearch.entities.client.Trip;
import com.arturoguillen.flightsearch.view.InjectedActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arturo.guillen on 29/08/2017.
 */

public class ResultActivity extends InjectedActivity {

    private FlightsResult flightsResult;
    private static final String EXTRA_FLIGHTRESULT = "EXTRA_FLIGHTRESULT";
    private static final String RECYCLERVIEW_STATE = "RECYCLERVIEW_STATE";
    private static final String RECYCLEVIEW_CONTENT = "RECYCLEVIEW_CONTENT";

    @Inject
    Picasso picasso;

    @BindView(R.id.flight_dest_orig)
    TextView tvHeaderDestOrig;

    @BindView(R.id.flight_dates)
    TextView tvHeaderDates;

    @BindView(R.id.flights_results)
    TextView tvHeaderResults;

    @BindView(R.id.rv_flights)
    RecyclerView rvFlights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        flightsResult = getFlightResultExtra(savedInstanceState);

        initUIHeader(flightsResult);
    }

    private void initUIHeader(FlightsResult flightsResult) {
        String destOrig = flightsResult.getOriginPlace() + " "
                + getString(R.string.to) + " " +
                flightsResult.getDestinationPlace();
        tvHeaderDestOrig.setText(destOrig);

        String dates = flightsResult.getInboundDate() + " - " +
                flightsResult.getOutboundDate();
        tvHeaderDates.setText(dates);

        String results = flightsResult.getNumberOfResults() + " "
                + getString(R.string.of) + " " +
                flightsResult.getNumberOfResults() + " " +
                getString(R.string.results_shown);
        tvHeaderResults.setText(results);

        setupRecyclerView(flightsResult.getTrips());
    }

    private void setupRecyclerView(List<Trip> trips) {
        TripsAdapter feedAdapter = new TripsAdapter(picasso, trips);
        rvFlights.setAdapter(feedAdapter);
        rvFlights.setLayoutManager(new LinearLayoutManager(this));
        ((SimpleItemAnimator) rvFlights.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    private FlightsResult getFlightResultExtra(Bundle savedInstanceState) {
        FlightsResult flightsResult;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                flightsResult = null;
            } else {
                flightsResult = (FlightsResult) extras.get(EXTRA_FLIGHTRESULT);
            }
        } else {
            flightsResult = (FlightsResult) savedInstanceState.get(EXTRA_FLIGHTRESULT);
        }
        return flightsResult;
    }

    public static Intent createIntent(Context context, FlightsResult flightsResult) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(EXTRA_FLIGHTRESULT, flightsResult);
        return intent;
    }

    @Override
    protected void injectComponent(FlightComponent component) {
        component.inject(this);
    }
}
