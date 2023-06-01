package com.sit305_sit708_7_P;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sit305_sit708_7_P.DashboardPackage.NewAdvert;
import com.sit305_sit708_7_P.DashboardPackage.ShowAllLostAndFoundActivity;
import com.sit305_sit708_7_P.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.newAdvertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewAdvert.class));
                finish();
            }
        });

        binding.showAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ShowAllLostAndFoundActivity.class));
                finish();
            }
        });


    }

    @Override
    protected ActivityMainBinding getViewBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        return binding;
    }


}