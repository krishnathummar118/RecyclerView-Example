package com.krishna.recyclerviewexample.ui.adapter;

import android.app.Activity;
import android.net.Uri;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.krishna.recyclerviewexample.R;
import com.krishna.recyclerviewexample.databinding.ItemGridRecBinding;
import com.krishna.recyclerviewexample.databinding.ItemLinearRecBinding;
import com.krishna.recyclerviewexample.model.DataModel;

import java.util.List;

public class AdapterGrid extends RecyclerView.Adapter<AdapterGrid.MyViewHolder> {

    List<DataModel> dataModelList;
    Activity activity;



    public AdapterGrid(List<DataModel> dataModelList, Activity activity) {
        this.dataModelList = dataModelList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdapterGrid.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGridRecBinding itemLinearRecBinding = ItemGridRecBinding.inflate(this.activity.getLayoutInflater(), parent, false);
        return new MyViewHolder(itemLinearRecBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGrid.MyViewHolder holder, int position) {

        DataModel dataModel = this.dataModelList.get(position);
        holder.binding.txtTitle.setText(dataModel.getTitle());
        holder.binding.txtDescription.setText(dataModel.getDescription());
        Glide.with(activity).load(Uri.parse(dataModel.getImageUrl()))
                .apply(new RequestOptions()
                        .placeholder(R.drawable.progress_animation)
                )
                .into(holder.binding.imgView);

    }

    @Override
    public int getItemCount() {
        return this.dataModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemGridRecBinding binding;

        public MyViewHolder(@NonNull ItemGridRecBinding itemLinearRecBinding) {
            super(itemLinearRecBinding.getRoot());
            binding = itemLinearRecBinding;
        }
    }
}
