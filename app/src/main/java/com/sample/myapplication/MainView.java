package com.sample.myapplication;

import com.weatherlibrary.datamodel.WeatherModel;

/**
 * Created by Abhishek.Kumar on 2/2/2017.
 */

public interface MainView {
    void showProgressBar();
    void hideProgressBar();
    void setData(WeatherModel weatherModel);
    void showError(String msg);
}
