package com.nl.manage.finasius;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class MainMenuActivity extends AppCompatActivity {

    int pengeluaran = 0;
    ValueLineSeries pemasukkanSeries;
    ValueLineSeries pengeluaranSeries;
    ValueLineChart lineChart;
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

        // Build Graph
        lineChart = (ValueLineChart)findViewById(R.id.linechart);
        BuildChart();
    }

    public void BuildChart(){
        pemasukkanSeries = new ValueLineSeries();
        pemasukkanSeries.setColor(0x4500A4E1);
        pengeluaranSeries = new ValueLineSeries();
        pengeluaranSeries.setColor(0x45FF764A);
        pemasukkanSeries.addPoint(new ValueLinePoint("18 Feb",20000));
        pemasukkanSeries.addPoint(new ValueLinePoint("19 Feb",25000));
        pemasukkanSeries.addPoint(new ValueLinePoint("20 Feb",33000));
        pemasukkanSeries.addPoint(new ValueLinePoint("21 Feb",50000));
        pemasukkanSeries.addPoint(new ValueLinePoint("22 Feb",45000));
        pemasukkanSeries.addPoint(new ValueLinePoint("23 Feb",44000));
        pemasukkanSeries.addPoint(new ValueLinePoint("24 Feb",47000));
        pengeluaranSeries.addPoint(new ValueLinePoint("18 Feb",10000));
        pengeluaranSeries.addPoint(new ValueLinePoint("19 Feb",15000));
        pengeluaranSeries.addPoint(new ValueLinePoint("20 Feb",23000));
        pengeluaranSeries.addPoint(new ValueLinePoint("21 Feb",30000));
        pengeluaranSeries.addPoint(new ValueLinePoint("22 Feb",25000));
        pengeluaranSeries.addPoint(new ValueLinePoint("23 Feb",34000));
        pengeluaranSeries.addPoint(new ValueLinePoint("24 Feb",pengeluaran));
        lineChart.addSeries(pemasukkanSeries);
        lineChart.addSeries(pengeluaranSeries);
        lineChart.startAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if(intent.hasExtra("PENGELUARAN_NEW_VALUE")){
            pengeluaran += intent.getIntExtra("PENGELUARAN_NEW_VALUE",0);

        }
        lineChart.getDataSeries().remove(pengeluaranSeries);
        pengeluaranSeries = new ValueLineSeries();
        pengeluaranSeries.setColor(0x45FF764A);
        pengeluaranSeries.addPoint(new ValueLinePoint("18 Feb",10000));
        pengeluaranSeries.addPoint(new ValueLinePoint("19 Feb",15000));
        pengeluaranSeries.addPoint(new ValueLinePoint("20 Feb",23000));
        pengeluaranSeries.addPoint(new ValueLinePoint("21 Feb",30000));
        pengeluaranSeries.addPoint(new ValueLinePoint("22 Feb",25000));
        pengeluaranSeries.addPoint(new ValueLinePoint("23 Feb",34000));
        pengeluaranSeries.addPoint(new ValueLinePoint("24 Feb",pengeluaran));
        lineChart.addSeries(pengeluaranSeries);
        lineChart.startAnimation();
    }
}
