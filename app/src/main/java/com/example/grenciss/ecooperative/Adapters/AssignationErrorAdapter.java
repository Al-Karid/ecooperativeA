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
import com.example.grenciss.ecooperative.Utils.AssignationsJsonLoader;

import java.util.HashMap;
import java.util.List;

/**
 * Created by grenciss on 1/8/18.
 */

public class AssignationErrorAdapter extends RecyclerView.Adapter<AssignationErrorAdapter.AssignationErrorViewHolder> {
    private List<Assignation> assignationList;
//    private HashMap<Integer,Integer>ids = new HashMap<>();
    private Context context;
    private RecyclerView recyclerView;

    public AssignationErrorAdapter(Context context, RecyclerView recyclerView)
    {
//        this.assignationList = assignationList;
        this.recyclerView = recyclerView;
        this.context = context;
    }

    @Override
    public AssignationErrorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.network_error_card, parent, false);
        return new AssignationErrorViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(AssignationErrorViewHolder holder, int position) {
//        Assignation assignation = assignationList.get(position);
//        ids.put(position,assignation.id);
        holder.txtName.setText("Oups!!");
        holder.txtTel.setText("Problème de connexion au serveur");
        holder.txtLocation.setText("Veuillez vérifier votre connexion");
        holder.btnReload.setId(1);
    }

    @Override
    public int getItemCount() {
//        return this.assignationList.size();
        return 1;
    }

    public class AssignationErrorViewHolder extends RecyclerView.ViewHolder {

        protected TextView txtName;
        protected TextView txtTel;
        protected TextView txtLocation;
        protected Button btnReload;

        public AssignationErrorViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView)itemView.findViewById(R.id.txtName);
            txtTel = (TextView)itemView.findViewById(R.id.txtTel);
            txtLocation = (TextView)itemView.findViewById(R.id.txtLocation);
            btnReload = (Button)itemView.findViewById(R.id.btnReload);

            btnReload.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View view) {
//                    int id = ids.get(getAdapterPosition());
//                    String nom = assignationList.get(getAdapterPosition()).nom;
//                    Toast.makeText(view.getContext(),String.valueOf(assignationList.get(getAdapterPosition()).tel),Toast.LENGTH_SHORT).show();
//                    Toast.makeText(view.getContext(),String.valueOf(nom),Toast.LENGTH_SHORT).show();
                    new AssignationsJsonLoader(AssignationErrorAdapter.this.context, AssignationErrorAdapter.this.recyclerView ).execute();
                }
            });

        }
    }
}
