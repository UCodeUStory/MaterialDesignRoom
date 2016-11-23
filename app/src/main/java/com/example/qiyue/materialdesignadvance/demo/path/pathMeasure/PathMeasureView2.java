package com.example.qiyue.materialdesignadvance.demo.path.pathMeasure;

/**
 * Created by qiyue on 2016/11/8.
 */

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
public class PathMeasureView2 extends View {
    private int mViewHeight;
    private int mViewWidth;
    private Paint mPaint;
    private Path path;

    public PathMeasureView2(Context context) {
        super(context);
        init(context);
    }

    public PathMeasureView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PathMeasureView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        path = new Path();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.translate(mViewWidth/2, mViewHeight/2);

        //-------------nextContour---------
		Path path = new Path();

        Path path2 = new Path();
		//多路径的效果需要关闭硬件加速！！
		path.addRect(-200, -200, 200, 200, Path.Direction.CW);
		path2.addRect(-100, -100, 100, 100, Path.Direction.CW);
      //  android:hardwareAccelerated="false" 关闭硬件加速才有效果
        path.addPath(path2);
		PathMeasure measure = new PathMeasure(path, false);
		float length = measure.getLength();
		boolean nextContour = measure.nextContour();//获取下一个路径，有可能没有多个路径了，返回false
		float length2 = measure.getLength();
		L.i("new length1:"+length);
		L.i("new length2:"+length2);

		canvas.drawPath(path, mPaint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewHeight = h;
        mViewWidth = w;
    }
}