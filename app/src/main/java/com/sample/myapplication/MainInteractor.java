package com.sample.myapplication;

import com.weatherlibrary.datamodel.WeatherModel;

/**
 * Created by Abhishek.Kumar on 2/2/2017.
 */

public interface MainInteractor {
    interface onNetworkCallFinishListener{
        void onSuccess(WeatherModel weatherModel);
        void onError(String msg);
    }

    void fetchDataFromNetwork(onNetworkCallFinishListener listener, String s, String valueOf);
}
