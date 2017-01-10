package com.example.qiyue.materialdesignadvance.demo.paint;

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
public class RadialView extends View {
    private Paint mPaint;
    private RadialGradient radialGradient;
    private SweepGradient sweepGradient;
    private int[] colors = {Color.RED,Color.GREEN,Color.BLUE, Color.YELLOW};

    public RadialView(Context context) {
        super(context);
        init(context);
    }

    public RadialView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RadialView(Context context, AttributeSet attrs, int defStyleAttr) {
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
         @param centerX  The x-coordinate of the center of the radius
         @param centerY  The y-coordinate of the center of the radius
         @param radius   Must be positive. The radius of the circle for this gradient.
         @param colors   The colors to be distributed between the center and edge of the circle
         @param stops    May be <code>null</code>. Valid values are between <code>0.0f</code> and
         <code>1.0f</code>. The relative position of each corresponding color in
         the colors array. If <code>null</code>, colors are distributed evenly
         between the center and edge of the circle.
         @param tileMode The Shader tiling mode
         */
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

	/*	sweepGradient = new SweepGradient(300, 300, colors, null);
		mPaint.setShader(sweepGradient);
		canvas.drawCircle(300, 300, 300, mPaint);*/
    }
}
