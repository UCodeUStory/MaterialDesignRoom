package com.example.qiyue.materialdesignadvance.demo.chart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

/**
 * Created by qiyue on 2016/10/25.
 */
public abstract class BaseChartView extends View {
    protected int mViewWidth,mViewHeight;
    protected int mWidth,mHeight;

    public BaseChartView(Context context) {
        super(context);
    }

    public BaseChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * getCurrentWidth()是我们自己要的最大宽度，一般可以当成可变的配置参数
         *
         */
        int w = getCurrentWidth() + getPaddingLeft() + getPaddingRight();
        int h = getCurrentHeight() + getPaddingTop() + getPaddingBottom();
        L.i("w="+w);
        L.i("h="+h);
        /**  w,h 是我们自己放置内容的宽高，而这个内容的宽高
         *  测量，父容器允许的宽高，还有就是子控件
         *  resolveSizeAndState ，很棒的一个方法
         */
        int widthSize = resolveSizeAndState(w,widthMeasureSpec,0);
        int heightSize = resolveSizeAndState(h,heightMeasureSpec,0);
        L.i("widthSize="+widthSize);
        L.i("heightSize="+heightSize);
      //  int finalWidth = Math.max(getSuggestedMinimumWidth(), widthSize);
      //  int finalHeight = Math.max(getSuggestedMinimumHeight(),heightSize);
        setMeasuredDimension(widthSize,heightSize);
    }

    abstract int getCurrentWidth() ;

    abstract int getCurrentHeight();


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        mWidth = mViewWidth - getPaddingLeft() - getPaddingRight();
        mHeight = mViewHeight - getPaddingTop() - getPaddingBottom();
        L.i("mViewwidth="+mViewWidth);
        L.i("mViewHeight="+mViewHeight);

    }


}
