package com.krishna.recyclerviewexample.ui.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.krishna.recyclerviewexample.R;
import com.krishna.recyclerviewexample.databinding.ActivityDataListBinding;
import com.krishna.recyclerviewexample.interfaces.DataView;
import com.krishna.recyclerviewexample.model.DataModel;
import com.krishna.recyclerviewexample.presenter.DataPresenter;
import com.krishna.recyclerviewexample.ui.adapter.AdapterGrid;
import com.krishna.recyclerviewexample.ui.adapter.AdapterLinear;

import java.util.List;


public class GridRecActivity extends AppCompatActivity implements DataView {

    DataPresenter dataPresenter;
    ActivityDataListBinding activityDataListBinding;
    AdapterGrid adapterLinear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDataListBinding = ActivityDataListBinding.inflate(getLayoutInflater());
        View view = activityDataListBinding.getRoot();
        setContentView(view);
        try {
            setSupportActionBar(activityDataListBinding.toolbar);
            getSupportActionBar().setTitle(getResources().getString(R.string.gridview));
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
        activityDataListBinding.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));


    }


    @Override
    public void showData(List<DataModel> user) {

        activityDataListBinding.progressBar.setVisibility(View.GONE);
        if (user != null && user.size() > 0) {
            adapterLinear = new AdapterGrid(user, GridRecActivity.this);
            activityDataListBinding.recyclerView.setAdapter(adapterLinear);
        } else {
            activityDataListBinding.recyclerView.setAdapter(null);
        }

    }


    @Override
    public void onBackPressed() {
        finish();
    }



}
