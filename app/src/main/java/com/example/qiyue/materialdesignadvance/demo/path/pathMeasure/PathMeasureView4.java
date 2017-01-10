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
public class PathMeasureView4 extends View {

    private Paint mPaint;
    private Path path;

    public PathMeasureView4(Context context) {
        super(context);
        init(context);
    }

    public PathMeasureView4(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PathMeasureView4(Context context, AttributeSet attrs, int defStyleAttr) {
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

        //------------getPosTan--------------------
        Path path = new Path();
        path.addCircle(0, 0, 300, Path.Direction.CW);

        PathMeasure measure = new PathMeasure(path, false);
        float[] pos = new float[2];
        float[] tan = new float[2];//tan=y/x
        /**
         *  得到某一长度时的点和正切值
         */
        measure.getPosTan(measure.getLength()/4, pos , tan );
        L.i("position:x-"+pos[0]+", y-"+pos[1]);
        L.i( "tan:x-"+tan[0]+", y-"+tan[1]);

        canvas.drawPath(path, mPaint);
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