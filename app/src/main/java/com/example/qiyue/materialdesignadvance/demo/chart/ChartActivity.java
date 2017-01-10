package com.example.qiyue.materialdesignadvance.demo.chart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.qiyue.materialdesignadvance.R;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        HistogramView histogramView = (HistogramView)findViewById(R.id.histogramView);
        histogramView.startAnimation();
    }
}
