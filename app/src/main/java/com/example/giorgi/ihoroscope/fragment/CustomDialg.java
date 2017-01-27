package com.example.giorgi.ihoroscope.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.giorgi.ihoroscope.R;

/**
 * Created by GIorgi on 1/27/2017.
 */

public class CustomDialg extends DialogFragment {
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        View view=inflater.inflate(R.layout.activity_dialog, container, false);
        Button button= (Button) view.findViewById(R.id.setting_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
                //getDialog().dismiss();
            }
        });
        return view;
    }

    //    public CustomDialg(Context context) {
//        super(context);
//        this.context=context;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dialog);
//
//        TextView title=new TextView(context);
//        title.setGravity(Gravity.CENTER_HORIZONTAL);
//        title.setText("ინტერნეტი");
//        getWindow().setTitle("ინტერნეტი");
//    }
}
