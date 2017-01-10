package com.example.qiyue.materialdesignadvance.demo.paint.filter;

/**
 * Created by qiyue on 2016/10/20.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.qiyue.materialdesignadvance.R;



public class  FilterView_Four extends View {


    private Bitmap bitmap;
    private Paint paint;
    private int flag = 0;
    public  FilterView_Four(Context context) {
        super(context);
        init(context);
    }

    public  FilterView_Four(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public  FilterView_Four(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        bitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.test_filter_1);
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        /**模糊遮罩滤镜效果
         * BlurMaskFilter.Blur.INNER
         * BlurMaskFilter.Blur.NORMAL
         * BlurMaskFilter.Blur.OUTER
         * BlurMaskFilter.Blur.SOLID
         */

        canvas.drawBitmap(bitmap, null,
                new RectF(0, 0, 400, 400*bitmap.getHeight()/bitmap.getWidth()), paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }
    private int width = 0;
    private int height = 0;
}

