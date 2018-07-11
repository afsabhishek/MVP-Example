package com.sample.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sample.myapplication.util.Utility;
import com.weatherlibrary.datamodel.WeatherModel;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends AppCompatActivity implements MainView{
    @InjectView(R.id.progress) ProgressBar progressBar;
    @InjectView(R.id.forecast)   CardView cardView;
    @InjectView(R.id.day1)  TextView day1;
    @InjectView(R.id.day2)  TextView day2;
    @InjectView(R.id.day3)  TextView day3;
    @InjectView(R.id.day4)  TextView day4;
    @InjectView(R.id.temp1)  TextView temp1;
    @InjectView(R.id.temp2)  TextView temp2;
    @InjectView(R.id.temp3)  TextView temp3;
    @InjectView(R.id.temp4)  TextView temp4;
    @InjectView(R.id.fail_layout) LinearLayout failLayout;
    @InjectView(R.id.success_layout) LinearLayout passLayout;
    @InjectView(R.id.progressLayout) LinearLayout progressLayout;
    @InjectView(R.id.retry) Button retry;


    private MainPresenter presenter;
    Gson gson = new Gson();
    private GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        showProgressBar();
        gps = new GPSTracker(this);
        presenter = new MainPresenterImpl(this);

        if (gps.canGetLocation()) {
            presenter.fetchData(gps.getLatitude(), gps.getLongitude());
        }
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressLayout.setVisibility(View.VISIBLE);
                passLayout.setVisibility(View.GONE);
                failLayout.setVisibility(View.GONE);
                showProgressBar();
                if (gps.canGetLocation()) {
                    presenter.fetchData(gps.getLatitude(), gps.getLongitude());
                }
            }
        });
    }

    @Override
    public void showProgressBar() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setData(WeatherModel models) {

        if(models != null) {
            progressLayout.setVisibility(View.GONE);
            passLayout.setVisibility(View.VISIBLE);
            failLayout.setVisibility(View.GONE);
            temp1.setText(String.valueOf(models.getForecast().forecastday.get(1).getDay().getAvgtempC()));
            temp2.setText(String.valueOf(models.getForecast().forecastday.get(2).getDay().getAvgtempC()));
            temp3.setText(String.valueOf(models.getForecast().forecastday.get(3).getDay().getAvgtempC()));
            temp4.setText(String.valueOf(models.getForecast().forecastday.get(4).getDay().getAvgtempC()));

            day1.setText(Utility.returnDay(models.getForecast().forecastday.get(1).getDate()));
            day2.setText(Utility.returnDay(models.getForecast().forecastday.get(2).getDate()));
            day3.setText(Utility.returnDay(models.getForecast().forecastday.get(3).getDate()));
            day4.setText(Utility.returnDay(models.getForecast().forecastday.get(4).getDate()));


            cardView.setVisibility(View.VISIBLE);
            Animation bottomUp = AnimationUtils.loadAnimation(MainActivity.this,
                    R.anim.bottom_up);
            cardView.startAnimation(bottomUp);
        }
    }

    @Override
    public void showError(String msg) {
        progressLayout.setVisibility(View.GONE);
        passLayout.setVisibility(View.GONE);
        failLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
