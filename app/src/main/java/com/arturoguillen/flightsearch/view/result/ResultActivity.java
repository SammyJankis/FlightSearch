package com.arturoguillen.flightsearch.view.result;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.arturoguillen.flightsearch.R;
import com.arturoguillen.flightsearch.entities.client.FlightsResult;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arturo.guillen on 29/08/2017.
 */

public class ResultActivity extends AppCompatActivity {

    private FlightsResult flightsResult;
    private static final String EXTRA_FLIGHTRESULT = "EXTRA_FLIGHTRESULT";

    @BindView(R.id.flight_dest_orig)
    TextView tvHeaderDestOrig;

    @BindView(R.id.flight_dates)
    TextView tvHeaderDates;

    @BindView(R.id.flights_results)
    TextView tvHeaderResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        flightsResult = getFlightResultExtra(savedInstanceState);

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
    }
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
}
