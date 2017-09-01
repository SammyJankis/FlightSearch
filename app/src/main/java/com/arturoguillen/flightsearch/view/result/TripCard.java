package com.arturoguillen.flightsearch.view.result;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.arturoguillen.flightsearch.R;
import com.arturoguillen.flightsearch.entities.client.Trip;
import com.arturoguillen.flightsearch.utils.ui.PicassoView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agl on 01/09/2017.
 */

class TripCard extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_price)
    TextView tvPrice;

    @BindView(R.id.tv_inbound_time)
    TextView tvInboundTime;

    @BindView(R.id.tv_inbound_info)
    TextView tvInboundInfo;

    @BindView(R.id.tv_inbound_type)
    TextView tvInboundType;

    @BindView(R.id.tv_inbound_duration)
    TextView tvInboundDuration;

    @BindView(R.id.pi_inbound_carrier)
    PicassoView piInboundCarrier;

    @BindView(R.id.tv_outbound_time)
    TextView tvOutboundTime;

    @BindView(R.id.tv_outbound_info)
    TextView tvOutboundInfo;

    @BindView(R.id.tv_outbound_type)
    TextView tvOutboundType;

    @BindView(R.id.tv_outbound_duration)
    TextView tvOutboundDuration;

    @BindView(R.id.pi_outbound_carrier)
    PicassoView piOutboundCarrier;

    private Picasso picasso;

    public TripCard(View itemView, Picasso picasso) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.picasso = picasso;
    }

    public void fillTripCard(Trip trip) {
        tvInboundTime.setText(getTimeFormat(trip.getInboundDepartureTime(), trip.getInboundArrivalTime()));
        tvInboundInfo.setText(getInfoFormat(trip.getInboundOrigin(), trip.getInboundDestination(), trip.getInboundCarrierName()));
        tvInboundType.setText(getTypeFormat(trip.getInboundStops()));
        tvInboundDuration.setText(getDurationFormat(trip.getInboundDuration()));
        piInboundCarrier.init(picasso, trip.getInboundCarrierImage());

        tvOutboundTime.setText(getTimeFormat(trip.getOutboundDepartureTime(), trip.getOutboundArrivalTime()));
        tvOutboundInfo.setText(getInfoFormat(trip.getOutboundOrigin(), trip.getOutboundDestination(), trip.getOutboundCarrierName()));
        tvOutboundType.setText(getTypeFormat(trip.getOutboundStops()));
        tvOutboundDuration.setText(getDurationFormat(trip.getOutboundDuration()));

        piOutboundCarrier.init(picasso, trip.getOutboundCarrierImage());

        tvPrice.setText("£ " + String.valueOf(trip.getPrice()));

    }

    private String getDurationFormat(int totalDurationMinutes) {
        int hours = totalDurationMinutes / 60; //since both are ints, you get an int
        int minutes = totalDurationMinutes % 60;
        return String.format("%d h %02d m",hours,minutes);
    }

    private String getTypeFormat(int inboundStops) {
        if (inboundStops > 0)
            return String.valueOf(inboundStops);
        return "Direct";
    }

    private String getInfoFormat(String origin, String destination, String carrierName) {
        return origin + "-" + destination + "," + carrierName;
    }

    private String getTimeFormat(String departureTime, String arrivalTime) {
        return departureTime + " - " + arrivalTime;
    }
}
