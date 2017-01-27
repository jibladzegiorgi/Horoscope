package com.example.giorgi.ihoroscope;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.giorgi.ihoroscope.internet.ConnectivityReceiver;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    Intent intent;
    public static String lang;
    private AdView ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        ad = (AdView) findViewById(R.id.ads);
        startADS();
    }


    private void startADS() {
        AdRequest adRequest = new AdRequest.Builder().build();
        ad.loadAd(adRequest);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        startADS();
    }

    public void choseLanguage(View view) {
        switch (view.getId()) {
            case R.id.geo_id:

                break;
            case R.id.ru_id:
                DashboardActivity.choseLanguage="ru";
                finish();
                break;
            case R.id.en_id:
                break;
        }
    }
}