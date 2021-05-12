package com.example.chillnoise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(ActivityMain.this, ActivityNoises.class);
                        startActivity(intent);
                        finish();

                    }
                }, 1000
        );

    }
}