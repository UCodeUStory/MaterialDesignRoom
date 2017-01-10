package com.example.qiyue.materialdesignadvance.demo.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.qiyue.materialdesignadvance.R;

/**
 * Created by qiyue on 2016/10/17.
 */
public class BitmapShaderView extends View {

    private Paint mPaint;
    private Bitmap bitmap;

    private int bitmapWidth;
    private int bitmapHeight;

    private BitmapShader bitmapShader;
    private Matrix matrix;

    public BitmapShaderView(Context context) {
        super(context);
        init(context);
    }

    public BitmapShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BitmapShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        bitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.test_circle)).getBitmap();
        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();
        /**
         * 图片渲染器，第一个参数就是一个bitmap，第二个参数是x方向上（当bitmap小于宽度的时候显示策略）
         * 第三个参数是y方向上显示策略（也是当bitmap小于宽度的时候显示策略）
         * TileMode.CLAMP 去最后一像素进行平铺
         * TileMode.REPEAT 重复
         * TileMode.MIRROR 镜面反转重复
         */
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        mPaint.setShader(bitmapShader);
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置像素矩阵，来调整大小，为了解决宽高不一致的问题。
        /**
         * 取布局里面的宽高的最小值,取图片宽高的最大值，运算出缩放比例
         */
		float scale = Math.min(getWidth(), getHeight())*1.0f/Math.max(bitmapWidth, bitmapHeight);
		matrix.setScale(scale, scale);//缩放比例
		bitmapShader.setLocalMatrix(matrix);
        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2,mPaint);


        //通过shapeDrawable也可以实现圆形图片
//		ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
//		shapeDrawable.getPaint().setShader(bitmapShader);
//		shapeDrawable.setBounds(0, 0, width, width);
//		shapeDrawable.draw(canvas);
    }
}
