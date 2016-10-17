package com.example.qiyue.materialdesignadvance.demo.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qiyue on 2016/10/17.
 */
public class LinearGradientView extends View {
    private Paint mPaint;
    private int[] colors = {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};
    private LinearGradient mLinearGradient;
    private LinearGradient linearGradient2;

    public LinearGradientView(Context context) {
        super(context);
        init(context);
    }

    public LinearGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LinearGradientView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        /**
         * float x0, float y0, 渐变的开始坐标x,y
         * float x1, float y1, 渐变的最终坐标x1,y1
         * int colors[], 渐变的颜色种类
         * float positions[], 每种渐变颜色的位置
           TileMode tile  也是3中方式
           TileMode.CLAMP 就是最后一像素重复
           TileMode.REPEAT 重复整个
           TileMode.MIRROR 镜面反转重复
         */
        mLinearGradient = new LinearGradient(0,50,400,50,colors,null,Shader.TileMode.CLAMP);
        linearGradient2 = new LinearGradient(0,50,400,200,colors,null, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.save();
        mPaint.setShader(mLinearGradient);
        canvas.drawRect(0,0,400,100,mPaint);
       // canvas.restore();
       // mPaint.reset();
        mPaint.setShader(linearGradient2);
        canvas.drawRect(0,120,400,200,mPaint);
    }
}
