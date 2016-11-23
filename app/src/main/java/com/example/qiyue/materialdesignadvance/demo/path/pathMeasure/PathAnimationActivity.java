package com.example.qiyue.materialdesignadvance.demo.path.pathMeasure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.qiyue.materialdesignadvance.R;

public class PathAnimationActivity extends AppCompatActivity {

    private BoatView boatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_animation);
        boatView = (BoatView)findViewById(R.id.boatView);
        boatView.startAnimation();
    }
}
