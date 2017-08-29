package com.arturoguillen.flightsearch.utils;

import android.util.Log;

import com.arturoguillen.flightsearch.BuildConfig;


/**
 * Created by arturo.guillen on 29/08/2017.
 */

public class LogUtils {

    public static void DEBUG(final String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }
}
