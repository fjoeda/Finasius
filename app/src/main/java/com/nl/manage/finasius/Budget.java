package com.nl.manage.finasius;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Budget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        getSupportActionBar().hide();
    }
}
