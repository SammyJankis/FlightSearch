package com.arturoguillen.flightsearch.model;

import android.support.annotation.Nullable;

import com.arturoguillen.flightsearch.di.api.SessionApi;
import com.arturoguillen.flightsearch.entities.client.FlightsResult;
import com.arturoguillen.flightsearch.entities.client.Search;
import com.arturoguillen.flightsearch.entities.client.Trip;
import com.arturoguillen.flightsearch.entities.server.Carrier;
import com.arturoguillen.flightsearch.entities.server.Itinerary;
import com.arturoguillen.flightsearch.entities.server.Leg;
import com.arturoguillen.flightsearch.entities.server.Place;
import com.arturoguillen.flightsearch.entities.server.PricingOption;
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

                        List<Trip> trips = new ArrayList<>();
                        for (Itinerary itinerary : session.getItineraries()) {
                            Leg outboundLeg = getLeg(session.getLegs(), itinerary.getOutboundLegId());
                            Leg inboundLeg = getLeg(session.getLegs(), itinerary.getInboundLegId());

                            for (PricingOption pricingOption : itinerary.getPricingOptions()) {
                                Trip trip = new Trip();
                                trip.setPrice(pricingOption.getPrice());
                                trip.setOutboundArrivalTime(convertToTime(outboundLeg.getArrival()));
                                trip.setOutboundDepartureTime(convertToTime(outboundLeg.getDeparture()));
                                trip.setOutboundCarrierImage(getCarrierImageUrl(session.getCarriers(), outboundLeg.getCarriers()[0]));
                                trip.setOutboundCarrierName(getCarrierName(session.getCarriers(), outboundLeg.getCarriers()[0]));
                                trip.setOutboundDestination(getPlaceCode(session.getPlaces(), outboundLeg.getDestinationStation()));
                                trip.setOutboundDuration(outboundLeg.getDuration());
                                trip.setOutboundOrigin(getPlaceCode(session.getPlaces(), outboundLeg.getOriginStation()));
                                trip.setOutboundStops(outboundLeg.getStops().length);

                                trip.setInboundArrivalTime(convertToTime(inboundLeg.getArrival()));
                                trip.setInboundCarrierImage(getCarrierImageUrl(session.getCarriers(), inboundLeg.getCarriers()[0]));
                                trip.setInboundCarrierName(getCarrierName(session.getCarriers(), inboundLeg.getCarriers()[0]));
                                trip.setInboundDepartureTime(convertToTime(inboundLeg.getDeparture()));
                                trip.setInboundDestination(getPlaceCode(session.getPlaces(), inboundLeg.getDestinationStation()));
                                trip.setInboundDuration(inboundLeg.getDuration());
                                trip.setInboundOrigin(getPlaceCode(session.getPlaces(), inboundLeg.getOriginStation()));
                                trip.setInboundStops(inboundLeg.getStops().length);

                                trips.add(trip);
                            }
                        }
                        flightResult.setTrips(trips);

                        return Observable.just(flightResult);
                    }
                }).
                subscribeWith(observer);
    }

    private String getCarrierName(List<Carrier> carriers, int carrierId) {
        for (Carrier carrier : carriers) {
            if (carrier.getId() == carrierId)
                return carrier.getName();
        }
        return null;
    }

    private String getCarrierImageUrl(List<Carrier> carriers, int carrierId) {
        for (Carrier carrier : carriers) {
            if (carrier.getId() == carrierId)
                return carrier.getImageUrl();
        }
        return null;
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

    private String convertToDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date myDate = null;
        try {
            myDate = dateFormat.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat timeFormat = new SimpleDateFormat("MMM dd., EEE");
        return timeFormat.format(myDate);
    }

    private String convertToTime(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        Date myDate = null;
        try {
            myDate = dateFormat.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
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

    private
    @Nullable
    String getPlaceCode(List<Place> places, int placeId) {
        for (Place place : places) {
            if (place.getId() == placeId)
                return place.getCode();
        }
        return null;
    }

    private
    @Nullable
    Leg getLeg(List<Leg> legs, String legId) {
        for (Leg leg : legs) {
            if (leg.getId().equals(legId))
                return leg;
        }
        return null;
    }
}
