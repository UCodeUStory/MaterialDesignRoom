package com.example.qiyue.materialdesignadvance.demo2.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

/**
 * Created by qiyue on 2016/11/9.
 */
public class DrawThread extends Thread{

    SurfaceHolder surfaceHolder;
    Context context;
    volatile boolean isRunning;
    float radius = 10f;
    Paint paint;

    public DrawThread(SurfaceHolder surfaceHolder,Context context){

        this.surfaceHolder = surfaceHolder;
        this.context = context;
        isRunning = false;

        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
    }


    public void startThread(){
        isRunning = true;
        this.start();
    }

    public void stopThread(){
        isRunning = false;
    }

    @Override
    public void run() {


        while(isRunning){
            Canvas c = null;
            try{
                synchronized (surfaceHolder) {
                    c = surfaceHolder.lockCanvas();
                    if (c!=null) {
                        doDraw(c);
                        /**
                         * 通过它来控制帧数执行一次绘制后休息50ms
                         * 也即是1000ms会执行20帧，
                         * 也就控制了1秒20帧
                         */
                        Thread.sleep(50);

                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                surfaceHolder.unlockCanvasAndPost(c);
            }

        }

    }

    public void doDraw(Canvas c){
/*

        //这个很重要，清屏操作，清楚掉上次绘制的残留图像

        c.drawCircle(0,0, radius++, paint);

        if(radius > 100){
            radius = 10f;
        }
*/

    }

}

