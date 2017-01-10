package com.example.qiyue.materialdesignadvance.demo2.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.VideoView;

import com.example.qiyue.materialdesignadvance.R;
import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

/**
 * Created by qiyue on 2016/11/9.
 */
public class LearnSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private int mViewWidth;
    private int mViewHeight;
    private Paint mPaint;
    private Paint mArcPaint;

    private final float DEFALUT_RADIUS = 60;
    private final float DEFAULT_TEXTSIZE = 30;
    private final float DEFAULT_RANKTEXTSIZE = 20;

    private int centerRadiusX;
    private int centerRadiusY;
    private float radius;
    private float textSize;
    private RectF mRectF;
    private int startAngle = 10;
    private int dAngle = 40;

    private int rankTextSize ;

    private int []colors;
    private DrawThread drawThread;

    public LearnSurfaceView(Context context) {
        super(context);
        init(context);
    }

    public LearnSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LearnSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this); //设置Surface生命周期回调
        {
            this.radius = dp2px(context,DEFALUT_RADIUS);
            this.textSize = dp2px(context,DEFAULT_TEXTSIZE);
            this.rankTextSize = dp2px(context,DEFAULT_RANKTEXTSIZE);
            this.colors = new int[]{
            R.color.arcColor_1,R.color.arcColor_2,R.color.arcColor_3,
            R.color.arcColor_4,R.color.arcColor_5,R.color.arcColor_6,
            R.color.arcColor_7,R.color.arcColor_8,R.color.arcColor_9};
        }
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);

        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * surfaceView创建时被调用
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.centerRadiusX = getWidth()/2;
        this.centerRadiusY = getHeight()/2;
        mRectF = new RectF(0,(getHeight()-getWidth())/2,getWidth(),(getHeight()+getWidth())/2);
        drawBackground(holder);
        drawThread = new DrawThread(holder,getContext());
        drawThread.startThread();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        drawThread.stopThread();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }



    private void drawBackground(SurfaceHolder holder) {
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            canvas.drawColor(getContext().getResources().getColor(R.color.choujiang_bg));
           // canvas.drawArc(mRectF,startAngle,dAngle,true,mArcPaint);
            mArcPaint.setColor(getContext().getResources().getColor(colors[0]));
            canvas.drawArc(mRectF,startAngle+dAngle*1,dAngle,true,mArcPaint);

            mArcPaint.setColor(getContext().getResources().getColor(colors[1]));
            canvas.drawArc(mRectF,startAngle+dAngle*2,dAngle,true,mArcPaint);
            mArcPaint.setColor(getContext().getResources().getColor(colors[2]));
            canvas.drawArc(mRectF,startAngle+dAngle*3,dAngle,true,mArcPaint);
            mArcPaint.setColor(getContext().getResources().getColor(colors[3]));
            canvas.drawArc(mRectF,startAngle+dAngle*4,dAngle,true,mArcPaint);
            mArcPaint.setColor(getContext().getResources().getColor(colors[4]));
            canvas.drawArc(mRectF,startAngle+dAngle*5,dAngle,true,mArcPaint);
            mArcPaint.setColor(getContext().getResources().getColor(colors[5]));
            canvas.drawArc(mRectF,startAngle+dAngle*6,dAngle,true,mArcPaint);
            mArcPaint.setColor(getContext().getResources().getColor(colors[6]));
            canvas.drawArc(mRectF,startAngle+dAngle*7,dAngle,true,mArcPaint);
            mArcPaint.setColor(getContext().getResources().getColor(colors[7]));
            canvas.drawArc(mRectF,startAngle+dAngle*8,dAngle,true,mArcPaint);
            mArcPaint.setColor(getContext().getResources().getColor(colors[8]));
            canvas.drawArc(mRectF,startAngle+dAngle*9,dAngle,true,mArcPaint);

            canvas.drawCircle(centerRadiusX,centerRadiusY,radius,mPaint);
            mPaint.reset();
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.RED);
            Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
            mPaint.setTypeface( font );
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setTextSize(textSize);
            canvas.drawText("抽奖",centerRadiusX,centerRadiusY+textSize/2,mPaint);

            
            drawText(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(canvas!=null)
                holder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawText(Canvas canvas) {
        Path path = new Path();
        path.moveTo(centerRadiusX,centerRadiusY);
        path.lineTo(centerRadiusX,0);
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(rankTextSize);
        canvas.drawTextOnPath("一等奖",path,180,20,mPaint);
        
        canvas.save();
        canvas.rotate(dAngle*1,centerRadiusX,centerRadiusY);
        path = new Path();
        path.moveTo(centerRadiusX,centerRadiusY);
        path.lineTo(centerRadiusX,0);
        canvas.drawTextOnPath("二等奖",path,180,20,mPaint);
        canvas.restore();


        canvas.save();
        canvas.rotate(dAngle*2,centerRadiusX,centerRadiusY);
        path = new Path();
        path.moveTo(centerRadiusX,centerRadiusY);
        path.lineTo(centerRadiusX,0);
        canvas.drawTextOnPath("三等奖",path,180,20,mPaint);
        canvas.restore();


        canvas.save();
        canvas.rotate(dAngle*3,centerRadiusX,centerRadiusY);
        path = new Path();
        path.moveTo(centerRadiusX,centerRadiusY);
        path.lineTo(centerRadiusX,0);
        canvas.drawTextOnPath("四等奖",path,180,20,mPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(dAngle*4,centerRadiusX,centerRadiusY);
        path = new Path();
        path.moveTo(centerRadiusX,centerRadiusY);
        path.lineTo(centerRadiusX,0);
        canvas.drawTextOnPath("五等奖",path,180,20,mPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(dAngle*5,centerRadiusX,centerRadiusY);
        path = new Path();
        path.moveTo(centerRadiusX,centerRadiusY);
        path.lineTo(centerRadiusX,0);
        canvas.drawTextOnPath("六等奖",path,180,20,mPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(dAngle*6,centerRadiusX,centerRadiusY);
        path = new Path();
        path.moveTo(centerRadiusX,centerRadiusY);
        path.lineTo(centerRadiusX,0);
        canvas.drawTextOnPath("七等奖",path,180,20,mPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(dAngle*7,centerRadiusX,centerRadiusY);
        path = new Path();
        path.moveTo(centerRadiusX,centerRadiusY);
        path.lineTo(centerRadiusX,0);
        canvas.drawTextOnPath("八等奖",path,180,20,mPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(dAngle*8,centerRadiusX,centerRadiusY);
        path = new Path();
        path.moveTo(centerRadiusX,centerRadiusY);
        path.lineTo(centerRadiusX,0);
        canvas.drawTextOnPath("九等奖",path,180,20,mPaint);
        canvas.restore();

    }

    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }


}
