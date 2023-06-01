package com.sit305_sit708_7_P.DashboardPackage;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sit305_sit708_7_P.AdaptersPackage.DisplayAdapter;
import com.sit305_sit708_7_P.AdaptersPackage.OnClickListenerInterface;
import com.sit305_sit708_7_P.BaseActivity;
import com.sit305_sit708_7_P.DataBasePackage.DatabaseHelper;
import com.sit305_sit708_7_P.DataModelPackage.DataModel;
import com.sit305_sit708_7_P.MainActivity;
import com.sit305_sit708_7_P.R;
import com.sit305_sit708_7_P.databinding.ActivityShowAllLostAndFoundBinding;

import java.util.List;

public class ShowAllLostAndFoundActivity extends BaseActivity<ActivityShowAllLostAndFoundBinding> implements OnClickListenerInterface {

    private ActivityShowAllLostAndFoundBinding binding;
    private DisplayAdapter displayAdapter;
    private List<DataModel> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.displayRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataList = getDataFromDatabase();

        if(dataList.isEmpty())
        {
            binding.noDataFoundTxtView.setVisibility(View.VISIBLE);
        }else
        {
            binding.displayRecyclerView.setVisibility(View.VISIBLE);
        }
        displayAdapter = new DisplayAdapter(dataList,this);
        binding.displayRecyclerView.setAdapter(displayAdapter);
    }

    @Override
    protected ActivityShowAllLostAndFoundBinding getViewBinding() {

        binding = ActivityShowAllLostAndFoundBinding.inflate(getLayoutInflater());
        return binding;
    }


    private List<DataModel> getDataFromDatabase() {
        // Implement your database retrieval logic here and return the list of data
        // For simplicity, let's assume you have a DatabaseHelper instance called dbHelper
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        return dbHelper.getAllData();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ShowAllLostAndFoundActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onItemClick(DataModel dataModel) {
        Intent intent = new Intent(ShowAllLostAndFoundActivity.this,DisplayAndRemoveAcitivty.class);
        intent.putExtra("dataModel", dataModel);
        startActivity(intent);

    }
}