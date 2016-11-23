package com.example.qiyue.materialdesignadvance.demo.paint;

import android.content.Context;
import android.graphics.ComposeShader;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qiyue on 2016/10/21.
 */


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qiyue on 2016/10/21.
 */
public class ComposeShaderView extends View {
    private Paint mPaint;
    private RadialGradient radialGradient;
    private SweepGradient sweepGradient;
    private int[] colors = {Color.RED,Color.GREEN,Color.BLUE, Color.YELLOW};
    ComposeShader composeShader;
    public ComposeShaderView(Context context) {
        super(context);
        init(context);
    }

    public ComposeShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ComposeShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
        /**
         * @param centerX  圆心x
         @param centerY  圆心y
         radius 半径
         stop  颜色相对位置穿null就是均匀分布
         */
        radialGradient = new RadialGradient(300, 300, 300, colors,
                null, Shader.TileMode.CLAMP);
        mPaint.setShader(radialGradient);
        canvas.drawCircle(300, 300, 300, mPaint);

		sweepGradient = new SweepGradient(300, 300, colors, null);
		mPaint.setShader(sweepGradient);
		canvas.drawCircle(300, 300, 200, mPaint);
        composeShader = new ComposeShader(radialGradient, sweepGradient, PorterDuff.Mode.SRC_OVER);
        mPaint.setShader(composeShader);
        canvas.drawRect(0, 0, 800, 1000, mPaint);
    }
}

