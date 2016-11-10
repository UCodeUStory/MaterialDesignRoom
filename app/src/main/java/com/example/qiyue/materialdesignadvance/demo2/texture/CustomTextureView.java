package com.example.qiyue.materialdesignadvance.demo2.texture;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.TextureView;

import com.example.qiyue.materialdesignadvance.R;
import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by qiyue on 2016/11/9.
 */

public class CustomTextureView extends TextureView
        implements TextureView.SurfaceTextureListener {


    private int timeHour;
    private int timeMinute;
    private int timeSecond;

    private int mViewWidth;
    private int mViewHeight;

    private boolean runningState = false;
    private DrawThread mThread;
    private volatile long lastRecordTime = 0L;
    private Paint timePaint;
    private Paint backgroundPaint;
    private long mCurrentMilliseconds = 0;

    private static final long ONE_HOUR = 1000 * 60 * 60L;
    private static final long ONE_MINUTE = 1000 * 60L;
    private static final long ONE_SECOND = 1000L;
    /**
     * 倒计时间隔
     */
    private static final int COUNT_DOWN_INTERVAL = 1000;
    private final Calendar mCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+00:00"));
    private DisplayMetrics mMetrics;

    private int paddingLeft;
    private int paddingTop;
    private int paddingBottom;
    private int paddingRight;

    private final float DEFAULT_WIDTH = 150f;
    private final float DEFAULT_HEIGHT = 50f;
    private final float DEFAULT_TEXTSIZE = 25f;
    private final float DEFAULT_STOKE_SIZE = 2.5f;
    private final float DEFAULT_BG_RECTF_LEFT = DEFAULT_STOKE_SIZE;
    private final float DEFAULT_BG_RECTF_TOP = DEFAULT_STOKE_SIZE;
    private final float DEFAULT_BG_RECTF_RIGHT = DEFAULT_BG_RECTF_LEFT + DEFAULT_TEXTSIZE*2;
    private final float DEFAULT_BG_RECTF_BOTTOM = DEFAULT_BG_RECTF_TOP + DEFAULT_TEXTSIZE*2;
    private final float DEFAULT_TIME_DISTANCE = 2.5f;
    private final float DEFAULT_TEXT_PADDING = 10f;
    private final float DEFAULT_RECT_RADIUS_SIZE = 2.5f;
    private final int DEFAULT_TEXT_COLOR = Color.BLACK;
    private final int DEFAULT_BACKGROUND_COLOR = Color.WHITE;
    private final float DEFAULT_COLON_LEFT_PADDING = 5.25f;
    private final float DEFAULT_COLON_RIGHT_PADDING = 5.25f;
    private final int DEFAULT_RECT_COLOR = Color.BLUE;

    private float defaultWrapContentWidth;
    private float defaultWrapContentHeight;
    private float textSize;
    private float backgroundStokeSize;
    private float bg_rectf_left;
    private float bg_rectf_top;
    private float bg_rectf_right;
    private float bg_rectf_bottom;
    private float bg_distance;
    private float textPadding;
    private float rectRadiusSize;
    private int textColor;
    private int backgroundColor;
    private float colonLeftPadding;
    private float colonRightPadding;
    private int rectColor;

    private RectF firstBackgroundRectF;
    private RectF secondBackgroundRectF;
    private RectF threeBackgroundRectF;

    public CustomTextureView(Context context) {
        super(context);
        init(context,null);
    }

    public CustomTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public CustomTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //TODO 初始化
        mMetrics = this.getResources().getDisplayMetrics();
        {   /**
            * initSize  先假设都是默认值
            */
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CountDownView);
            textSize = a.getDimension(R.styleable.CountDownView_textSize,sp2px(DEFAULT_TEXTSIZE));//这个是自定属性
            backgroundStokeSize = a.getDimension(R.styleable.CountDownView_rectStrokeSize,sp2px(DEFAULT_STOKE_SIZE));//这个是自定义属性

            textPadding =a.getDimension(R.styleable.CountDownView_textPadding,dp2px(DEFAULT_TEXT_PADDING)); //这是自定义属性
            bg_rectf_left = backgroundStokeSize ;
            bg_rectf_top = backgroundStokeSize ;
            bg_rectf_right = bg_rectf_left*2 + textSize + textPadding*2;
            bg_rectf_bottom = bg_rectf_top + textSize + textPadding*2;
            rectRadiusSize = a.getDimension(R.styleable.CountDownView_rectRadiusSize,dp2px(DEFAULT_RECT_RADIUS_SIZE));//这是自定义属性
            textColor = a.getColor(R.styleable.CountDownView_textColor,DEFAULT_TEXT_COLOR);//这是自定义属性
            backgroundColor = a.getColor(R.styleable.CountDownView_backgroundColor,DEFAULT_BACKGROUND_COLOR);//这是自定义属性
            colonLeftPadding = a.getDimension(R.styleable.CountDownView_colonLeftPadding,dp2px(DEFAULT_COLON_LEFT_PADDING));//这是自定义属性
            colonRightPadding = a.getDimension(R.styleable.CountDownView_colonRightPadding,dp2px(DEFAULT_COLON_RIGHT_PADDING));//这是自定义属性
            rectColor = a.getColor(R.styleable.CountDownView_rectColor,DEFAULT_RECT_COLOR);

            bg_distance = colonLeftPadding+backgroundStokeSize+colonRightPadding ;
            firstBackgroundRectF = new RectF(bg_rectf_left,bg_rectf_top,bg_rectf_right,bg_rectf_bottom);

            secondBackgroundRectF = new RectF(bg_distance+backgroundStokeSize+firstBackgroundRectF.right,
                    bg_rectf_top,
                    bg_distance + backgroundStokeSize*2 + firstBackgroundRectF.right + textSize + textPadding*2,
                    bg_rectf_bottom);

            threeBackgroundRectF = new RectF(bg_distance +backgroundStokeSize+ secondBackgroundRectF.right,
                    bg_rectf_top,
                    bg_distance + backgroundStokeSize*2 + secondBackgroundRectF.right + textSize + textPadding*2,
                    bg_rectf_bottom);

            defaultWrapContentWidth = threeBackgroundRectF.right+backgroundStokeSize;//应该根据上面计算出来的
            defaultWrapContentHeight = threeBackgroundRectF.bottom+backgroundStokeSize;//应该根据上面计算出来的
            a.recycle();
        }
        this.timePaint = new Paint();
        this.timePaint.setAntiAlias(true);
        this.timePaint.setColor(textColor);
        this.timePaint.setTextSize(textSize);

        this.backgroundPaint = new Paint();
        this.backgroundPaint.setAntiAlias(true);
        this.backgroundPaint.setColor(rectColor);
        this.backgroundPaint.setStyle(Paint.Style.STROKE);
        this.backgroundPaint.setStrokeWidth(backgroundStokeSize);

        this.setSurfaceTextureListener(this);

        initData();
    }

    private void initData(){
        this.timeHour = 12;
        this.timeMinute = 30;
        this.timeSecond = 10;
        this.mCurrentMilliseconds = this.timeHour * ONE_HOUR + this.timeMinute * ONE_MINUTE +
                this.timeSecond * ONE_SECOND;
        mCalendar.setTimeInMillis(mCurrentMilliseconds);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mViewWidth = MeasureSpec.getSize(widthMeasureSpec);
        this.mViewHeight = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        float resultWidth;
        float resultHeight;
        /**
         * 处理当wrap和UNSPECIFIED 时自己通过自己自定义的属性计算宽高
         *
         * 指明固定值宽高就根据指定的计算
         */
        switch (widthMode) {
            case MeasureSpec.AT_MOST://wrap_content需要自己计算
            case MeasureSpec.UNSPECIFIED:
                resultWidth = this.defaultWrapContentWidth;
                break;
            case MeasureSpec.EXACTLY://具体xml值和
            default:
                resultWidth = Math.max(this.mViewWidth, this.defaultWrapContentWidth);
                break;
        }
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                resultHeight = this.defaultWrapContentHeight;
                break;
            case MeasureSpec.EXACTLY:
            default:
                resultHeight = Math.max(this.mViewHeight, this.defaultWrapContentHeight);
                break;
        }
        resultWidth += (this.paddingLeft + this.paddingRight);
        resultHeight += (this.paddingTop + this.paddingBottom);
        L.i("resultWidth="+resultWidth);
        L.i("resultHeight="+resultHeight);
        this.setMeasuredDimension((int) resultWidth, (int) resultHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mViewWidth = w;
        this.mViewHeight = h;
        this.paddingLeft = this.getPaddingLeft();
        this.paddingTop = this.getPaddingTop();
        this.paddingRight = this.getPaddingRight();
        this.paddingBottom = this.getPaddingBottom();

    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        //TODO draw
        L.i("onSurfaceTextureAvailable");
        this.start();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        this.stop();
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }


    public void start(){
        if (this.runningState){
            return;
        }
        mThread = new DrawThread();
        this.mThread.startThread();
        this.runningState = true;
    }


    public void stop() {
        if (!this.runningState){
            return;
        }
        if (this.mThread != null) {
            this.mThread.stopThread();
        }
        this.runningState = false;
    }


    class DrawThread extends Thread{

        volatile boolean running = false;

        @Override
        public void run() {
            super.run();
            L.i("run");
            while (running){
                L.i("run-drawView");
                drawView();
            }
        }

        final public void startThread(){
            synchronized (this){
                running = true;
                this.start();
            }
        }

        final public void stopThread(){
            synchronized (this){
                running = false;
            }
        }

        private void drawView() {
            Canvas canvas = null;
            try {
                synchronized (this) {
                    canvas = CustomTextureView.this.lockCanvas();
                    if(canvas!=null) {
                        canvas.drawColor(backgroundColor);
                        timeHour = mCalendar.get(Calendar.HOUR_OF_DAY);
                        timeMinute = mCalendar.get(Calendar.MINUTE);
                        timeSecond = mCalendar.get(Calendar.SECOND);

                        mCurrentMilliseconds -= 1000;
                        mCalendar.setTimeInMillis(mCurrentMilliseconds);

                        drawTime(canvas,timeHour,timeMinute,timeSecond);


                        /**
                         *
                         * 如果我们每次通过sleep(1000)或者是直接wait1000,这样是会有误差的
                         * 因为程序执行受性能影响执行速度是不均匀的
                         *
                         * 用本次运行的时间戳减去上次执行完时间戳，就是通过了多少时间
                         * 用1000毫秒减去就是应该等待的时间
                         *
                         */
                        long pastTime = SystemClock.uptimeMillis() - lastRecordTime;
                        if (pastTime < COUNT_DOWN_INTERVAL){
                            this.wait(COUNT_DOWN_INTERVAL - pastTime);
                        }
                        /**
                         * 代码执行完后获取个时间戳
                         */
                        lastRecordTime = SystemClock.uptimeMillis();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    unlockCanvasAndPost(canvas);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void drawTime(Canvas canvas,int timeHour, int timeMinute, int timeSecond) {
            L.i("firstBackgroundRectF="+firstBackgroundRectF.left);
            if (timeHour>9){
                //x起点坐标=背景左边线宽+文本padding,y起点距离字体大小+上边线宽+文本padding
                canvas.drawText(String.valueOf(timeHour),bg_rectf_left + textPadding,
                         textSize+textPadding,timePaint);
            }else{
                canvas.drawText("0"+timeHour,bg_rectf_left+textPadding,
                        textSize+textPadding,timePaint);
            }
            canvas.drawRoundRect(firstBackgroundRectF,rectRadiusSize,rectRadiusSize,backgroundPaint);
            canvas.drawText(":",bg_rectf_right+colonLeftPadding,
                    textSize+textPadding,timePaint);
            if (timeMinute>9){
                //x起点坐标=背景之间距离+背景左边线宽+文本padding,y起点距离字体大小+上边线宽+文本padding
                canvas.drawText(String.valueOf(timeMinute),secondBackgroundRectF.left+textPadding,
                         textSize+textPadding,timePaint);
            }else{
                canvas.drawText("0"+timeMinute,secondBackgroundRectF.left+textPadding,
                        textSize+textPadding,timePaint);
            }
            canvas.drawRoundRect(secondBackgroundRectF,rectRadiusSize,rectRadiusSize,backgroundPaint);
            canvas.drawText(":",secondBackgroundRectF.right+colonLeftPadding,
                    textSize+textPadding,timePaint);
            if (timeSecond>9){
                canvas.drawText(String.valueOf(timeSecond),threeBackgroundRectF.left+textPadding,
                        textSize+textPadding,timePaint);
            }else{
                canvas.drawText("0"+timeSecond,threeBackgroundRectF.left+textPadding,
                        textSize+textPadding,timePaint);
            }

            canvas.drawRoundRect(threeBackgroundRectF,rectRadiusSize,rectRadiusSize,backgroundPaint);
        }
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


    /**
     * Sp to px
     *
     * @param sp sp
     * @return sp
     */
    private float sp2px(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, this.mMetrics);
    }
}
