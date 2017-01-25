package com.example.giorgi.ihoroscope;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.giorgi.ihoroscope.customview.CustomAdapter;
import com.example.giorgi.ihoroscope.internet.ApiClient;
import com.example.giorgi.ihoroscope.internet.ApiInterface;
import com.example.giorgi.ihoroscope.model.HoroscopeModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    RecyclerView horoscopeRecyclerView;
    CustomAdapter adapter;
   public static List<HoroscopeModel> modelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        fillList();

        horoscopeRecyclerView = (RecyclerView) findViewById(R.id.horoscope_view);
        adapter = new CustomAdapter(modelList, this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        horoscopeRecyclerView.setLayoutManager(mLayoutManager);
        horoscopeRecyclerView.setAdapter(adapter);
    }


    private void fillList() {
        modelList = new ArrayList<>();
        HoroscopeModel model_1 = new HoroscopeModel("ვერძი", getResources().getIdentifier("aries", "drawable", this.getPackageName()), "მარ 21-აპრ 19");
        HoroscopeModel model_2 = new HoroscopeModel("კურო", getResources().getIdentifier("taurus", "drawable", this.getPackageName()), "აპრ 20-მაი 20");
        HoroscopeModel model_3 = new HoroscopeModel("ტყუპები", getResources().getIdentifier("gemini", "drawable", this.getPackageName()), "მაი 21-ივნ 21");
        HoroscopeModel model_4 = new HoroscopeModel("კირჩხიბი", getResources().getIdentifier("cancer", "drawable", this.getPackageName()), "ივნ 22-ივლ 22");
        HoroscopeModel model_5 = new HoroscopeModel("ლომი", getResources().getIdentifier("leo", "drawable", this.getPackageName()), "ივლ 23-აგვ 22");
        HoroscopeModel model_6 = new HoroscopeModel("ქალწული", getResources().getIdentifier("virgo", "drawable", this.getPackageName()), "აგვ 23-სექ 22");
        HoroscopeModel model_7 = new HoroscopeModel("სასწორი", getResources().getIdentifier("libra", "drawable", this.getPackageName()), "სექ 23-ოქტ 22");
        HoroscopeModel model_8 = new HoroscopeModel("მორიელი", getResources().getIdentifier("scorpio", "drawable", this.getPackageName()), "ოქტ 23-ნოე 21");
        HoroscopeModel model_9 = new HoroscopeModel("მშვილდოსანი", getResources().getIdentifier("sagitarius", "drawable", this.getPackageName()), "ნოწ 22-დეკ 21");
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
}
