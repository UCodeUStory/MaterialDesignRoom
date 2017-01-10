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
import android.view.View;

import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;


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
import android.view.View;

import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

/**
 * Created by qiyue on 2016/11/8.
 */
public class PathMeasureView3 extends View {

    private Paint mPaint;
    private Path path;

    public PathMeasureView3(Context context) {
        super(context);
        init(context);
    }

    public PathMeasureView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PathMeasureView3(Context context, AttributeSet attrs, int defStyleAttr) {
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

        //----------getSegment:截取片段---------------
		Path path = new Path();
		//多路径的效果需要关闭硬件加速！！
		path.addRect(0, 0, 200, 200, Path.Direction.CW);

		PathMeasure measure = new PathMeasure(path, false);
		float length = measure.getLength();
		L.i( "length1:"+length);
		canvas.drawPath(path, mPaint);

		Path dst = new Path();
		dst.lineTo(-300, -300);

		/**
         * startWithMoveTo:false，代表该起始点是否位上一个的结束点(是否保持连续性)。
         * **/
		measure.getSegment(200, 600, dst , false);
        mPaint.setColor(Color.GREEN);

		canvas.drawPath(dst, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewHeight = h;
        mViewWidth = w;
    }

    private int mViewHeight;
    private int mViewWidth;
}