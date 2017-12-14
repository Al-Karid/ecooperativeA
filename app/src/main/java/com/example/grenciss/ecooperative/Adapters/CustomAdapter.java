package com.example.grenciss.ecooperative.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.grenciss.ecooperative.R;

import java.util.Arrays;


/**
 * Created by HP on 14/11/2017.
 */

public class CustomAdapter extends BaseAdapter {

//    String[] fullnames, notes;
    private String[] fullname = {"Sah Dier", "Asne Bio", "Restu Sdik","Sah Dier", "Asne Bio", "Restu Sdik","Sah Dier", "Asne Bio", "Restu Sdik","Sah Dier", "Asne Bio", "Restu Sdik"};
    private String[] note = {"25654128","85452136","96321452","25654128","85452136","96321452","25654128","85452136","96321452","25654128","85452136","96321452"};
    private String[] location = {"Dioer","Sirre","Brigf","Dioer","Sirre","Brigf","Dioer","Sirre","Brigf","Dioer","Sirre","Brigf"};
    private static LayoutInflater inflater = null;

//    public CustomAdapter(Context ma, String[] txt1, String[] txt2){
    public CustomAdapter(Context ma){
        inflater = (LayoutInflater) ma.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return fullname.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder{
        TextView txt1;
        TextView txt2;
        TextView txt3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rv;
             rv = inflater.inflate(R.layout.lstass_item, null);
             holder.txt1 = (TextView) rv.findViewById(R.id.txtName);
             holder.txt2 = (TextView) rv.findViewById(R.id.badge);
             holder.txt3 = (TextView) rv.findViewById(R.id.txtLocation);
        holder.txt1.setText(fullname[position]);
        holder.txt2.setText(note[position]);
        holder.txt3.setText(location[position]);

        return rv;
    }
}
