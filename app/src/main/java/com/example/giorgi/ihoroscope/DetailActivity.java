package com.example.giorgi.ihoroscope;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giorgi.ihoroscope.internet.ApiClient;
import com.example.giorgi.ihoroscope.internet.ApiInterface;
import com.example.giorgi.ihoroscope.model.HoroscopeDetailModelClass;
import com.example.giorgi.ihoroscope.model.HoroscopeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    TextView detailHoroscopeName,detailText;
    ImageView detailImage;
    int positio;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);

        detailHoroscopeName = (TextView) findViewById(R.id.detail_horoscope_name_id);
        detailText= (TextView) findViewById(R.id.detail_description_id);
        detailImage= (ImageView) findViewById(R.id.detail_image_id);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            positio = extras.getInt("position");
            // and get whatever type user account id is
        }

        HoroscopeDetailModelClass detailModelClass= (HoroscopeDetailModelClass) DashboardActivity.list.get(positio);
        HoroscopeModel modelClass =DashboardActivity.modelList.get(positio);
        detailHoroscopeName.setText(modelClass.getName());
        detailImage.setImageResource(modelClass.getImage());
        detailText.setText(detailModelClass.getHoroscopeDetail().trim());

    }
}
