package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Activity2 extends AppCompatActivity{

    protected TextView textView;
    protected TextView textView8;
    protected TextView caloriesBurned;
    protected double MagnitudePrevious = 0;
    protected Integer stepCount = 0;
    protected Float burned=0.0f;
    protected Double b=0.0;

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
        setContentView(R.layout.activity_2);
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        stepCount = sharedPreferences.getInt("stepCount", 0);
        burned = sharedPreferences.getFloat("burned", 0.0f);

        textView = findViewById(R.id.countsteps);
        textView8=findViewById(R.id.textView8);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SensorEventListener stepDetector = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                if (sensorEvent!= null){
                    float x_acceleration = sensorEvent.values[0];
                    float y_acceleration = sensorEvent.values[1];
                    float z_acceleration = sensorEvent.values[2];

                    double Magnitude = Math.sqrt(x_acceleration*x_acceleration + y_acceleration*y_acceleration + z_acceleration*z_acceleration);
                    double MagnitudeDelta = Magnitude - MagnitudePrevious;
                    MagnitudePrevious = Magnitude;

                    if (MagnitudeDelta > 6){
                        stepCount++;
                       burned= burned+0.04f;


                    }
                    textView.setText("You have made\n" + " " + stepCount.toString()+ "\n" + " " +"steps and burned\n " + " " + burned.toString() + " \n" +" calories");
                    Intent mIntent = getIntent();
                    String result1 = mIntent.getStringExtra("result");
                  if (result1 != null) {
                      b = Double.parseDouble(result1);


                    }
                  if (b != 0.0) {
                      double newResult=b;
                      newResult=newResult-(double)burned;
                      System.out.println("Result2"+ newResult);
                      textView8.setText(Double.toString(newResult));
                    }





                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

        sensorManager.registerListener(stepDetector, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected (MenuItem item){
                switch(item.getItemId()){
                    case R.id.navigation_calculate:
                        startActivity(new Intent(getApplicationContext(),Activity3.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(),Activity2.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_meal:
                        startActivity(new Intent(getApplicationContext(),Activity4.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }

//    protected void onPause() {
//        super.onPause();
//
//        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.putInt("stepCount", stepCount);
//        editor.putFloat("burned", burned);
//        editor.apply();
//    }
//
    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt("stepCount", stepCount);
        editor.putFloat("burned",  burned);
        editor.apply();
    }
//
//    protected void onResume() {
//        super.onResume();
//
//        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
//        stepCount = sharedPreferences.getInt("stepCount", 0);
//        burned = sharedPreferences.getFloat("burned", 0.0f);
//    }




    }





