package com.krishna.recyclerviewexample.ui.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.krishna.recyclerviewexample.R;
import com.krishna.recyclerviewexample.databinding.ActivityDataListBinding;
import com.krishna.recyclerviewexample.interfaces.DataView;
import com.krishna.recyclerviewexample.model.DataModel;
import com.krishna.recyclerviewexample.presenter.DataPresenter;
import com.krishna.recyclerviewexample.ui.adapter.AdapterGrid;
import com.krishna.recyclerviewexample.ui.adapter.AdapterStaggeredGrid;

import java.util.List;


public class StaggeredGridRecActivity extends AppCompatActivity implements DataView {

    DataPresenter dataPresenter;
    ActivityDataListBinding activityDataListBinding;
    AdapterStaggeredGrid adapterLinear;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDataListBinding = ActivityDataListBinding.inflate(getLayoutInflater());
        View view = activityDataListBinding.getRoot();
        setContentView(view);
        try {
            setSupportActionBar(activityDataListBinding.toolbar);
            getSupportActionBar().setTitle(getResources().getString(R.string.staggeredGridview));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activityDataListBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        dataPresenter = new DataPresenter(this);
        dataPresenter.loadUser();
        activityDataListBinding.recyclerView.setHasFixedSize(true);

        activityDataListBinding.recyclerView.setVisibility(View.GONE);
        activityDataListBinding.progressBar.setVisibility(View.VISIBLE);
        activityDataListBinding.recyclerView.addOnScrollListener(scrollListener);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activityDataListBinding.recyclerView.setVisibility(View.VISIBLE);
                activityDataListBinding.progressBar.setVisibility(View.GONE);
            }
        },500);

    }


    @Override
    public void showData(List<DataModel> user) {

        if (user != null && user.size() > 0) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            int gridSize=2;
            activityDataListBinding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(gridSize,StaggeredGridLayoutManager.VERTICAL));

            adapterLinear = new AdapterStaggeredGrid(user, StaggeredGridRecActivity.this,width/gridSize);
            activityDataListBinding.recyclerView.setAdapter(adapterLinear);

        } else {
            activityDataListBinding.recyclerView.setAdapter(null);
        }

    }
    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            // Check if the user has stopped scrolling
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                // Do something when scrolling stops
                // For example, load more data
            }
        }
    };


    @Override
    public void onBackPressed() {
        finish();
    }
}
