package com.example.giorgi.ihoroscope;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.giorgi.ihoroscope.internet.ConnectivityReceiver;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class LanguageActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    Intent intent;
    String lang;
    private AdView ad;
    public static SharedPreferences pref;
    TextView rite;
    int counter = 0;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        pref = getSharedPreferences("PREFERENCE", MODE_PRIVATE);

        counter = pref.getInt("counter", 0);

        rite = (TextView) findViewById(R.id.rite);


        if (counter == 0) {
            rite.setVisibility(View.INVISIBLE);
        } else rite.setVisibility(View.VISIBLE);

        ad = (AdView) findViewById(R.id.ads);
        counter++;


        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putInt("counter", counter)
                .apply();

        startADS();

        switch (pref.getString("language", "")){
            case "english":
                rite.setText("RATE US!");
                break;
            case "russian":
                rite.setText("Оцените Нас !");
                break;
        }
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
                        .putString("language", lang)
                        .apply();

                startActivity(intent);
                break;
            case R.id.en_id:
                counter++;
                lang = "english";
                intent = new Intent(LanguageActivity.this, DashboardActivity.class);
                // intent.putExtra("language",lang);
                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putBoolean("isFirstRun", false)
                        .putString("language", lang)
                        .apply();

                startActivity(intent);
                break;
            case R.id.rite:

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store")));
                break;
        }
    }
}
