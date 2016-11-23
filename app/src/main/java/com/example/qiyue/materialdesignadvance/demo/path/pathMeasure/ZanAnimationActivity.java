package com.example.qiyue.materialdesignadvance.demo.path.pathMeasure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.qiyue.materialdesignadvance.R;

public class ZanAnimationActivity extends AppCompatActivity {

    private ZanView viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zan_animation);
        viewById = (ZanView)findViewById(R.id.zanView);
        viewById.startAnimation();
    }
}
