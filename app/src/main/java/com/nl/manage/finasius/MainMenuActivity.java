package com.nl.manage.finasius;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class MainMenuActivity extends AppCompatActivity {

    int pengeluaran = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_menu);
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);

        //Building ham button
        //Scan
        HamButton.Builder builder = new HamButton.Builder()
                .normalImageRes(R.drawable.piece)
                .normalText("Scan Pembelian")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        Intent intent = new Intent(MainMenuActivity.this, DetectImageActivity.class);
                        intent.putExtra("PENGELUARAN_VALUE", pengeluaran);
                        startActivity(intent);
                    }
                });
        bmb.addBuilder(builder);
        //Invest
        builder = new HamButton.Builder()
                .normalImageRes(R.drawable.piece)
                .normalText("Jenius Invest")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        Intent intent = new Intent(MainMenuActivity.this, InvestActivity.class);
                        startActivity(intent);
                    }
                });
        bmb.addBuilder(builder);
        //Bill Reminder
        builder = new HamButton.Builder()
                .normalImageRes(R.drawable.piece)
                .normalText("Bill Reminder")
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        Intent intent = new Intent(MainMenuActivity.this, BillTracker.class);
                        startActivity(intent);
                    }
                });
        bmb.addBuilder(builder);
        //

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if(intent.hasExtra("PENGELUARAN_NEW_VALUE")){
            pengeluaran += intent.getIntExtra("PENGELUARAN_NEW_VALUE",0);
        }
    }
}
