package com.example.giorgi.ihoroscope.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by GIorgi on 1/25/2017.
 */

public class HoroscopeDetailModelClass {
    @SerializedName("name")
    @Expose
    private String horoscopeDetail;

    public String getHoroscopeDetail() {
        return horoscopeDetail;
    }

    public void setHoroscopeDetail(String horoscopeDetail) {
        this.horoscopeDetail = horoscopeDetail;
    }
}
