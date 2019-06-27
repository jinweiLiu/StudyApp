package com.example.hardwork.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hardwork.R;

public class WelcomActivity extends AppCompatActivity implements  Runnable{
    final Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        mHandler.postDelayed(this,2000);
    }

    @Override
    public void run() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
