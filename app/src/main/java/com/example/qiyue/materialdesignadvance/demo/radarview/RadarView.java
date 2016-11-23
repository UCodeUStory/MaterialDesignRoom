package com.example.qiyue.materialdesignadvance.demo.radarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.example.qiyue.materialdesignadvance.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiyue on 2016/10/21.
 */
public class RadarView extends View {
    private int mCurrentAlpha = 0;
    private SweepGradient sweepGradient;
    private int[] colors = {0xFFF73703,0xFFED6A0D};
    private List<CircleBean> circleBeanList = new ArrayList<>();
    private boolean isArcCreate = false;

    public RadarView(Context context) {
        super(context);
        init(context);
    }
    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private Paint mPaint;
    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        int color = getResources().getColor(R.color.radar);
        mCurrentAlpha = Color.alpha(color);
        mPaint.setColor(getResources().getColor(R.color.radar));
        CircleBean circleBean = new CircleBean(mCurrentAlpha,50);
        circleBeanList.add(circleBean);

    }
    private int startAngle = 180;
    private int endAngle = 35;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(getResources().getColor(R.color.radar));
        for (int i=0;i<circleBeanList.size();i++){
            CircleBean circleBean = circleBeanList.get(i);
            if (circleBean.alpha>0) {
                circleBean.alpha = circleBean.alpha - 1;
                mPaint.setAlpha(circleBean.alpha);
                circleBean.radias = circleBean.radias + 3;
                canvas.drawCircle(width/2, height/2, circleBean.radias, mPaint);
            }
        }
        /**
         * 如果最后一个半径大于150就增加一个
         */
        if (circleBeanList.get(circleBeanList.size()-1).radias>150){
            CircleBean newcircleBean = new CircleBean(mCurrentAlpha,50);
            circleBeanList.add(newcircleBean);
        }
        /**
         * 如果第一个alpha为0去除
         */
        if (circleBeanList.get(0).alpha<=0){
            circleBeanList.remove(0);
        }

   /*     mPaint.reset();
        *//**
         * 圆心坐标
         *//*
        //sweepGradient = new SweepGradient(width/2, height/2, colors, null);
        //mPaint.setShader(sweepGradient);
        if (startAngle>360){
            startAngle = 0;
        }
        startAngle = startAngle +5;
        canvas.drawArc(new RectF(0f, height/2-width/2, width, width+height/2-width/2), startAngle, 45, true, mPaint);
        mPaint.reset();*/

        invalidate();
    }
    private int width;
    private int height;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
    }

    class CircleBean {
        public int alpha;
        public int radias;
        public CircleBean(int alpha,int radias){
            this.alpha = alpha;
            this.radias = radias;
        }
    }
}
