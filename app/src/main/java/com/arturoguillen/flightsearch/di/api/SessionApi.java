package com.arturoguillen.flightsearch.di.api;

import com.arturoguillen.flightsearch.entities.server.Session;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

public interface SessionApi {

    @FormUrlEncoded
    @POST("/apiservices/pricing/v1.0")
    Observable<Response<Session>> createSession(@Field("cabinclass") String cabinclass,
                                                @Field("country") String country,
                                                @Field("currency") String currency,
                                                @Field("locale") String locale,
                                                @Field("locationSchema") String locationSchema,
                                                @Field("originplace") String originplace,
                                                @Field("destinationplace") String destinationplace,
                                                @Field("outbounddate") String outbounddate,
                                                @Field("inbounddate") String inbounddate,
                                                @Field("adults") int adults,
                                                @Field("children") int children,
                                                @Field("infants") int infants,
                                                @Field("apikey") String apikey
    );

    @GET("/apiservices/pricing/uk2/v1.0/{sessionKey}")
    Observable<Session> pollSession(@Path("sessionKey") String sessionKey, @Query("apikey") String apikey);

}
