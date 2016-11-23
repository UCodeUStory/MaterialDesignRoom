package com.example.qiyue.materialdesignadvance.demo.paint.filter;

/**
 * Created by qiyue on 2016/10/20.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.qiyue.materialdesignadvance.R;





public class FilterView_Two extends View {


    private Bitmap bitmap;
    private Paint paint;

    public FilterView_Two(Context context) {
        super(context);
        init(context);
    }

    public FilterView_Two(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FilterView_Two(Context context, AttributeSet attrs, int defStyleAttr) {
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

        /**
         * direction, 指定长度为xxx的数组标量[x,y,z]，用来指定光源的位置
         * ambient, 指定周边背景光源（0~1）
         * specular, 指镜面反射系数
         * blurRadius 指定模糊半径
         */
		paint.setMaskFilter(new EmbossMaskFilter(new float[]{80,80,30}, 0.2f, 60, 180));
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
