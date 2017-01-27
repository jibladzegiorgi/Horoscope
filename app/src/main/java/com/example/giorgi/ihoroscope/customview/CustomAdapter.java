package com.example.giorgi.ihoroscope.customview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giorgi.ihoroscope.DetailActivity;
import com.example.giorgi.ihoroscope.R;
import com.example.giorgi.ihoroscope.model.HoroscopeModel;

import java.util.List;

/**
 * Created by GIorgi on 1/25/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private List<HoroscopeModel> horoscopeModelList;
    private Context context;

    public CustomAdapter(List<HoroscopeModel> horoscopeModelList, Context context) {
        this.horoscopeModelList = horoscopeModelList;
        this.context = context;
    }

    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.MyViewHolder holder, final int position) {
        final HoroscopeModel model=horoscopeModelList.get(position);
        holder.horoscopeImage.setImageResource(model.getImage());
        holder.horoscopeDate.setText(model.getDate());
        holder.horoscopeName.setText(model.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return horoscopeModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView horoscopeImage;
        TextView horoscopeName,horoscopeDate;
        public MyViewHolder(View itemView) {
            super(itemView);
            horoscopeImage= (ImageView) itemView.findViewById(R.id.horoscope_image);
            horoscopeName= (TextView) itemView.findViewById(R.id.horoscope_name);
            horoscopeDate= (TextView) itemView.findViewById(R.id.horoscope_date);
        }
    }
    public void swapItems(List<HoroscopeModel> items) {
        this.horoscopeModelList = items;
        notifyDataSetChanged();
    }
}
