package com.example.qiyue.materialdesignadvance.demo.custom_animation_frame;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.qiyue.materialdesignadvance.R;
import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

/**
 * Created by qiyue on 2016/10/13 0013.
 * 自身实现监听就不需要在Activity 里面setListener
 */
public class UItemView extends FrameLayout implements ScrollAnimationListener {

    private boolean isCanAlpha;
    private boolean isCanScaleX;
    private boolean isCanScaleY;
    /**
     * 四种方向
     */
    private int translationMode;
    /**
     * 背景颜色渐变配色
     */
    private int fromeBgColor;
    private int toBgColor;
    private int mHeight;//本view的高度
    private int mWidth;//宽度

    private static final int TRANSLATION_FROM_TOP = 0x01;
    private static final int TRANSLATION_FROM_BOTTOM = 0x02;
    private static final int TRANSLATION_FROM_LEFT = 0x04;
    private static final int TRANSLATION_FROM_RIGHT = 0x08;

    //颜色估值器
    private static ArgbEvaluator sArgbEvaluator = new ArgbEvaluator();

    public UItemView(Context context) {
        super(context);
    }

    public UItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setCanAlpha(boolean canAlpha) {
        isCanAlpha = canAlpha;
    }

    public void setCanScaleX(boolean canScaleX) {
        isCanScaleX = canScaleX;
    }

    public void setCanScaleY(boolean canScaleY) {
        isCanScaleY = canScaleY;
    }

    public void setTranslationMode(int translationMode) {
        this.translationMode = translationMode;
    }

    public void setFromeBgColor(int fromeBgColor) {
        this.fromeBgColor = fromeBgColor;
    }

    public void setToBgColor(int toBgColor) {
        this.toBgColor = toBgColor;
    }

    /**
     * 这是重写自己定义的方法，命名太像系统方法
     *
     * @param ratio 0~1
     */
    @Override
    public void onStartAnimation(float ratio) {
        // ratio:0~1
        //控制自身的动画属性
        if (isCanAlpha) {
            setAlpha(ratio);
        }
        if (isCanScaleX) {
            setScaleX(ratio);
        }
        if (isCanScaleY) {
            setScaleY(ratio);
        }

        //判断到底是哪一种值：fromTop,fromBottom,fromLeft,fromRight
        //fromBottom
        if (isScrollTranslationFrom(TRANSLATION_FROM_BOTTOM)) {
            setTranslationY(mHeight * (1 - ratio));//mHeight-->0(代表原来的位置)
        }
        if (isScrollTranslationFrom(TRANSLATION_FROM_TOP)) {
            setTranslationY(-mHeight * (1 - ratio));//-mHeight-->0(代表原来的位置)
        }
        if (isScrollTranslationFrom(TRANSLATION_FROM_LEFT)) {
            setTranslationX(-mWidth * (1 - ratio));//-width-->0(代表原来的位置)
        }
        if (isScrollTranslationFrom(TRANSLATION_FROM_RIGHT)) {
            setTranslationX(mWidth * (1 - ratio));//width-->0(代表原来的位置)
        }

        //颜色渐变动画
        if (fromeBgColor != -1 && toBgColor != -1) {
            //ratio=0.5 color=中间颜色
            L.i("color>>>>>>>>>>>>>>>>>>>>>>>>"+ratio);
            setBackgroundColor((int) sArgbEvaluator.evaluate(Math.abs(ratio), fromeBgColor, toBgColor));

        }
    }

    @Override
    public void onResetAnimation() {

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mHeight = h;
        this.mWidth = w;
        /**
         * 尺寸变化时，重置动画属性
         */
        onResetAnimation();
    }

    /**
     * 判断是否包含这方想的滚动
     *
     * @param translation
     * @return
     */
    private boolean isScrollTranslationFrom(int translation) {
        if (translationMode == -1) {
            return false;
        }
        /**
         * 自己计算从左到右会发现，如果前面全是或，最后与上一个a,如果得a,证明包含
         */
        //fromLeft|fromBottom & fromBottom = fromBottom
        return (translationMode & translation) == translation;
    }
}
