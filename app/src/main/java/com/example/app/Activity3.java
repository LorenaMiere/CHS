package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.view.View.OnClickListener;
import java.text.NumberFormat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class Activity3 extends AppCompatActivity {
    private EditText age;
    private EditText weight;
    private EditText height;
    private TextView result;
    private RadioButton light;
    private RadioButton moderate;
    private RadioButton active;
    private RadioButton female;
    private RadioButton male;

    private double age1 = 0;
    private double weight1=0;
    private double height1=0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_calculate:

                    return true;
                case R.id.navigation_meal:

                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_calculate);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_calculate:
                        startActivity(new Intent(getApplicationContext(), Activity3.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(), Activity2.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.navigation_meal:
                        startActivity(new Intent(getApplicationContext(), Activity4.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });

        age =(EditText) findViewById(R.id.age);
        weight =(EditText) findViewById(R.id.weight);
        height =(EditText) findViewById(R.id.editTextNumberDecimal3);
        result=(TextView)findViewById(R.id.result);
        light=(RadioButton)findViewById(R.id.light);
        moderate=(RadioButton)findViewById(R.id.moderate);
        active=(RadioButton)findViewById(R.id.active);
        female=(RadioButton)findViewById(R.id.female);
        male=(RadioButton)findViewById(R.id.male);
        Button submitButton = (Button) findViewById(R.id.button8);

        submitButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                makeCalculations();
            }

        });


        }
    public void makeCalculations() {
        if (age.getText().toString().matches("") || weight.getText().toString().matches("")  || height.getText().toString().matches("") ) {
            result.setText("Introduceti o valoare");
            return;
        }
        double age1=Double.valueOf(age.getText().toString());
        double weight1=Double.valueOf(weight.getText().toString());
        double height1=Double.valueOf(height.getText().toString());


        double result1=weight1 * 10 + height1 * 6.25 - age1 * 5;
        if(female.isChecked()) {
            result1 = result1-161;
        }else
            if(male.isChecked()){
                result1=result1-5;
            }
        if(light.isChecked()){
            result1*=1.37;
            result.setText(String.valueOf(result1));
        }else if(moderate.isChecked()){
                result1*=1.55;
                result.setText(String.valueOf(result1));
            }else if(active.isChecked()){
                    result1*=1.72;
                    result.setText(String.valueOf(result1));
                }
                    if((moderate.isChecked()&&light.isChecked()&&active.isChecked())||(!moderate.isChecked()&&light.isChecked()&&active.isChecked())||
                            (moderate.isChecked()&&!light.isChecked()&&active.isChecked())||(moderate.isChecked()&&light.isChecked()&&!active.isChecked())){
                        result.setText("Selectati o singura optiune");
                        return;
                    }else
                    if(!moderate.isChecked()&&!light.isChecked()&&!active.isChecked()){
                        result.setText("Selectati o optiune");
                        return;
                    }
                    if(female.isChecked()&&male.isChecked()) {
                        result.setText("Selectati doar o optiune");
                        return;
                    }else
                    if(!female.isChecked()&&!male.isChecked()){
                        result.setText("Selectati una din cele doua optiuni ");
                        return;
                    }

        Intent intent = new Intent(Activity3.this, Activity2.class);
        intent.putExtra("result",Double.toString(result1));
        startActivity(intent);
    }

    }

