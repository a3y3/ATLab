package com.example.soham.clapooftask1;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Soham on 02-May-17.
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder>{
    private List<LocationHolder> locationList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView location;
        public MyViewHolder(View view){
            super(view);
            this.location = (TextView)view.findViewById(R.id.location_recycler_view);
        }
    }

    public LocationAdapter(List<LocationHolder> locationList){
        this.locationList = locationList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position){
        LocationHolder locationHolder = locationList.get(position);
        myViewHolder.location.setText(locationHolder.getLatitude()+", "+locationHolder.getLongitude());
    }

    @Override
    public int getItemCount(){
        return locationList.size();
    }

}
