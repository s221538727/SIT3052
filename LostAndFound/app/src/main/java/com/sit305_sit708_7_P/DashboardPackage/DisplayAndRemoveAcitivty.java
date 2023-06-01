package com.sit305_sit708_7_P.DashboardPackage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sit305_sit708_7_P.BaseActivity;
import com.sit305_sit708_7_P.DataBasePackage.DatabaseHelper;
import com.sit305_sit708_7_P.DataModelPackage.DataModel;
import com.sit305_sit708_7_P.MainActivity;
import com.sit305_sit708_7_P.R;
import com.sit305_sit708_7_P.databinding.ActivityDisplayAndRemoveAcitivtyBinding;

public class DisplayAndRemoveAcitivty extends BaseActivity<ActivityDisplayAndRemoveAcitivtyBinding>
{

    ActivityDisplayAndRemoveAcitivtyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("dataModel")) {
            DataModel dataModel = (DataModel) intent.getSerializableExtra("dataModel");

            binding.txtPostType.setText(dataModel.getPostType());
            binding.txtName.setText(dataModel.getName());
            binding.txtDescription.setText(dataModel.getDescription());
            binding.txtPhone.setText(dataModel.getPhone());
            binding.txtDate.setText(dataModel.getDate());
            binding.txtLocation.setText(dataModel.getLocation());

        }

        binding.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatabaseHelper databaseHelper = new DatabaseHelper(DisplayAndRemoveAcitivty.this);

                // Retrieve the DataModel object from the intent
                DataModel dataModel = (DataModel) getIntent().getSerializableExtra("dataModel");

                // Get the ID of the data model
                int id = dataModel.getId();

                // Remove the item from the database
                boolean isDeleted = databaseHelper.deleteData(id);

                if (isDeleted) {
                    // Item deleted successfully
                    Toast.makeText(DisplayAndRemoveAcitivty.this, dataModel.getName()+" removed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DisplayAndRemoveAcitivty.this,ShowAllLostAndFoundActivity.class));
                    finish(); // Finish the activity and go back to the previous screen
                } else {
                    // Failed to delete the item
                    Toast.makeText(DisplayAndRemoveAcitivty.this, "Failed to remove item", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected ActivityDisplayAndRemoveAcitivtyBinding getViewBinding() {
        binding = ActivityDisplayAndRemoveAcitivtyBinding.inflate(getLayoutInflater());
        return  binding;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DisplayAndRemoveAcitivty.this, MainActivity.class));
        finish();
    }

}