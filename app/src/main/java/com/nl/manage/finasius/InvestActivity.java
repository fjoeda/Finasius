package com.nl.manage.finasius;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

public class InvestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_invest);

        ValueLineChart investChart = (ValueLineChart)findViewById(R.id.investChart);

        ValueLineSeries invsetSeries = new ValueLineSeries();
        invsetSeries.setColor(0xFF00A4E1);
        invsetSeries.addPoint(new ValueLinePoint("18 Feb", 1200));
        invsetSeries.addPoint(new ValueLinePoint("19 Feb", 1225));
        invsetSeries.addPoint(new ValueLinePoint("20 Feb", 1278));
        invsetSeries.addPoint(new ValueLinePoint("21 Feb", 1283));
        invsetSeries.addPoint(new ValueLinePoint("22 Feb", 1300));
        invsetSeries.addPoint(new ValueLinePoint("23 Feb", 1350));
        invsetSeries.addPoint(new ValueLinePoint("24 Feb", 1387));
        investChart.addSeries(invsetSeries);
        investChart.startAnimation();

    }
}
