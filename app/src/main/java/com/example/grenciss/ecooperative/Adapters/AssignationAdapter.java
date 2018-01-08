package com.example.grenciss.ecooperative.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grenciss.ecooperative.AcheterActivity;
import com.example.grenciss.ecooperative.R;

import java.util.List;

/**
 * Created by grenciss on 1/8/18.
 */

public class AssignationAdapter extends RecyclerView.Adapter<AssignationAdapter.AssignationViewHolder> {
    private List<Assignation> assignationList;
    private Context context;

    public AssignationAdapter(Context context, List<Assignation> assignationList)
    {
        this.assignationList = assignationList;
        this.context = context;
    }

    @Override
    public AssignationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignation_item, parent, false);
        return new AssignationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AssignationViewHolder holder, int position) {
        Assignation assignation = assignationList.get(position);
        holder.txtName.setText(assignation.nom);
        holder.txtTel.setText(assignation.tel);
        holder.txtLocation.setText(assignation.localite);
        holder.btnCall.setId(assignation.id);
        holder.btnBuy.setId(assignation.id);
    }

    @Override
    public int getItemCount() {
        return this.assignationList.size();
    }

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

            btnCall.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),String.valueOf(assignationList.get(getAdapterPosition()).tel),Toast.LENGTH_SHORT).show();
                }
            });

            btnBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent I = new Intent(AssignationAdapter.this.context, AcheterActivity.class);
                    AssignationAdapter.this.context.startActivity(I);
                }
            });
        }
    }
}
