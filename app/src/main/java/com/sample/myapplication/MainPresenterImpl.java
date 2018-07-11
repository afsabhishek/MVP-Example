package com.sample.myapplication;

import com.weatherlibrary.datamodel.WeatherModel;

/**
 * Created by Abhishek.Kumar on 12/6/2018.
 */

public class MainPresenterImpl implements MainPresenter, MainInteractor.onNetworkCallFinishListener{

    MainView mMainView;
    MainInteractor mainInteractor;

    public MainPresenterImpl(MainView mainView){
        mMainView = mainView;
        mainInteractor = new MainInteractorImpl();
    }

    @Override
    public void fetchData(double latitude, double longitude) {
        if(mMainView != null){
            mMainView.showProgressBar();
        }
        mainInteractor.fetchDataFromNetwork(this,String.valueOf(latitude),String.valueOf(longitude));
    }

    @Override
    public void onDestroy() {
        mMainView = null;
    }

    @Override
    public void onSuccess(WeatherModel weatherModel) {
        if(mMainView != null){
            mMainView.hideProgressBar();
            mMainView.setData(weatherModel);
        }
    }

    @Override
    public void onError(String msg) {
        if(mMainView != null){
            mMainView.hideProgressBar();
            mMainView.showError(msg);
        }
    }
}
