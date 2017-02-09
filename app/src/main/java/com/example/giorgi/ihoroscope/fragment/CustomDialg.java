package com.example.giorgi.ihoroscope.fragment;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.giorgi.ihoroscope.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by GIorgi on 1/27/2017.
 */

public class CustomDialg extends DialogFragment {
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        view = inflater.inflate(R.layout.activity_dialog, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.dialog_corner);

        String lan = getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getString("language", "");

        switch (lan) {
            case "russian":
                TextView title_ru = (TextView) view.findViewById(R.id.title);
                title_ru.setText("Ошибка!");
                TextView descriptionText_ru = (TextView) view.findViewById(R.id.description_text);
                descriptionText_ru.setText("Пожалуйста, Проверьте Соединения С Интернетом Согласен");
                break;
            case "english":
                TextView title_eng = (TextView) view.findViewById(R.id.title);
                title_eng.setText("Error!");
                TextView descriptionText_eng = (TextView) view.findViewById(R.id.description_text);
                descriptionText_eng.setText("Please Check Internet Connection Accept");
                break;
        }

        TextView button = (TextView) view.findViewById(R.id.setting_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
            }
        });
        return view;
    }
}
