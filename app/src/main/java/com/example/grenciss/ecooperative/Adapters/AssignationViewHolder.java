package com.example.grenciss.ecooperative.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.grenciss.ecooperative.R;

/**
 * Created by grenciss on 1/8/18.
 */

public class AssignationViewHolder extends RecyclerView.ViewHolder {
    protected TextView txtName;
    protected TextView txtTel;
    protected TextView txtLocation;
    protected Button btnCall;
    protected Button btnBuy;
    public AssignationViewHolder(View itemView) {
        super(itemView);
        txtName = (TextView)itemView.findViewById(R.id.txtName);
        txtTel = (TextView)itemView.findViewById(R.id.txtTel);
        txtLocation = (TextView)itemView.findViewById(R.id.txtLocation);
        btnCall = (Button)itemView.findViewById(R.id.btnCall);
        btnBuy = (Button)itemView.findViewById(R.id.btnBuy);
    }
}
