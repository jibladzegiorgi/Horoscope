package com.example.giorgi.ihoroscope.model;

import android.widget.ImageView;

/**
 * Created by GIorgi on 1/25/2017.
 */

public class HoroscopeModel {
//    @SerializedName("name")
//    @Expose
    private String name;
    private int image;
    private String date;

    public HoroscopeModel(String name, int image, String date) {
        this.name = name;
        this.image = image;
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
