package com.example.grenciss.ecooperative.Binders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ahamed.multiviewadapter.ItemBinder;
import com.ahamed.multiviewadapter.ItemViewHolder;
import com.example.grenciss.ecooperative.Adapters.Assignation;
import com.example.grenciss.ecooperative.R;

/**
 * Created by grenciss on 1/10/18.
 */

public class AssignationCardBinder extends ItemBinder<Assignation,AssignationCardBinder.AssignationCardBinderVH> {

    Context context;

    AssignationCardBinder(Context context)
    {
        this.context = context;
    }

    @Override
    public AssignationCardBinderVH create(LayoutInflater inflater, ViewGroup parent) {
        return new AssignationCardBinderVH(inflater.inflate(R.layout.assignation_item, parent, false));
    }

    @Override
    public void bind(AssignationCardBinderVH holder, Assignation item) {
        holder.txtName.setText(item.nom);
        holder.txtTel.setText(item.tel);
        holder.txtLocation.setText(item.localite);
        holder.btnCall.setId(item.id);
//        holder.btnBuy.setId(item.id);
    }

    @Override
    public boolean canBindData(Object item) {
        return item instanceof Assignation;
    }

    public class AssignationCardBinderVH extends ItemViewHolder<Assignation> {
        protected TextView txtName;
        protected TextView txtTel;
        protected TextView txtLocation;
        protected Button btnCall;
        protected Button btnBuy;

        public AssignationCardBinderVH(View itemView) {
            super(itemView);
            txtName = (TextView)itemView.findViewById(R.id.txtName);
            txtTel = (TextView)itemView.findViewById(R.id.txtTel);
            txtLocation = (TextView)itemView.findViewById(R.id.txtLocation);
            btnCall = (Button)itemView.findViewById(R.id.btnReload);
            btnBuy = (Button)itemView.findViewById(R.id.btnBuy);

            btnBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Erreur de connextion au serveur", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
