package com.example.qiyue.materialdesignadvance.demo.chart;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.qiyue.materialdesignadvance.R;
import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiyue on 2016/10/25.
 */
public class HistogramView extends BaseChartView {
    private float startX = 100;
    private float startY = 100;
    private float centerX = 100;
    private int count = 4;
    private Paint mPaint;

    List<HistorgramBean> datas = new ArrayList<>();
    private ValueAnimator valueAnimator;

    private float animationValue = 0;

    public HistogramView(Context context) {
        super(context);
        init(context);
    }

    public HistogramView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        init(context);
    }

    public HistogramView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        Resources resources = getContext().getResources();
        HistorgramBean bean1 = new HistorgramBean(
                resources.getColor(R.color.histogram_color_one),300);
        HistorgramBean bean2 = new HistorgramBean(
                resources.getColor(R.color.histogram_color_two),400);
        HistorgramBean bean3 = new HistorgramBean(
                resources.getColor(R.color.histogram_color_three),600);
        HistorgramBean bean4 = new HistorgramBean(
                resources.getColor(R.color.histogram_color_four),350);
        datas.add(bean1);
        datas.add(bean2);
        datas.add(bean3);
        datas.add(bean4);


        valueAnimator = ValueAnimator.ofFloat(0,1).setDuration(1500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                L.i("animation="+animation.getAnimatedFraction());
                animationValue =  animation.getAnimatedFraction();
                postInvalidate();
            }
        });
    }

    @Override
    int getCurrentWidth() {
        return 400;
    }

    @Override
    int getCurrentHeight() {
        return 400;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
        /**
         * 绘制上三角
         */
        Path path = new Path();
        path.moveTo(80,120);
        path.lineTo(startX,startY);
        path.lineTo(120,120);
        mPaint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawPath(path,mPaint);
       // path.close();
        /**
         * 绘制两条线
         */

        path.moveTo(startX,startY);
        path.lineTo(centerX,mHeight-startX);
        path.lineTo(mWidth-startX,mHeight-startX);
        canvas.drawPath(path,mPaint);
       // canvas.drawPath(path,mPaint);
       // path.close();
        /**
         * 绘制右角标
         */
        path.moveTo(mWidth-startX-20,mHeight-startX-20);
        path.lineTo(mWidth-startX,mHeight-startX);
        path.lineTo(mWidth-startX-20,mHeight-startX+20);
        canvas.drawPath(path,mPaint);
        float allDistance = mWidth-startX-startX;
        float everyDistance = allDistance/4.5f;
        L.i("data-size="+datas.size());
        /**
         * 绘制矩形
         */
        for (int i=1;i<=count;i++) {
            mPaint.reset();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.RED);
            mPaint.setStrokeWidth(15);
            canvas.drawLine(startX + everyDistance*i,mHeight-startX,
                    startX+everyDistance*i,mHeight-startX-10,mPaint);

            mPaint.reset();
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(datas.get(i-1).getColor());
            Rect rect = new Rect();
            rect.left = (int)(startX + everyDistance*i-everyDistance/2+20);
            rect.right = (int)(startX + everyDistance*i+everyDistance/2-20);
            rect.top = (int)(mHeight-startX-(datas.get(i-1).getHeight())*animationValue);
            rect.bottom = (int)(mHeight-startX);
            canvas.drawRect(rect,mPaint);
        }
        /**
         * 绘制垂直距离刻度
         */
        for (int j = 1;j<=15;j++){
            mPaint.reset();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.BLUE);
            mPaint.setStrokeWidth(1);
            float allDistanceY = mHeight-startX-startX;
            float everyDistanceY = allDistance/10.5f;
            canvas.drawLine(startX ,mHeight-startX-everyDistanceY*j,
                    startX+15,mHeight-startX-everyDistanceY*j,mPaint);
            mPaint.setStrokeWidth(2);
            mPaint.setAntiAlias(true);
            mPaint.setTextSize(25);
            Rect bounds = new Rect();
            String text= j*10+"";
            mPaint.getTextBounds(text, 0, text.length(), bounds);
            canvas.drawText(text,startX-60,mHeight-startX-everyDistanceY*j,mPaint);
        }
      /*  canvas.drawLine(startX,startY,centerX,mHeight-50,mPaint);
        canvas.drawLine(centerX,mHeight-50,mWidth-50,mHeight-50,mPaint);*/
    }



    public void startAnimation(){
        valueAnimator.start();
    }
}
