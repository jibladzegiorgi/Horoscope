package com.example.giorgi.ihoroscope;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.giorgi.ihoroscope.customview.CustomAdapter;
import com.example.giorgi.ihoroscope.fragment.CustomDialg;
import com.example.giorgi.ihoroscope.internet.ApiClient;
import com.example.giorgi.ihoroscope.internet.ApiInterface;
import com.example.giorgi.ihoroscope.internet.ConnectivityReceiver;
import com.example.giorgi.ihoroscope.internet.MyApplication;
import com.example.giorgi.ihoroscope.model.HoroscopeDetailModelClass;
import com.example.giorgi.ihoroscope.model.HoroscopeModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    RecyclerView horoscopeRecyclerView;
    CustomAdapter adapter;
    private String language;
    List internet;
    public static List<HoroscopeModel> modelList;
    public static List list;
    AdView ad;
    AlertDialog.Builder alertDialog;
    CustomDialg customDialg;
    private ProgressDialog progressDialog;
    ImageView languageImage;
    SharedPreferences pref;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        pref = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        language = pref.getString("language", "");

        ad = (AdView) findViewById(R.id.adreq);

        list = new ArrayList();

        progressDialog = new ProgressDialog(this);

        customDialg = new CustomDialg();

        internet = new ArrayList();

        fillList();

        languageImage = (ImageView) findViewById(R.id.language_id);
        horoscopeRecyclerView = (RecyclerView) findViewById(R.id.horoscope_view);
        adapter = new CustomAdapter(modelList, this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        horoscopeRecyclerView.setLayoutManager(mLayoutManager);

        MyApplication.getInstance().setConnectivityListener(this);

        horoscopeRecyclerView.setAdapter(adapter);

        languageImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, LanguageActivity.class);
                getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .edit()
                        .putBoolean("isFirstRun", true)
                        .apply();
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkConnection();
//        if (choseLanguage != null) {
//            if (choseLanguage == "ru") {
//                fillRUList();
//                adapter.swapItems(modelList);
//            }
//        }
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected) {
            checkConnection();
            customDialg.dismiss();
        }
        startADS();
    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (list.size() == 0) {
            if (isConnected) {
                //alertDialog = null;
                startADS();
                makeRequset();
            } else {
//                alertDialog = new AlertDialog.Builder(this);
//                alertDialog.setTitle("arsdas")
//                        .setCancelable(false)
//                        .setMessage("interneti ar aris")
//                        .setPositiveButton("Setting", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                startActivity(new Intent(Settings.ACTION_SETTINGS));
//
//                            }
//                        })
//                        .show();

//                customDialg = null;
//                customDialg = new CustomDialg();
                if (!customDialg.isAdded()) {
                    customDialg.show(getFragmentManager(), "dialog_fragment");
                }
            }
        }
    }

    private void startADS() {
        AdRequest adRequest = new AdRequest.Builder().build();
        ad.loadAd(adRequest);
    }


    private void fillList() {
        fillGEOList();
        switch (language) {
            case "georgian":
                progressDialog.setMessage(getResources().getString(R.string.load));
                fillGEOList();
                break;
            case "russian":
                progressDialog.setMessage("подождите");
                fillRUList();
                break;
            case "english":
                progressDialog.setMessage("Loading");
                fillENList();
                break;
        }

    }

    private void fillGEOList() {
        modelList = new ArrayList<>();
        HoroscopeModel model_1 = new HoroscopeModel("ვერძი", getResources().getIdentifier("aries", "drawable", this.getPackageName()), "მარ 21-აპრ 19");
        HoroscopeModel model_2 = new HoroscopeModel("კურო", getResources().getIdentifier("taurus", "drawable", this.getPackageName()), "აპრ 20-მაი 20");
        HoroscopeModel model_3 = new HoroscopeModel("ტყუპები", getResources().getIdentifier("gemini", "drawable", this.getPackageName()), "მაი 21-ივნ 21");
        HoroscopeModel model_4 = new HoroscopeModel("კირჩხიბი", getResources().getIdentifier("cancer", "drawable", this.getPackageName()), "ივნ 22-ივლ 22");
        HoroscopeModel model_5 = new HoroscopeModel("ლომი", getResources().getIdentifier("leo", "drawable", this.getPackageName()), "ივლ 23-აგვ 22");
        HoroscopeModel model_6 = new HoroscopeModel("ქალწული", getResources().getIdentifier("virgo", "drawable", this.getPackageName()), "აგვ 23-სექ 22");
        HoroscopeModel model_7 = new HoroscopeModel("სასწორი", getResources().getIdentifier("libra", "drawable", this.getPackageName()), "სექ 23-ოქტ 22");
        HoroscopeModel model_8 = new HoroscopeModel("მორიელი", getResources().getIdentifier("scorpio", "drawable", this.getPackageName()), "ოქტ 23-ნოე 21");
        HoroscopeModel model_9 = new HoroscopeModel("მშვილდოსანი", getResources().getIdentifier("sagitarius", "drawable", this.getPackageName()), "ნოe 22-დეკ 21");
        HoroscopeModel model_10 = new HoroscopeModel("თხის რქა", getResources().getIdentifier("capricorn", "drawable", this.getPackageName()), "დეკ 22-იან 19");
        HoroscopeModel model_11 = new HoroscopeModel("მერწყური", getResources().getIdentifier("aquarius", "drawable", this.getPackageName()), "იან 20-თებ 18");
        HoroscopeModel model_12 = new HoroscopeModel("თევზები", getResources().getIdentifier("pisces", "drawable", this.getPackageName()), "თებ 19-მარ 20");
        modelList.add(model_1);
        modelList.add(model_2);
        modelList.add(model_3);
        modelList.add(model_4);
        modelList.add(model_5);
        modelList.add(model_6);
        modelList.add(model_7);
        modelList.add(model_8);
        modelList.add(model_9);
        modelList.add(model_10);
        modelList.add(model_11);
        modelList.add(model_12);
    }

    private void fillRUList() {
        modelList = new ArrayList<>();
        HoroscopeModel model_1 = new HoroscopeModel("Овен", getResources().getIdentifier("aries", "drawable", this.getPackageName()), "Мар  21-Апр 19");
        HoroscopeModel model_2 = new HoroscopeModel("Телец", getResources().getIdentifier("taurus", "drawable", this.getPackageName()), "Апр 20-Мая 20");
        HoroscopeModel model_3 = new HoroscopeModel("Близнецы", getResources().getIdentifier("gemini", "drawable", this.getPackageName()), "Мая 21-Июн 21");
        HoroscopeModel model_4 = new HoroscopeModel("Рак", getResources().getIdentifier("cancer", "drawable", this.getPackageName()), "Июн 22-Июл 22");
        HoroscopeModel model_5 = new HoroscopeModel("Лев", getResources().getIdentifier("leo", "drawable", this.getPackageName()), "Июл 23-Авг 22");
        HoroscopeModel model_6 = new HoroscopeModel("Дева", getResources().getIdentifier("virgo", "drawable", this.getPackageName()), "Авг 23-Сен 22");
        HoroscopeModel model_7 = new HoroscopeModel("Весы", getResources().getIdentifier("libra", "drawable", this.getPackageName()), "Сен 23-Окт 22");
        HoroscopeModel model_8 = new HoroscopeModel("Скорпион", getResources().getIdentifier("scorpio", "drawable", this.getPackageName()), "Окт 23-Ноя 21");
        HoroscopeModel model_9 = new HoroscopeModel("Стрелец", getResources().getIdentifier("sagitarius", "drawable", this.getPackageName()), "Ноя 22-Дек 21");
        HoroscopeModel model_10 = new HoroscopeModel("Козерог", getResources().getIdentifier("capricorn", "drawable", this.getPackageName()), "Дек 22-Янв 19");
        HoroscopeModel model_11 = new HoroscopeModel("Водолей", getResources().getIdentifier("aquarius", "drawable", this.getPackageName()), "Янв 20-Фев 18");
        HoroscopeModel model_12 = new HoroscopeModel("Рыбы", getResources().getIdentifier("pisces", "drawable", this.getPackageName()), "Фев 19-Мар 20");
        modelList.add(model_1);
        modelList.add(model_2);
        modelList.add(model_3);
        modelList.add(model_4);
        modelList.add(model_5);
        modelList.add(model_6);
        modelList.add(model_7);
        modelList.add(model_8);
        modelList.add(model_9);
        modelList.add(model_10);
        modelList.add(model_11);
        modelList.add(model_12);
    }

    private void fillENList() {
        modelList = new ArrayList<>();
        HoroscopeModel model_1 = new HoroscopeModel("Aries", getResources().getIdentifier("aries", "drawable", this.getPackageName()), "Mar  21-Apr 19");
        HoroscopeModel model_2 = new HoroscopeModel("taurus", getResources().getIdentifier("taurus", "drawable", this.getPackageName()), "Apr 20-May 20");
        HoroscopeModel model_3 = new HoroscopeModel("gemini", getResources().getIdentifier("gemini", "drawable", this.getPackageName()), "May 21-Jun 21");
        HoroscopeModel model_4 = new HoroscopeModel("cancer", getResources().getIdentifier("cancer", "drawable", this.getPackageName()), "Jun 22-Jul 22");
        HoroscopeModel model_5 = new HoroscopeModel("leo", getResources().getIdentifier("leo", "drawable", this.getPackageName()), "Jul 23-Aug 22");
        HoroscopeModel model_6 = new HoroscopeModel("virgo", getResources().getIdentifier("virgo", "drawable", this.getPackageName()), "Aug 23-Sep 22");
        HoroscopeModel model_7 = new HoroscopeModel("libra", getResources().getIdentifier("libra", "drawable", this.getPackageName()), "Sep 23-Oct 22");
        HoroscopeModel model_8 = new HoroscopeModel("scorpio", getResources().getIdentifier("scorpio", "drawable", this.getPackageName()), "Oct 23-Nov 21");
        HoroscopeModel model_9 = new HoroscopeModel("sagitarius", getResources().getIdentifier("sagitarius", "drawable", this.getPackageName()), "Nov 22-Dec 21");
        HoroscopeModel model_10 = new HoroscopeModel("capricorn", getResources().getIdentifier("capricorn", "drawable", this.getPackageName()), "Dec 22-Jan 19");
        HoroscopeModel model_11 = new HoroscopeModel("aquarius", getResources().getIdentifier("aquarius", "drawable", this.getPackageName()), "Jan 20-Feb 18");
        HoroscopeModel model_12 = new HoroscopeModel("pisces", getResources().getIdentifier("pisces", "drawable", this.getPackageName()), "Feb 19-Mar 20");
        modelList.add(model_1);
        modelList.add(model_2);
        modelList.add(model_3);
        modelList.add(model_4);
        modelList.add(model_5);
        modelList.add(model_6);
        modelList.add(model_7);
        modelList.add(model_8);
        modelList.add(model_9);
        modelList.add(model_10);
        modelList.add(model_11);
        modelList.add(model_12);
    }


    private void makeRequset() {
        if (list.size() == 0) {
            if (customDialg.isAdded()) {
                customDialg.dismiss();
            }
            progressDialog.show();
            progressDialog.setCancelable(false);
            ApiInterface anInterface = ApiClient.getClient()
                    .create(ApiInterface.class);
            Call<List<HoroscopeDetailModelClass>> call = anInterface.getHoroscopeDetail(language);
            call.enqueue(new Callback<List<HoroscopeDetailModelClass>>() {
                @Override
                public void onResponse(Call<List<HoroscopeDetailModelClass>> call, Response<List<HoroscopeDetailModelClass>> response) {
                    list = response.body();
                    progressDialog.setCancelable(true);
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<List<HoroscopeDetailModelClass>> call, Throwable t) {
//                    getSharedPreferences("PREFERENCE", MODE_PRIVATE)
//                            .edit()
//                              .putBoolean("isFirstRun", true)
//                            .apply();
//                    progressDialog.setMessage("სერვერის შეცდომა");
//                    Intent intent = new Intent(DashboardActivity.this, LanguageActivity.class);
//                    startActivity(intent);
                }
            });
        }
    }
}
