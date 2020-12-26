package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener(){

        public void onClick(View v){
            openActivity2();

        }

    });
    }
     public void openActivity2() {
         Intent intent=new Intent (this,Activity2.class);
         startActivity(intent);
        }

}