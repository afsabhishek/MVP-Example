package com.sample.myapplication;

/**
 * Created by Abhishek.Kumar on 2/2/2017.
 */

public interface MainPresenter {
    void fetchData(double latitude, double longitude);
    void onDestroy();

}
