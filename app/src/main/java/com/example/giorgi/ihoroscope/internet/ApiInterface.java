package com.example.giorgi.ihoroscope.internet;

import com.example.giorgi.ihoroscope.model.HoroscopeDetailModelClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by GIorgi on 1/25/2017.
 */

public interface ApiInterface {
    @GET("welcome/gaparsva")
    Call<List<HoroscopeDetailModelClass>> getHoroscopeDetail(@Query("ln") String language);
}
