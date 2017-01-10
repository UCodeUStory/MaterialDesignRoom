package com.example.qiyue.materialdesignadvance.demo.path;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.example.qiyue.materialdesignadvance.R;
import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

/**
 * Created by qiyue on 2016/11/7.
 */
public class WaveView extends View {

    private Paint paint;
    private Path path;
    private int wavelength = 700;

    private int originY = 700;

    private int dx = 0;
    private float dy = 0;
    private int color;
    private float p;

    public WaveView(Context context) {
        super(context);
        init(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        this.color = getResources().getColor(R.color.wave_color);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.clipRect(new Rect(0,0,getWidth(),getWidth()));
        path.reset();
        path.addCircle(getWidth()/2,getWidth()/2,getWidth()/2, Path.Direction.CCW);
        canvas.clipPath(path);
        canvas.drawColor(Color.RED);
        path.reset();

        int halfWavelength = wavelength/2;
        /**
         * 这里加150就是为了全部都跑出去
         */
        if(dy < originY + 150){
            dy += 1;
        }
        path.moveTo(-wavelength+dx,originY-dy);
        /**
         * 最大不超过屏幕宽度加一个波长，并且每次绘制一个波长是加一个波长
         *
         * 这里通过rQuadTo实现相对位置，做动画的时候不用去计算每一个点
         *
         *
         */
        for(int i = -wavelength;i<getWidth() + wavelength;i+=wavelength){
            /**
             * 这里绘制的每一个点都是相对起始点，第一个二阶贝塞尔曲线的起始点是
             * path.moveTo(-wavelength,originY);
             * 第一个拐点，和结束点
             */
            path.rQuadTo(halfWavelength/2,-150,halfWavelength,0);
            /**
             * 这里的起始点是上一个的终点
             */
            path.rQuadTo(halfWavelength/2,150,halfWavelength,0);
        }

        path.lineTo(getWidth(),getHeight());
        path.lineTo(0,getHeight());
        path.close();
        canvas.drawPath(path,paint);
        paint.reset();
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);
        L.i("dy="+dy);
        this.p = dy/(originY+150);
        L.i("p="+p);

        p  = p * 100;
        int percentage = (int)p;
        canvas.drawText(percentage + "%",getWidth()/2-50,getWidth()/2+10,paint);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);

    }

    public void startWaveAnimation(){
        /**
         * 保证衔接好，最大值是一个波长
         */
        ValueAnimator animator = ValueAnimator.ofInt(0,wavelength);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        /**
         * 实现循环
         */
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                if (p>=100) {
                }else{
                    postInvalidate();
                }
            }
        });
        animator.start();
    }
}
