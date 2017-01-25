package com.example.giorgi.ihoroscope;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.giorgi.ihoroscope.internet.ApiClient;
import com.example.giorgi.ihoroscope.internet.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private final String language="georgian";
    int positio;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            positio = extras.getInt("position");
            // and get whatever type user account id is
        }

        makeRequset();
    }

    private void makeRequset() {
        ApiInterface anInterface= ApiClient.getClient()
                .create(ApiInterface.class);
        Call call=anInterface.getHoroscopeDetail(language);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List list= (List) response.body();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
