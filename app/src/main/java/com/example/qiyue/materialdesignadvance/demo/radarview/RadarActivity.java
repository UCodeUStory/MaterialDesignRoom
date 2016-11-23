package com.example.qiyue.materialdesignadvance.demo.radarview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.RotateAnimation;

import com.example.qiyue.materialdesignadvance.R;
import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

public class RadarActivity extends AppCompatActivity {

    private radarSweepView sweepView;
    ViewTreeObserver mViewTreeObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);
        sweepView = (radarSweepView) findViewById(R.id.radarSweepView);
     /*   mViewTreeObserver = sweepView.getViewTreeObserver();
        mViewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                // TODO Auto-generated method stub
                sweepView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//               // System.out.println("onGlobalLayout width=" + mTextView.getWidth() + " height=" + mTextView.getHeight());
//                L.i("sweepView"+sweepView.getWidth()+"=--"+sweepView.getHeight());
//                L.i("rea"+sweepView.getRealWidth());
                RotateAnimation rotateAnimation = new RotateAnimation(0,360,
                        sweepView.getWidth()/2,sweepView.getHeight()/2);
                rotateAnimation.setDuration(2000);
                rotateAnimation.setRepeatCount(Integer.MIN_VALUE);
                sweepView.startAnimation(rotateAnimation);
            }
        });*/
       /* L.i("sweepView"+sweepView.getWidth()+"=--"+sweepView.getHeight());
        L.i("rea"+sweepView.getRealWidth());
        RotateAnimation rotateAnimation = new RotateAnimation(0,360,
                sweepView.getWidth()/2,sweepView.getHeight()/2);
        rotateAnimation.setDuration(4000);
        rotateAnimation.setRepeatCount(Integer.MIN_VALUE);
        sweepView.startAnimation(rotateAnimation);*/
    }
}
