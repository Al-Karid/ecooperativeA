package com.example.grenciss.ecooperative.Binders;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ahamed.multiviewadapter.ItemBinder;
import com.ahamed.multiviewadapter.ItemViewHolder;
import com.example.grenciss.ecooperative.Adapters.Assignation;
import com.example.grenciss.ecooperative.Adapters.AssignationErrorAdapter;
import com.example.grenciss.ecooperative.Adapters.NetError;
import com.example.grenciss.ecooperative.R;
import com.example.grenciss.ecooperative.Utils.AssignationsJsonLoader;

/**
 * Created by grenciss on 1/10/18.
 */

public class ErrorCardBinder extends ItemBinder<NetError, ErrorCardBinder.ErrorCardBinderViewHolder > {
    @Override
    public ErrorCardBinderViewHolder create(LayoutInflater inflater, ViewGroup parent) {
        return new ErrorCardBinderViewHolder(inflater.inflate(R.layout.network_error_card,parent,false));
    }

    @SuppressLint("ResourceType")
    @Override
    public void bind(ErrorCardBinderViewHolder holder, NetError item) {
//        holder.txtName.setText("Oups!!");
        holder.txtName.setText(item.nom);
//        holder.txtTel.setText("Problème de connexion au serveur");
        holder.txtTel.setText(item.tel);
//        holder.txtLocation.setText("Veuillez vérifier votre connexion");
        holder.txtLocation.setText(item.localite);
        holder.btnReload.setId(1);
    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof NetError;
    }

    public class ErrorCardBinderViewHolder extends ItemViewHolder<NetError> {
        protected TextView txtName;
        protected TextView txtTel;
        protected TextView txtLocation;
        protected Button btnReload;

        public ErrorCardBinderViewHolder(View itemView) {
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
//                    new AssignationsJsonLoader(AssignationErrorAdapter.this.context, AssignationErrorAdapter.this.recyclerView ).execute();
                }
            });
        }
    }
}
