package com.sample.myapplication;

import android.util.Log;

import com.sample.myapplication.network.ServerCommunication;
import com.sample.myapplication.network.ServerCommunicationInterface;
import com.sample.myapplication.util.Constant;
import com.weatherlibrary.RequestBlocks;
import com.weatherlibrary.RequestBuilder;
import com.weatherlibrary.datamodel.WeatherModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Abhishek.Kumar on 2/2/2017.
 */

public class MainInteractorImpl implements MainInteractor{
    String url;
    @Override
    public void fetchDataFromNetwork(final onNetworkCallFinishListener listener, String lat, String lon) {
        ServerCommunicationInterface mServerCommunication = ServerCommunication.getClient().create(ServerCommunicationInterface.class);
        try {
            url = "v1/"+ RequestBlocks.MethodTypeParemeters.GetParameters(RequestBlocks.MethodType.Forecast)+ "?key="+ Constant.API_KEY+ "&"+ RequestBlocks.ReqestFor.LatLong(lat,lon)+ "&"+"days="+5;

        } catch (Exception e) {
            e.printStackTrace();
        }


        Call<WeatherModel> call = mServerCommunication.getWeather(url);
        Log.v("VMA","FINAL "+call.request().toString());
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel>call, Response<WeatherModel> response) {
                WeatherModel weatherModel = response.body();

                listener.onSuccess(weatherModel);
            }

            @Override
            public void onFailure(Call<WeatherModel>call, Throwable t) {

                Log.e(TAG, t.toString());
                listener.onError("Something wrong at our end!!");
            }
        });
    }
}
