package com.krishna.recyclerviewexample.ui.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.krishna.recyclerviewexample.R;
import com.krishna.recyclerviewexample.databinding.ActivityDataListBinding;
import com.krishna.recyclerviewexample.interfaces.DataView;
import com.krishna.recyclerviewexample.model.DataModel;
import com.krishna.recyclerviewexample.presenter.DataPresenter;
import com.krishna.recyclerviewexample.ui.adapter.AdapterLinear;

import java.util.List;


public class LinearRecActivity extends AppCompatActivity implements DataView {

    DataPresenter dataPresenter;
    ActivityDataListBinding activityDataListBinding;
    AdapterLinear adapterLinear;
    AdapterLinear adapterHorizontal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDataListBinding = ActivityDataListBinding.inflate(getLayoutInflater());
        View view = activityDataListBinding.getRoot();
        setContentView(view);

        try {
            setSupportActionBar(activityDataListBinding.toolbar);
            getSupportActionBar().setTitle(getResources().getString(R.string.linear_recyclerview));
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
        activityDataListBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        activityDataListBinding.recHorizontal.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


    }


    @Override
    public void showData(List<DataModel> user) {

        if (user != null && user.size() > 0) {
            adapterLinear = new AdapterLinear(user, LinearRecActivity.this, false);
            activityDataListBinding.recyclerView.setAdapter(adapterLinear);
            adapterHorizontal = new AdapterLinear(user, LinearRecActivity.this, true);
            activityDataListBinding.recHorizontal.setAdapter(adapterHorizontal);
            activityDataListBinding.recHorizontal.setVisibility(View.VISIBLE);
            activityDataListBinding.progressBar.setVisibility(View.GONE);
        } else {
            activityDataListBinding.recyclerView.setAdapter(null);
            activityDataListBinding.recHorizontal.setAdapter(null);
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
