package com.example.qiyue.materialdesignadvance.demo.path.pathMeasure;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by qiyue on 2016/11/8.
 */

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.qiyue.materialdesignadvance.R;
import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

/**
 * Created by qiyue on 2016/11/8.
 *
 * 实现思路
 * 1.先绘制完整path
 * 2.通过开启动画获得每一个地方的点和正切值
 *
 *通过getSegment 可以实现动态画出路径（路径动画不需要这个方法）
 */
public class ZanView extends View {
    private Path path;
    private Paint mPaint;
    private PathMeasure pathMeasure;
    private Bitmap bitmap;
    private Paint paint;
    private Matrix matrix;
    private int mViewHeight;
    private int mViewWidth;
    private float length;
    private float factor;
    private ValueAnimator animator;

    public ZanView(Context context) {
        super(context);
        init(context);
    }
    public ZanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ZanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(10);

        path = new Path();
        initBitmap(context);
        matrix = new Matrix();
        animator = ValueAnimator.ofFloat(0,1);
    }

    private void initBitmap(Context context) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dianzan,options );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //drawOne(canvas);
        drawTwo(canvas);
    }

    private void drawTwo(Canvas canvas) {
        matrix.reset();
        path.reset();

        canvas.translate(mViewWidth/2,(mViewHeight*3)/4);
        path.moveTo(0,0);
        path.rQuadTo(100,-100,0,-200);
        path.rQuadTo(-100,-100,0,-200);
        path.rQuadTo(100,-100,0,-200);
        /**
         * 之前范二一定要在添加路径后开始测量
         */
        pathMeasure = new PathMeasure(path, false);
        length = pathMeasure.getLength();

        float []pos = new float[2];
        float []tan = new float[2];
        pathMeasure.getPosTan(length*factor,pos,tan);
        /**
         * 计算图片的矩阵信息，包含位置，旋转放大等信息
         */
        //方法一：自己计算
        //将tan值通过反三角函数得到对应的弧度；然后将弧度转换成度数degree
        float degrees = (float) (Math.atan2(tan[1], tan[0])*180f/Math.PI);
        /**
         * 这里旋转的点比标准，移动的点要减去这个，才会绕中心旋转
         */
        /**
         * 调整图片方向,绕中间选转*/
       // matrix.postRotate(degrees , bitmap.getWidth()/2, bitmap.getHeight());
        /**
         * 调整图片位置*/
        matrix.postTranslate( pos[0]-bitmap.getWidth()/2, pos[1]-bitmap.getHeight());
        //matrix.setScale(1f+factor,1f+factor);
        L.i("pos[0]="+pos[0]+"pos[1]="+pos[1]);
        //canvas.drawPath(path,paint);
        canvas.drawBitmap(bitmap, matrix, paint);
        // canvas.drawBitmap(bitmap, pos[0]-bitmap.getWidth()/2, pos[1]-bitmap.getHeight()/2 ,paint);

    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewHeight = h;
        mViewWidth = w;
    }

    public void startAnimation(){
        /**
         * 保证衔接好，最大值是一个波长
         */
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
        /**
         * 实现循环
         */
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                factor = (float) animation.getAnimatedValue();
                L.i("factor="+factor);
                postInvalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //  startAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        L.i("onDetachedFromWindow");
        animator.cancel();
    }
}
