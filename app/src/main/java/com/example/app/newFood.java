package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class newFood extends AppCompatActivity {
    private EditText Name;
    private EditText Calories;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);
        Name = findViewById(R.id.editTextTextPersonName);
        Calories = findViewById(R.id.Calories);
        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Food food = new Food();
                food.setName(Name.getText().toString());
                food.setCalories(Long.valueOf(Calories.getText().toString()));
                new FirebaseDatabaseHelper().addFood(food, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Food> food, List<String> keys) {
                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(newFood.this, "New food added", Toast.LENGTH_LONG).show();
                    }


                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });

            }


        });
    }
}