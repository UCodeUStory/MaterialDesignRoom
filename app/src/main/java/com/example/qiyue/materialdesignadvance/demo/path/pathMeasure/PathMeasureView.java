package com.example.qiyue.materialdesignadvance.demo.path.pathMeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

/**
 * Created by qiyue on 2016/11/8.
 */
public class PathMeasureView extends View {

    private Paint mPaint;
    private Path path;

    public PathMeasureView(Context context) {
        super(context);
        init(context);
    }

    public PathMeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PathMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        path = new Path();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.translate(100,0);
		Path path = new Path();
		path.lineTo(0, 200);
		path.lineTo(200, 200);
		path.lineTo(200, 0);

		PathMeasure measure = new PathMeasure(path, false);
		PathMeasure measure2 = new PathMeasure(path, true);
		L.i( "length"+measure.getLength());//600
		L.i("length"+ measure2.getLength());//800

     //   forceClosed:不管path绘制的是否关闭，forceClosed=true都会自动测量path包括闭合部分的长度

		canvas.drawPath(path, mPaint);
    }
}
