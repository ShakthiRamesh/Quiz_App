package com.example.onlinequizappglide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void cartoon(View view){
        Intent i=new Intent(this,MainActivity1.class);
        startActivity(i);
    }
    public void fruits(View view){
        Intent i=new Intent(this,MainActivity2.class);
        startActivity(i);
    }
    public void animals(View view){
        Intent i=new Intent(this,MainActivity3.class);
        startActivity(i);
    }
    public void vehicles(View view){
        Intent i=new Intent(this,MainActivity4.class);
        startActivity(i);
    }

}