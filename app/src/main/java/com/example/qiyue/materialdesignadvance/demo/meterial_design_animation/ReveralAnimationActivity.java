package com.example.qiyue.materialdesignadvance.demo.meterial_design_animation;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.qiyue.materialdesignadvance.R;

public class ReveralAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveral_animation);

        final Button buttonOne = (Button)findViewById(R.id.button_one);

        final Button buttonTwo = (Button)findViewById(R.id.button_two);

        buttonOne.setOnClickListener(new View.OnClickListener() {
            /**
             * 只有新版本有这api效果
             * @param v
             */
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Animator animator = ViewAnimationUtils.createCircularReveal(buttonOne, buttonOne.getWidth()/2,
                        buttonOne.getHeight()/2, 0, buttonOne.getHeight());
                animator.setDuration(1000);
                animator.setInterpolator(new AccelerateInterpolator());
                animator.start();
            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Animator animator = ViewAnimationUtils.createCircularReveal(buttonTwo, 0, 0, 0,
                        (float)Math.hypot(buttonTwo.getWidth(), buttonTwo.getHeight()));
                animator.setDuration(1000);
                animator.setInterpolator(new AccelerateInterpolator());
                animator.start();
            }
        });

    }
}
