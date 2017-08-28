package com.arturoguillen.flightsearch.model;

import com.arturoguillen.flightsearch.di.api.SessionApi;
import com.arturoguillen.flightsearch.entities.client.Flight;
import com.arturoguillen.flightsearch.entities.client.Search;
import com.arturoguillen.flightsearch.entities.server.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

public class SearchModel extends BaseModel {

    private static final String apiKey = "ss630745725358065467897349852985";

    SessionApi sessionApi;

    @Inject
    public SearchModel(SessionApi sessionApi) {
        this.sessionApi = sessionApi;
    }


    public Disposable getFlightsInfo(Search search, DisposableObserver<List<Flight>> observer) {

        Observable<Response<Session>> observable = sessionApi.createSession(
                "Economy",
                "UK",
                "GBP",
                "en-GB",
                "iata",
                search.getOriginPlace(),
                search.getDestinationPlace(),
                search.getOutboundDate(),
                search.getInbounddate(),
                search.getAdults(),
                search.getChildren(),
                search.getInfants(),
                apiKey
        );


        return observable.
                compose(this.<Response<Session>>applySchedulers()).
                flatMap(new Function<Response<Session>, ObservableSource<Session>>() {
                    @Override
                    public ObservableSource<Session> apply(Response<Session> sessionResponse) throws Exception {

                        String location = sessionResponse.headers().get("location");
                        String sessionKey = location.replace("http://partners.api.skyscanner.net/apiservices/pricing/uk2/v1.0/", "");
                        return sessionApi.pollSession(sessionKey,apiKey);
                    }
                }).
                flatMap(new Function<Session, ObservableSource<List<Flight>>>() {
                    @Override
                    public ObservableSource<List<Flight>> apply(Session session) throws Exception {
                        List<Flight> flightList = new ArrayList<Flight>();

                        return Observable.just(flightList).subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread().;
                    }
                }).
                subscribeWith(observer);
    }
}
