package com.example.qiyue.materialdesignadvance.demo.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qiyue on 2016/10/25.
 */
public class BlurView extends View {
    public BlurView(Context context) {
        super(context);
        init(context);
    }

    public BlurView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BlurView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    Paint mPaint = new Paint();
    private void init(Context context) {
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
