package com.example.grenciss.ecooperative.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.example.grenciss.ecooperative.R;

import java.io.IOException;

/**
 * Created by grenciss on 1/9/18.
 */

public class LoadingAdapter extends RecyclerView.Adapter<LoadingAdapter.LoadingAdapterViewHolder> {

    public Context context;

    public LoadingAdapter(Context context)
    {
        this.context = context;
    }

    @Override
    public LoadingAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading, parent, false);
        return new LoadingAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LoadingAdapterViewHolder holder, int position) {
        holder.loading.setAnimation("loading.json");
        holder.loading.loop(true);
        holder.loading.playAnimation();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class LoadingAdapterViewHolder extends RecyclerView.ViewHolder {

        public LottieAnimationView loading;

        public LoadingAdapterViewHolder(View itemView) {
            super(itemView);
            loading = (LottieAnimationView)itemView.findViewById(R.id.animation_view);
        }
    }
}
