package com.sit305_sit708_7_P.DashboardPackage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.sit305_sit708_7_P.BaseActivity;
import com.sit305_sit708_7_P.DataBasePackage.DatabaseHelper;
import com.sit305_sit708_7_P.MainActivity;
import com.sit305_sit708_7_P.databinding.ActivityDashBoardBinding;

public class NewAdvert extends BaseActivity<ActivityDashBoardBinding> {

    private ActivityDashBoardBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);

        binding.saveAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        String postType = "";

        int selectedRadioButtonId = binding.radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId != View.NO_ID) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            postType = selectedRadioButton.getText().toString();
        }


        String name = binding.editName.getText().toString().trim();
        String phone = binding.editPhone.getText().toString().trim();
        String description = binding.editDescription.getText().toString().trim();
        String date = binding.editDate.getText().toString().trim();
        String location = binding.editLocation.getText().toString().trim();


        if (postType.isEmpty() || name.isEmpty() || phone.isEmpty() || description.isEmpty() || date.isEmpty() || location.isEmpty()) {
            Toast.makeText(NewAdvert.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
        } else {
            // All fields are valid, insert the data into the database
           boolean isInserted =  databaseHelper.insertData(postType, name, phone, description, date, location);
            if (isInserted) {
                Toast.makeText(NewAdvert.this, "Requested Added successfully", Toast.LENGTH_SHORT).show();
                onBackPressed();
            } else {
                Toast.makeText(NewAdvert.this, "Failure!!", Toast.LENGTH_SHORT).show();
            }

        }

            }
        });

    }

    @Override
    protected ActivityDashBoardBinding getViewBinding() {
        binding = ActivityDashBoardBinding.inflate(getLayoutInflater());
        return binding;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(NewAdvert.this, MainActivity.class));
        finish();
    }

}