package com.krishna.recyclerviewexample.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.krishna.recyclerviewexample.R;
import com.krishna.recyclerviewexample.databinding.ActivityMainBinding;
import com.krishna.recyclerviewexample.interfaces.DataView;
import com.krishna.recyclerviewexample.model.DataModel;
import com.krishna.recyclerviewexample.presenter.DataPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding activityMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
        activityMainBinding.btnRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTo(LinearRecActivity.class);
            }
        });
        activityMainBinding.btnGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTo(GridRecActivity.class);
            }
        });
        activityMainBinding.btnStaggeredGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTo(StaggeredGridRecActivity.class);
            }
        });

    }

    public void sendTo(Class<?> activity) {
        Intent intent = new Intent(getApplicationContext(), activity);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}