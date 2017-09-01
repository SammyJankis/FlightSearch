package com.arturoguillen.flightsearch.view.result;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arturoguillen.flightsearch.R;
import com.arturoguillen.flightsearch.entities.client.Trip;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by agl on 01/09/2017.
 */

class TripsAdapter extends RecyclerView.Adapter {


    private Picasso picasso;
    private List<Trip> trips;

    public TripsAdapter(Picasso picasso, List<Trip> trips) {
        this.picasso = picasso;
        this.trips = trips;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TripCard(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_trip, parent, false), picasso);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TripCard) holder).fillTripCard(trips.get(position));
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }
}

