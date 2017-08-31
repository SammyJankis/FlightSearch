package com.arturoguillen.flightsearch.di.module;

import android.app.Application;

import com.arturoguillen.flightsearch.di.api.SessionApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by arturo.guillen on 28/08/2017.
 */

@Module
public class SearchModule extends NetModule {

    public SearchModule(Application application) {
        super(application);
    }

    @Provides
    @Singleton
    SessionApi provideSearchApi(Retrofit retrofit) {
        return retrofit.create(SessionApi.class);
    }
}
