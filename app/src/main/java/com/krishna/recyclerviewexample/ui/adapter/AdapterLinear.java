package com.krishna.recyclerviewexample.ui.adapter;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.krishna.recyclerviewexample.R;
import com.krishna.recyclerviewexample.databinding.ItemHorRecBinding;
import com.krishna.recyclerviewexample.databinding.ItemLinearRecBinding;
import com.krishna.recyclerviewexample.model.DataModel;

import java.util.List;

//public class AdapterLinear extends RecyclerView.Adapter<AdapterLinear.MyViewHolder> {
public class AdapterLinear extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<DataModel> dataModelList;
    Activity activity;
    boolean isHor;

    public AdapterLinear(List<DataModel> dataModelList, Activity activity, boolean isHor) {
        this.dataModelList = dataModelList;
        this.activity = activity;
        this.isHor = isHor;
    }

//    @NonNull
//    @Override
//    public AdapterLinear.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        ItemLinearRecBinding itemLinearRecBinding = ItemLinearRecBinding.inflate(this.activity.getLayoutInflater(), parent, false);
//        return new MyViewHolder(itemLinearRecBinding);
//    }
//


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (!isHor) {
            ItemLinearRecBinding itemLinearRecBinding = ItemLinearRecBinding.inflate(this.activity.getLayoutInflater(), parent, false);
            return new MyViewHolder(itemLinearRecBinding);
        } else {
            ItemHorRecBinding itemLinearRecBinding = ItemHorRecBinding.inflate(this.activity.getLayoutInflater(), parent, false);
            return new MyHorViewHolder(itemLinearRecBinding);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
//
            DataModel dataModel = this.dataModelList.get(position);
            ((MyViewHolder) holder).binding.txtTitle.setText(dataModel.getTitle());
            ((MyViewHolder) holder).binding.txtDescription.setText(dataModel.getDescription());
            Glide.with(activity).load(Uri.parse(dataModel.getImageUrl()))
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.progress_animation)
                    )
                    .into(((MyViewHolder) holder).binding.imgView);

        } else {
            DataModel dataModel = this.dataModelList.get(position);
            ((MyHorViewHolder) holder).binding.txtTitle.setText(dataModel.getTitle());
            ((MyHorViewHolder) holder).binding.txtDescription.setText(dataModel.getDescription());
            Glide.with(activity).load(Uri.parse(dataModel.getImageUrl()))
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.progress_animation)
                    )
                    .into(((MyHorViewHolder) holder).binding.imgView);

        }
    }

//    @Override
//    public void onBindViewHolder(@NonNull AdapterLinear.MyViewHolder holder, int position) {
//
//        DataModel dataModel = this.dataModelList.get(position);
//        holder.binding.txtTitle.setText(dataModel.getTitle());
//        holder.binding.txtDescription.setText(dataModel.getDescription());
//        Glide.with(activity).load(Uri.parse(dataModel.getImageUrl())).into(holder.binding.imgView);
//
//    }

    @Override
    public int getItemCount() {
        return this.dataModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemLinearRecBinding binding;

        public MyViewHolder(@NonNull ItemLinearRecBinding itemLinearRecBinding) {
            super(itemLinearRecBinding.getRoot());
            binding = itemLinearRecBinding;
        }
    }

    public class MyHorViewHolder extends RecyclerView.ViewHolder {
        ItemHorRecBinding binding;

        public MyHorViewHolder(@NonNull ItemHorRecBinding itemLinearRecBinding) {
            super(itemLinearRecBinding.getRoot());
            binding = itemLinearRecBinding;
        }
    }
}
