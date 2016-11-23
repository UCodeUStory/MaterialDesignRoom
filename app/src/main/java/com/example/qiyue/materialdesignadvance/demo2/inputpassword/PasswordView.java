package com.example.qiyue.materialdesignadvance.demo2.inputpassword;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.EditText;

import com.example.qiyue.materialdesignadvance.R;
import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

import java.lang.reflect.Field;

/**
 * Created by qiyue on 2016/11/18.
 * length 必须和maxlength相同
 */
public class PasswordView extends EditText{


    private Paint mbgPaint;
    private Paint dotPaint;
    private RectF bgRect;

    private float mViewWidth;
    private float mViewHeight;
    private float paddingLeft;
    private float paddingTop;
    private float paddingRight;
    private float paddingBottom;
    private DisplayMetrics mMetrics;

    public static final float RECT_WIDTH = 300;
    public static final float RECT_HEIGHT = 50;
    public static final int LINE_COLOR = Color.GRAY;
    public static final int DOT_RADIUS = 15;
    public static final float LINE_STROKE = 0.5f;
    public static final int DOT_COLOR = Color.BLACK;
    public static final float RECT_RX = 5;
    public static final int LENGTH = 6;

    private int textLength;
    private float rectWidth;
    private float rectHeight;
    private int lineColor;
    private float defaultWrapContentWidth;
    private float defaultWrapContentHeight;
    private float dotRaduis;
    private float lineStroke;
    private int dotColor;
    private float rectRx;
    private float rectRy;
    private float length;

    public PasswordView(Context context) {
        super(context);
        init(context,null);
    }

    public PasswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public PasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.setBackground(null);
        this.setInputType(InputType.TYPE_CLASS_NUMBER);

        this.mMetrics = this.getResources().getDisplayMetrics();
        this.mbgPaint = new Paint();
        this.mbgPaint.setAntiAlias(true);
        this.mbgPaint.setStyle(Paint.Style.STROKE);

        this.dotPaint = new Paint();
        this.dotPaint.setAntiAlias(true);
        this.dotPaint.setStyle(Paint.Style.FILL);

        this.bgRect = new RectF();

        {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PasswordView);
            this.rectWidth = a.getDimension(R.styleable.PasswordView_rectWidth,dp2px(RECT_WIDTH));
            this.rectHeight = rectWidth/6;
            this.lineColor = a.getColor(R.styleable.PasswordView_lineColor,LINE_COLOR);
            this.lineStroke = a.getDimension(R.styleable.PasswordView_lineStroke,dp2px(LINE_STROKE));
            this.dotRaduis = a.getDimension(R.styleable.PasswordView_dotRaduis,dp2px(DOT_RADIUS));
            this.dotColor = a.getColor(R.styleable.PasswordView_dotColor,DOT_COLOR);
            this.rectRx = a.getDimension(R.styleable.PasswordView_rectRx,RECT_RX);
            this.length = a.getInt(R.styleable.PasswordView_length,LENGTH);
            a.recycle();
        }
        this.rectRy = rectRx;
        this.mbgPaint.setStrokeWidth(lineStroke);
        this.mbgPaint.setColor(lineColor);
        this.dotPaint.setColor(dotColor);
        this.bgRect.left = 0;
        this.bgRect.top = 0;
        this.bgRect.right = bgRect.left + rectWidth;
        this.bgRect.bottom = bgRect.top + rectHeight;

        this.defaultWrapContentWidth = bgRect.width();
        this.defaultWrapContentHeight = bgRect.height();

    }


    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        this.textLength = text.toString().length();
        invalidate();
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
    protected void onDraw(Canvas canvas) {
        RectF finalRecF = new RectF();
        finalRecF.left = this.bgRect.left + this.getPaddingLeft();
        finalRecF.right = this.bgRect.right + this.getPaddingRight();
        finalRecF.top = this.bgRect.top + this.getPaddingTop();
        finalRecF.bottom = this.bgRect.bottom + this.getPaddingBottom();

        canvas.drawRoundRect(finalRecF,rectRx,rectRy,this.mbgPaint);

        float distance = bgRect.width()/length;
        float startX = finalRecF.left ;
        float startY = finalRecF.top;
        float endX = finalRecF.left;
        float endY = finalRecF.top + finalRecF.height();

        for (int i=0;i<length-1;i++){
            startX = startX +distance;
            endX = endX +distance ;
            L.i("startX="+startX+"startY="+startY+"endX="+endX+"endY="+endY+"distance="+distance);
            canvas.drawLine(startX,startY,endX,endY,mbgPaint);
        }

        float cx = finalRecF.left + distance/2;
        float cy = finalRecF.top + finalRecF.height()/2;
        for (int j=0;j<textLength;j++){
            if (j>0){
                cx = cx + distance;
            }
            canvas.drawCircle(cx,cy,dotRaduis,dotPaint);
        }
      //  super.onDraw(canvas);
        L.i("draw draw draw draw draw draw draw ondraw");
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
