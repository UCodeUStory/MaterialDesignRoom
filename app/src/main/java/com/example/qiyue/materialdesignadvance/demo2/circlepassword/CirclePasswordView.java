package com.example.qiyue.materialdesignadvance.demo2.circlepassword;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

/**
 * Created by qiyue on 2016/11/20.
 */
public class CirclePasswordView extends View implements View.OnTouchListener{

    public static final int ONE_RADIUS = 100;
    public static final int TWO_RADIUS = 160;
    public static final float STOKE = 5;
    public static final float LINE_LENGTH = 10;
    public static final float LINE_STROKE = 3;
    public static final float POINT_STROKE = 2;
    public static final float IN_RADIUS = 40;
    public static final float P_LINE_LENGTH = 60;

    private Paint cPaint;
    private Paint lPaint;
    private Paint pPaint;
    private float mViewWidth;
    private float mViewHeight;
    private float twoRadius;
    private float oneRaduis;
    private float inRadius;
    private float stoke;
    private DisplayMetrics mMetrics;
    private float linelength ;
    private float lineStroke;
    private float pointStroke;
    private float currentDegrees;
    private float currentTwoDegrees;
    private float plinelength;
    private float slop = 2;
    float mLastX = 0;
    float mLastY = 0;
    float centerX;
    float centerY;



    public CirclePasswordView(Context context) {
        super(context);
        init(context,null);
    }

    public CirclePasswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public CirclePasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.setOnTouchListener(this);
        mMetrics = this.getResources().getDisplayMetrics();
        {
            oneRaduis = dp2px(ONE_RADIUS);
            stoke = dp2px(STOKE);
            linelength = dp2px(LINE_LENGTH);
            lineStroke = dp2px(LINE_STROKE);
            pointStroke = dp2px(POINT_STROKE);
            inRadius = dp2px(IN_RADIUS);
            currentDegrees = 0;
            currentTwoDegrees = -45;
            twoRadius = dp2px(TWO_RADIUS);
            plinelength = dp2px(P_LINE_LENGTH);
        }
        cPaint = new Paint();
        cPaint.setColor(Color.BLACK);
        cPaint.setStyle(Paint.Style.STROKE);
        cPaint.setStrokeWidth(stoke);
        cPaint.setAntiAlias(true);

        lPaint = new Paint();
        lPaint.setColor(Color.BLACK);
        lPaint.setStyle(Paint.Style.STROKE);
        lPaint.setStrokeWidth(lineStroke);
        lPaint.setAntiAlias(true);

