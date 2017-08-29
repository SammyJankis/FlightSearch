package com.arturoguillen.flightsearch.view.search;

import android.support.annotation.StringRes;

import com.arturoguillen.flightsearch.entities.client.FlightsResult;

/**
 * Created by arturo.guillen on 29/08/2017.
 */

public interface SearchView {

    void showProgress();

    void hideProgress();

    void showMessage(@StringRes int stringId);

    void hideMessage();

    void goToResultActivity(FlightsResult flightsResult);
}
