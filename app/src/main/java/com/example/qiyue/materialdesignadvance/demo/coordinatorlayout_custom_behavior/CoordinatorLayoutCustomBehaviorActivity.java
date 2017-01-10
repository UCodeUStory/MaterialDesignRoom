package com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_custom_behavior;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.qiyue.materialdesignadvance.R;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class CoordinatorLayoutCustomBehaviorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_behavior);
        findViewById(R.id.depentent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewCompat.offsetTopAndBottom(v, 5);
            }
        });

      /*  findViewById(R.id.depentent).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View v, MotionEvent event) {

                GestureDetector gestureDetector = new GestureDetector(CoordinatorLayoutCustomBehaviorActivity.this,
                        new GestureDetector.OnGestureListener() {
                            @Override
                            public boolean onDown(MotionEvent e) {
                                return false;
                            }

                            @Override
                            public void onShowPress(MotionEvent e) {

                            }

                            @Override
                            public boolean onSingleTapUp(MotionEvent e) {
                                return false;
                            }

                            @Override
                            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                             //   distanceY = Math.abs(distanceY);
                                try {
                                   *//* Toast.makeText(CoordinatorLayoutCustomBehaviorActivity.this,
                                            "distanceY=" + distanceY, Toast.LENGTH_SHORT).show();*//*
                                    Log.i("qiyue",  "distanceY=" + distanceY);
                                }catch (Exception e){

                                }
                              //  ViewCompat.offsetTopAndBottom(v, (int) distanceY/10);
                                return true;
                            }

                            @Override
                            public void onLongPress(MotionEvent e) {

                            }

                            @Override
                            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                                return false;
                            }
                        });

               return gestureDetector.onTouchEvent(event);
//                return true;
            }
        });*/
    }
}
