package com.example.qiyue.materialdesignadvance.demo.customdrawable;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.qiyue.materialdesignadvance.R;

/**
 * 通过这个Demo学习
 * 1.自定义Drawable 重写draw方法，一个Drawable是一个可以绘制，里面可以包含多个图片
 * 2.save 和 restore应用
 * 3.通过Gravity.apply裁剪Drawable
 * 4.canvas.clip 剪切画布
 * 5.通过getBounds();获得自身矩形区域
 * 6.通过level定义显示范围
 * 7.通过getIntrinsicWidth获得图片真实宽高
 *
 *
 * */
public class CustomDrawableActivity extends AppCompatActivity {

    private ImageView iv;
    public Drawable revealDrawables;
    protected int level = 5000;
    private float factor = 3;
    private GallaryHorizonalScrollView hzv;

    private float mDownX ;
    private float mDownY ;
    private float mLastX ;
    private float mLastY ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_drawable);
        revealDrawables = new RevealDrawable(
                getResources().getDrawable(R.drawable.avft),
                getResources().getDrawable(R.drawable.avft_active),
                RevealDrawable.HORIZONTAL);

        iv = (ImageView) findViewById(R.id.iv);
        iv.setImageDrawable(revealDrawables);
        iv.setImageLevel(level);
        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mDownX = event.getX();
                        mDownY = event.getY();
                        mLastX = mDownX;
                        mLastY = mDownY;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float x = event.getX();
                        float y = event.getY();
                        float distance =  x - mLastX ;

                        level -= distance*factor;
                        iv.getDrawable().setLevel(level);

                        mLastX = x;
                        mLastY = y;
                        break;

                }
                return true;
            }
        });
     /*   iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level -= 1000;
                iv.getDrawable().setLevel(level);
            }
        });*/

    }
}



