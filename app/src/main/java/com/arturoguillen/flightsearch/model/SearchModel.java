package com.arturoguillen.flightsearch.model;

import android.support.annotation.Nullable;

import com.arturoguillen.flightsearch.di.api.SessionApi;
import com.arturoguillen.flightsearch.entities.client.Flight;
import com.arturoguillen.flightsearch.entities.client.FlightsResult;
import com.arturoguillen.flightsearch.entities.client.Search;
import com.arturoguillen.flightsearch.entities.server.Itinerary;
import com.arturoguillen.flightsearch.entities.server.Place;
import com.arturoguillen.flightsearch.entities.server.Query;
import com.arturoguillen.flightsearch.entities.server.Session;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    /*TODO : Revisar que con el cambio de esquema a sky todo funciona bien (cambiarlo en postman)
    *        Añadir todo lo referente a los  vuelos teniendo en cuenta el tema de itineraries, legs y segments
    *        */
    public Disposable getFlightsInfo(final Search search, DisposableObserver<FlightsResult> observer) {

        Observable<Response<Session>> observable = sessionApi.createSession(
                "Economy",
                "UK",
                "GBP",
                "en-GB",
                "sky",
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
                concatMap(new Function<Response<Session>, ObservableSource<Session>>() {
                    @Override
                    public ObservableSource<Session> apply(Response<Session> sessionResponse) throws Exception {

                        String location = sessionResponse.headers().get("location");
                        String sessionKey = location.replace("http://partners.api.skyscanner.net/apiservices/pricing/uk2/v1.0/", "");
                        return sessionApi.pollSession(sessionKey, apiKey).subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                }).
                flatMap(new Function<Session, ObservableSource<FlightsResult>>() {
                    @Override
                    public ObservableSource<FlightsResult> apply(Session session) throws Exception {
                        FlightsResult flightResult = new FlightsResult();

                        setDestinationInfo(flightResult, session);

                        setOriginInfo(flightResult, session);

                        setInBoundDate(flightResult, session.getQuery().getInboundDate());

                        setOutBoundDate(flightResult, session.getQuery().getOutboundDate());

                        flightResult.setNumberOfResults(session.getItineraries().size());

                        List<Flight> flights = new ArrayList<>();
                        for (Itinerary itinerary : session.getItineraries()) {
                            setFlight(flights, itinerary);
                        }
                        flightResult.setFligths(flights);

                        return Observable.just(flightResult);
                    }
                }).
                subscribeWith(observer);
    }

    private void setFlight(List<Flight> flights, Itinerary itinerary) {

    }

    private void setOutBoundDate(FlightsResult flightResult, String outboundDate) {
        flightResult.setOutboundDate(convertToDate(outboundDate));
    }

    private void setInBoundDate(FlightsResult flightResult, String inboundDate) {
        flightResult.setInboundDate(convertToDate(inboundDate));
    }

    private void setOriginInfo(FlightsResult flightResult, Session session) {
        Query query = session.getQuery();

        int originPlaceId = query.getOriginPlace();
        flightResult.setOriginPlaceId(originPlaceId);

        String originPlace = getPlaceName(session.getPlaces(), query.getOriginPlace());
        flightResult.setOriginPlace(originPlace);
    }

    private void setDestinationInfo(FlightsResult flightResult, Session session) {
        Query query = session.getQuery();

        int destinationPlaceId = query.getDestinationPlace();
        flightResult.setDestinationPlaceId(destinationPlaceId);

        String destinationPlace = getPlaceName(session.getPlaces(), query.getDestinationPlace());
        flightResult.setDestinationPlace(destinationPlace);
    }

    private String convertToDate(String inboundDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date myDate = null;
        try {
            myDate = dateFormat.parse(inboundDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat timeFormat = new SimpleDateFormat("MMM dd., EEE");
        return timeFormat.format(myDate);
    }

    private
    @Nullable
    String getPlaceName(List<Place> places, int placeId) {
        for (Place place : places) {
            if (place.getId() == placeId)
                return place.getName();
        }
        return null;
    }
}
