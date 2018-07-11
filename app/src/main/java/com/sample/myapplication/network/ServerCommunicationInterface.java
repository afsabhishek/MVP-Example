package com.sample.myapplication.network;

import com.weatherlibrary.datamodel.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Abhishek.Kumar on 2/2/2017.
 */

public interface ServerCommunicationInterface {

        @GET
        Call<WeatherModel> getWeather(@Url String url);

}
