package com.krishna.recyclerviewexample.ui.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.StrictMode;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.krishna.recyclerviewexample.R;
import com.krishna.recyclerviewexample.databinding.ItemGridRecBinding;
import com.krishna.recyclerviewexample.databinding.ItemStaggeredGridRecBinding;
import com.krishna.recyclerviewexample.model.DataModel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;

public class AdapterStaggeredGrid extends RecyclerView.Adapter<AdapterStaggeredGrid.MyViewHolder> {

    List<DataModel> dataModelList;
    Activity activity;
    int width;

    int max=600;
    int min=300;

    public AdapterStaggeredGrid(List<DataModel> dataModelList, Activity activity, int width) {
        this.dataModelList = dataModelList;
        this.activity = activity;
        this.width = width;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

    }

    @NonNull
    @Override
    public AdapterStaggeredGrid.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStaggeredGridRecBinding itemLinearRecBinding = ItemStaggeredGridRecBinding.inflate(this.activity.getLayoutInflater(), parent, false);
        return new MyViewHolder(itemLinearRecBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterStaggeredGrid.MyViewHolder holder, int position) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        DataModel dataModel = this.dataModelList.get(position);
//        Bitmap bitmap=getBitmapFromURL(dataModel.getImageUrl());
        Glide.with(activity).asBitmap().load(dataModel.getImageUrl())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.progress_animation).override(width,randomNum)
                )
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
            .into(holder.binding.imageView);

//        Glide.with(activity).asBitmap().load(dataModel.getImageUrl())
//                .apply(new RequestOptions()
//                        .placeholder(R.drawable.progress_animation).fallback(R.drawable.progress_animation)
//                )
//                .centerCrop()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .priority(Priority.HIGH).
//                into(new CustomTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
//                        float aspectRatio = bitmap.getWidth() /
//                                (float) bitmap.getHeight();
//
//                        int height = Math.round(width / aspectRatio);
//                        Bitmap b = getResizedBitmap(bitmap, width, height);
//                        holder.binding.imageView.setImageBitmap(b);
//                    }
//
//                    @Override
//                    public void onLoadCleared(@Nullable Drawable placeholder) {
//                    }
//                });


//        Glide.with(activity).asBitmap().load(dataModel.getImageUrl()).encodeQuality(30).into(new CustomTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
//                float aspectRatio = bitmap.getWidth() /
//                        (float) bitmap.getHeight();
//
//                int height = Math.round(width / aspectRatio);
//                Bitmap b =getResizedBitmap(bitmap,width,height);
//                holder.binding.imageView.setImageBitmap(b);
//            }
//            @Override
//            public void onLoadCleared(@Nullable Drawable placeholder) {
//            }
//        });
//        if (bitmap != null) {
//            float aspectRatio = bitmap.getWidth() /
//                    (float) bitmap.getHeight();
//
//            int height = Math.round(width / aspectRatio);
//            Bitmap b = getResizedBitmap(bitmap, width, height);
//            holder.binding.imageView.setImageBitmap(b);
//        }
//
////        if (bitmap!=null && bitmap.getHeight()>400 || bitmap.getWidth()>400)
////        {
////            float aspectRatio = bitmap.getWidth() /
////                    (float) bitmap.getHeight();
////
////            int height = Math.round(this.width / aspectRatio);
////            Bitmap b =getResizedBitmap(bitmap,this.width,height);
////            holder.binding.imageView.setImageBitmap(b);
////
//////            Bitmap b =getResizedBitmap(bitmap,bitmap.getWidth()/2,bitmap.getHeight()/2);
//////            holder.binding.imageView.setImageBitmap(b);
////        }else {
//////            holder.binding.imageView.setImageBitmap(bitmap);
////        }
////
//
//////        holder.binding.txtTitle.setText(dataModel.getTitle());
//////        Glide.with(activity).load(Uri.parse(dataModel.getImageUrl())).into(holder.binding.imgView);
//////        Glide.with(activity).load(Uri.parse(dataModel.getImageUrl())).placeholder(android.R.drawable.progress_indeterminate_horizontal).error(android.R.drawable.stat_notify_error).into(holder.binding.imageView);
////        Picasso.get().load(Uri.parse(dataModel.getImageUrl())).resize()
////               .into(holder.binding.imageView);


    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);


        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
//        original.compress(Bitmap.CompressFormat.PNG, 100, out);

//        bm.recycle();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        resizedBitmap.compress(Bitmap.CompressFormat.PNG, 20, out);
        Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));


//        BitmapFactory.Options imageOpts = new BitmapFactory.Options ();
//        imageOpts.inSampleSize = 2;   // for 1/2 the image to be loaded
//        Bitmap thumb = Bitmap.createScaledBitmap (resizedBitmap, resizedBitmap.getWidth(), resizedBitmap.getHeight(), false);

        return decoded;
    }


    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    } // Author: silentnuke


    @Override
    public int getItemCount() {
        return this.dataModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ItemStaggeredGridRecBinding binding;

        public MyViewHolder(@NonNull ItemStaggeredGridRecBinding itemLinearRecBinding) {
            super(itemLinearRecBinding.getRoot());
            binding = itemLinearRecBinding;
        }
    }
}
