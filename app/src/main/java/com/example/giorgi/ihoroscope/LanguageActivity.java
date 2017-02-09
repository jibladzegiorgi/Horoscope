package com.example.giorgi.ihoroscope;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.giorgi.ihoroscope.internet.ConnectivityReceiver;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

public class LanguageActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    Intent intent;
    String lang;
    private AdView ad;
    public static SharedPreferences pref;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        pref = getSharedPreferences("PREFERENCE", MODE_PRIVATE);

        ad = (AdView) findViewById(R.id.ads);
        startADS();
    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean isFirstRun = pref.getBoolean("isFirstRun", true);
        if (!isFirstRun) {

            startActivity(new Intent(this, DashboardActivity.class));

            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("isFirstRun", false)
                    .apply();
        }

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
                lang = "georgian";
                intent = new Intent(LanguageActivity.this, DashboardActivity.class);
                // intent.putExtra("language",lang);
                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putBoolean("isFirstRun", false)
                        .apply();

                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putString("language", lang)
                        .apply();
                startActivity(intent);
                break;
            case R.id.ru_id:
                lang = "russian";
                intent = new Intent(LanguageActivity.this, DashboardActivity.class);
                //intent.putExtra("language",lang);
                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putBoolean("isFirstRun", false)
                        .apply();

                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putString("language", lang)
                        .apply();
                startActivity(intent);
                break;
            case R.id.en_id:
                lang = "english";
                intent = new Intent(LanguageActivity.this, DashboardActivity.class);
                // intent.putExtra("language",lang);
                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putBoolean("isFirstRun", false)
                        .apply();

                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putString("language", lang)
                        .apply();
                startActivity(intent);
                break;
        }
    }
}
