package com.arturoguillen.flightsearch.view.result;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.arturoguillen.flightsearch.R;
import com.arturoguillen.flightsearch.entities.client.Trip;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by agl on 01/09/2017.
 */

class TripCard extends RecyclerView.ViewHolder {

    @BindView(R.id.title)
    TextView title;

    /*@BindView(R.id.feed_picasso)
    PicassoView feedPicasso;*/

    private Picasso picasso;

    public TripCard(View itemView, Picasso picasso) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.picasso = picasso;
    }

    public void fillTripCard(Trip trip) {
        title.setText(trip.getInboundOrigin());
    }
}
