package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Activity4 extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Button select;
    private long calculate=0;
   private DatabaseReference caloriesRef;
   private FirebaseDatabase database;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_calculate:

                    return true;


            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
        mRecyclerView=(RecyclerView) findViewById(R.id.recyclerview_food);
        select=findViewById(R.id.button5);
        mRecyclerView.setNestedScrollingEnabled(false);
        new FirebaseDatabaseHelper().selectFood(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Food> foods, List<String> keys) {
                new RecyclerVire_config().setConfig(mRecyclerView,Activity4.this,foods,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_meal);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.foodlistmeu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addnewFoodItem:
                startActivity(new Intent(this,newFood.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}