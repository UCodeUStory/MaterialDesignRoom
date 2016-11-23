package com.example.qiyue.materialdesignadvance.demo.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qiyue on 2016/10/21.
 */
public class SweepView extends View {
    private Paint mPaint;
    private RadialGradient radialGradient;
    private SweepGradient sweepGradient;
    private SweepGradient sweepGradient2;


    private int[] colors = {Color.RED,Color.GREEN,Color.BLUE, Color.YELLOW};

    public SweepView(Context context) {
        super(context);
        init(context);
    }

    public SweepView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SweepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

		sweepGradient = new SweepGradient(300, 300, colors, null);
		mPaint.setShader(sweepGradient);
		canvas.drawCircle(300, 300, 300, mPaint);
        mPaint.reset();
        sweepGradient2 = new SweepGradient(300, 1000, colors, null);
        mPaint.setShader(sweepGradient2);
        canvas.drawArc(new RectF(0f,700f,600f,1300f),180,280,true,mPaint);
    }
}
