package com.example.qiyue.materialdesignadvance.demo.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qiyue on 2016/11/7.
 */
public class CurveView extends View {

    private Paint paint;
    private Path path;
    private int wavelength = 800;
    private int count = 1;
    private int currentHeight = 50;
    public CurveView(Context context) {
        super(context);
        init(context);
    }

    public CurveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CurveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        float inflexionPointX = 200;
        float inflexionPointY = 0;
        path.moveTo(0,300);
        path.quadTo(inflexionPointX,inflexionPointY,400,300);
        /**
         * 连续绘制二阶贝塞尔曲线会把上一个终点作为起点来绘制
         */
        path.quadTo(600,600,800,300);
       // drawText(canvas,"连续绘制二阶贝塞尔曲线",0,60);
        drawText(canvas,"P0(0,300)",10,300);
        drawText(canvas,"P1(200,0)",200,40);
        drawText(canvas,"P2(400,300)",400,300);
        drawText(canvas,"P3(600,600)",600,600);
        drawText(canvas,"P4(800,300)",760,300);
        drawPoint(canvas,0,300);
        drawPoint(canvas,200,0);
        drawPoint(canvas,400,300);
        drawPoint(canvas,600,600);
        drawPoint(canvas,800,300);
        canvas.drawPath(path,paint);



        drawText(canvas,"绘制三阶贝塞尔曲线",0,700);
        path.reset();
        path.moveTo(0,1050);
        path.cubicTo(150,750,400,750,500,1050);
        drawPoint(canvas,0,1050);
        drawPoint(canvas,150,750);
        drawPoint(canvas,400,750);
        drawPoint(canvas,500,1050);
        canvas.drawPath(path,paint);

    }

    private void drawPoint(Canvas canvas,int x,int y){
        paint.reset();
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(30);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(x,y,paint);
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
    }


    private void drawText(Canvas canvas,String text,int x,int y){
        paint.reset();
        paint.setColor(Color.BLUE);
        paint.setTextSize(40);
        paint.setAntiAlias(true);
        canvas.drawText(text,x,y,paint);
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

    }
}
