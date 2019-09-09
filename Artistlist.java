package com.example.prasannakumar.fireapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Artistlist extends ArrayAdapter<Artist>{

    private Activity context;
    private List<Artist> artistList;

    public Artistlist(Activity context, List<Artist> artistList){
        super(context,R.layout.list_layout,artistList);
        this.context = context;
        this.artistList = artistList;
    }



    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView textViewPlate_no = (TextView) listViewItem.findViewById(R.id.textViewPlateno);
        TextView textViewAmount_In_Rs = (TextView) listViewItem.findViewById(R.id.textViewamount);

        Artist artist = artistList.get(position);

        textViewPlate_no.setText(artist.getPlateNo());
        textViewAmount_In_Rs.setText(artist.getAmount());

        return listViewItem;
    }
}
