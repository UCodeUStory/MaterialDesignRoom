package com.example.qiyue.materialdesignadvance.demo.path;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.qiyue.materialdesignadvance.R;

public class WaveActivity extends AppCompatActivity {

    private WaveView waveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);
        waveView = (WaveView)findViewById(R.id.waveView);
        waveView.startWaveAnimation();
    }
}
