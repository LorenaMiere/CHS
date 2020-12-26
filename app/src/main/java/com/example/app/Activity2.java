package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 *
 */
public class Activity2 extends AppCompatActivity{
    private Button calculator;
    private Button exercises;
    private Button meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        calculator = (Button)findViewById(R.id.button6);
        calculator.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Activity2.this, Activity3.class);
                startActivity(intent);
            }
        });

        exercises = (Button) findViewById(R.id.button7);
        exercises.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent2 = new Intent(Activity2.this, Activity4.class);
                startActivity(intent2);
            }
        });
        meals = (Button) findViewById(R.id.button5);
        meals.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent3 = new Intent(Activity2.this, Activity5.class);
                startActivity(intent3);
            }
        });
    }
}




