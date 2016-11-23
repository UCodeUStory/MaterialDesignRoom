package com.example.qiyue.materialdesignadvance.demo.radarview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

/**
 * Created by qiyue on 2016/10/22.
 */
public class radarSweepView extends View {
    private SweepGradient sweepGradient;
    private Paint mPaint;
    /**
     * 分成12份
     */
    private int[] colors = {0xED6A0D,0xED6A0D,0x10ED6A0D,0xAED6A0D,0x9ED6A0D,
            0x8ED6A0D,0x7ED6A0D,0x6ED6A0D,0x040140
    ,0x040140,0x4ED6A0D,0x3ED6A0D,0x2ED6A0D,0x1ED6A0D,0xED6A0D};
    public radarSweepView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
    }

    public radarSweepView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public radarSweepView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        sweepGradient = new SweepGradient(getWidth()/2, getHeight()/2, colors, null);
        mPaint.setShader(sweepGradient);
        canvas.drawArc(new RectF(0f, getHeight()/2-getWidth()/2,
                getWidth(), getWidth()+getHeight()/2-getWidth()/2), 180, 35, true, mPaint);
    }

    private int width;
    private int height;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        L.i("w="+w+"h="+h);
    }

    public int getRealWidth(){
        L.i("width="+width);
        return this.width;
    }

    public int getRealHeight(){
        return this.height;
    }
}