        pPaint = new Paint();
        pPaint.setColor(Color.RED);
        pPaint.setStyle(Paint.Style.STROKE);
        pPaint.setStrokeWidth(pointStroke);
        pPaint.setAntiAlias(true);
        pPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawTwoCircle(canvas);
        drawOneCircle(canvas);
        canvas.drawCircle(mViewWidth/2,mViewHeight/2,inRadius,cPaint);

    }

    private void drawTwoCircle(Canvas canvas) {
        canvas.drawCircle(mViewWidth/2,mViewHeight/2,twoRadius,cPaint);

        for (int i=0;i<120;i++){
            canvas.save();
            canvas.rotate(i*3,mViewWidth/2,mViewHeight/2);
            float startX = mViewWidth / 2;
            float startY = mViewHeight / 2 - twoRadius;
            float stopX = mViewWidth / 2;
            float stopY;
            if (i%5==0) {
                stopY = mViewHeight / 2 - twoRadius + linelength+10;
            }else{
                stopY = mViewHeight / 2 - twoRadius + linelength;
            }
            canvas.drawLine(startX, startY, stopX, stopY, lPaint);
            canvas.restore();
        }
        float onePointStartX = mViewWidth / 2;
        float onePointStartY = mViewHeight / 2 - twoRadius +15;
        float onePointEndX = mViewWidth / 2;
        float onePointEndY = mViewHeight / 2 - twoRadius + plinelength;

        canvas.save();
        canvas.rotate(currentTwoDegrees,mViewWidth/2,mViewHeight/2);
        canvas.drawLine(onePointStartX,onePointStartY,onePointEndX,onePointEndY,pPaint);
        canvas.drawLine(onePointStartX,onePointStartY,onePointEndX+20,onePointEndY-20,pPaint);
        canvas.drawLine(onePointStartX,onePointStartY,onePointEndX-20,onePointEndY-20,pPaint);
        canvas.restore();

    }

    private void drawOneCircle(Canvas canvas) {
        canvas.drawCircle(mViewWidth/2,mViewHeight/2,oneRaduis,cPaint);


        /**
         * 绘制刻度
         */

        for (int i=0;i<120;i++){
            canvas.save();
            canvas.rotate(i*3,mViewWidth/2,mViewHeight/2);
            float startX = mViewWidth / 2;
            float startY = mViewHeight / 2 - oneRaduis;
            float stopX = mViewWidth / 2;
            float stopY;
            if (i%5==0) {
                stopY = mViewHeight / 2 - oneRaduis + linelength+10;
            }else{
                stopY = mViewHeight / 2 - oneRaduis + linelength;
            }
            canvas.drawLine(startX, startY, stopX, stopY, lPaint);
            canvas.restore();
        }
        float onePointStartX = mViewWidth / 2;
        float onePointStartY = mViewHeight / 2 - oneRaduis +15;
        float onePointEndX = mViewWidth / 2;
        float onePointEndY = mViewHeight / 2 - oneRaduis + plinelength;

        canvas.save();
        canvas.rotate(currentDegrees,mViewWidth/2,mViewHeight/2);
        canvas.drawLine(onePointStartX,onePointStartY,onePointEndX,onePointEndY,pPaint);
        canvas.drawLine(onePointStartX,onePointStartY,onePointEndX+20,onePointEndY-20,pPaint);
        canvas.drawLine(onePointStartX,onePointStartY,onePointEndX-20,onePointEndY-20,pPaint);
        canvas.restore();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mViewWidth = w;
        this.mViewHeight = h;
        centerX = mViewWidth/2;
        centerY = mViewHeight/2;
    }

    /**
     * Dp to px
     *
     * @param dp dp
     * @return px
     */
    private float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, this.mMetrics);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                L.i("down-down");
                mLastX = event.getX();
                mLastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                float dx = x - mLastX;
                float dy = y - mLastY;
                mLastX = x;
                mLastY = y;
                L.i("move"+"dx="+dx+"dy="+dy);
                movePoint(dx,dy,x,y);
                checkPassword();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return false;
    }
    private boolean isright = false;
    private void checkPassword() {
        if (currentDegrees==-45 && currentTwoDegrees ==45){
            if (listener!=null){
                if (!isright) {
                    isright = true;
                    listener.rightPassword();

                }
            }
        }
    }

    private void movePoint(float dx, float dy, float x, float y) {

        if (isRange(mLastX,mLastY)) {
            realMove(dx,dy);
            L.i("in one range");
            invalidate();
        }else if (isRangeTwo(mLastX,mLastY)){
            realMoveTwo(dx,dy);
            L.i("in two range");
            invalidate();
        }else{
            L.i("is not range");
        }
        // return true;
    }

    private void realMoveTwo(float dx, float dy) {
        /**
         * 判断在第一象限
         */
        if (mLastX>(mViewWidth/2) && mLastY<(mViewHeight/2)){
            if (dx>slop || dy>slop){
                L.i("dx顺时针=>>>>>>>>>>>>>>"+dx+"dy="+dy);
                this.currentTwoDegrees = currentTwoDegrees + 3;
            }else if (dx<-slop|| dy<-slop){
                L.i("dx逆时针=>>>>>>>>>>>>>>"+dx+"dy="+dy);
                this.currentTwoDegrees = currentTwoDegrees - 3;
            }
        }
        /**
         * 判断在第二象限
         */
        if (mLastX<(mViewWidth/2) && mLastY<(mViewHeight/2)){
            if (dx>slop || dy<-slop){
                this.currentTwoDegrees = currentTwoDegrees + 3;
            }else if (dx<-slop || dy>slop){
                this.currentTwoDegrees = currentTwoDegrees - 3;
            }
        }
        /**
         * 判断在第三象限
         */
        if (mLastX<(mViewWidth/2) && mLastY>(mViewHeight/2)){
            if (dx<-slop || dy<-slop){
                this.currentTwoDegrees = currentTwoDegrees + 3;
            }else if (dx>slop || dy>slop){
                this.currentTwoDegrees = currentTwoDegrees - 3;
            }
        }
        /**
         * 判断在第四象限
         */
        if (mLastX>(mViewWidth/2) && mLastY>(mViewHeight/2)){
            if (dx<-slop || dy> slop){
                this.currentTwoDegrees = currentTwoDegrees + 3;
            }else if (dx>slop || dy<-slop){
                this.currentTwoDegrees = currentTwoDegrees - 3;
            }
        }
    }

    private void realMove(float dx,float dy) {
        /**
         * 判断在第一象限
         */
        if (mLastX>(mViewWidth/2) && mLastY<(mViewHeight/2)){
            if (dx>slop || dy>slop){
                L.i("dx顺时针=>>>>>>>>>>>>>>"+dx+"dy="+dy);
                this.currentDegrees = currentDegrees + 3;
            }else if (dx<-slop|| dy<-slop){
                L.i("dx逆时针=>>>>>>>>>>>>>>"+dx+"dy="+dy);
                this.currentDegrees = currentDegrees - 3;
            }
        }
        /**
         * 判断在第二象限
         */
        if (mLastX<(mViewWidth/2) && mLastY<(mViewHeight/2)){
            if (dx>slop || dy<-slop){
                this.currentDegrees = currentDegrees + 3;
            }else if (dx<-slop || dy>slop){
                this.currentDegrees = currentDegrees - 3;
            }
        }
        /**
         * 判断在第三象限
         */
        if (mLastX<(mViewWidth/2) && mLastY>(mViewHeight/2)){
            if (dx<-slop || dy<-slop){
                this.currentDegrees = currentDegrees + 3;
            }else if (dx>slop || dy>slop){
                this.currentDegrees = currentDegrees - 3;
            }
        }
        /**
         * 判断在第四象限
         */
        if (mLastX>(mViewWidth/2) && mLastY>(mViewHeight/2)){
            if (dx<-slop || dy> slop){
                this.currentDegrees = currentDegrees + 3;
            }else if (dx>slop || dy<-slop){
                this.currentDegrees = currentDegrees - 3;
            }
        }
    }

    private boolean isRangeTwo(float mLastX, float mLastY) {
        if (oneRaduis*oneRaduis<((mLastX-centerX)*(mLastX-centerX)
                + (mLastY-centerY)*(mLastY-centerY))
                && ((mLastX-centerX)*(mLastX-centerX)
                + (mLastY-centerY)*(mLastY-centerY))<twoRadius*twoRadius){
            return true;
        }
        return false;
    }

    private boolean isRange(float mLastX, float mLastY) {
        L.i("inRadius*inRadius="+inRadius*inRadius);
        L.i("imLastX*mLastX+mLastY*mLastY="+((mLastX-centerX)*(mLastX-centerX)
                + (mLastY-centerY)*(mLastY-centerY)));
        L.i("oneRaduis*oneRaduis="+oneRaduis*oneRaduis);
        if (inRadius*inRadius<((mLastX-centerX)*(mLastX-centerX)
                 + (mLastY-centerY)*(mLastY-centerY))
                && ((mLastX-centerX)*(mLastX-centerX)
                + (mLastY-centerY)*(mLastY-centerY))<oneRaduis*oneRaduis){
            return true;
        }
        return false;
    }

    public void setListener(PassWordListener listener) {
        this.listener = listener;
    }

    PassWordListener listener;

    public interface PassWordListener{
        public void rightPassword();
    }

}
