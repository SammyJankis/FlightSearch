package com.arturoguillen.flightsearch.presenter;

/**
 * Created by arturo.guillen on 29/08/2017.
 */

public interface PresenterInterface<V> {

    void attachView(V view);

    void detachView();

}
