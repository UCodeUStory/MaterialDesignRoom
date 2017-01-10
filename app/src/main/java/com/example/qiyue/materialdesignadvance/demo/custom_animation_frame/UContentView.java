package com.example.qiyue.materialdesignadvance.demo.custom_animation_frame;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.qiyue.materialdesignadvance.R;
import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

/**
 * Created by qiyue on 2016/10/13 0013.
 */
public class UContentView extends LinearLayout {

    public UContentView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
    }

    public UContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

     /**
     * 这个方法中attrs就是从布局中传来的所有配置参数，如果直接传递给generateLayoutParams(attrs)
      * 查看系统源码 也只是能或得系统默认配置的属性，因此我们自己配置重新获取
      *
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        //return super.generateLayoutParams(attrs);
        return new MyLayoutParams(getContext(),attrs);
    }

    @Override
    public void addView(View child, int index,
                        android.view.ViewGroup.LayoutParams params) {
        /**从child里面拿到我自定义的属性，传到discrollvableView里面
         */
        MyLayoutParams p = (MyLayoutParams) params;
        if(!isCanAnimationAble(p)){
            L.i("nonononononononononoon");
            super.addView(child, index, params);
        }else{
            L.i("yesyesyesyesyesyesyesyes");
            /**
             * 在addView里面插一脚，往child外面包裹一层FrameLayout
             */
            UItemView mItemView = new UItemView(getContext());
            mItemView.setCanAlpha(p.mDiscrollveAlpha);
            mItemView.setTranslationMode(p.mDisCrollveTranslation);
            mItemView.setCanScaleX(p.mDiscrollveScaleX);
            mItemView.setCanScaleY(p.mDiscrollveScaleY);
            mItemView.setFromeBgColor(p.mDiscrollveFromBgColor);
            mItemView.setToBgColor(p.mDiscrollveToBgColor);
            mItemView.addView(child);
            L.i("params<>>>>>>>>>>>>>>>="+params.height);
            super.addView(mItemView, index, params);
        }
    }

    /**
     * 判断该view是否穿了自定义属性值，不是就不需要执行动画，不包一层FrameLayout
     * @param p
     * @return
     */
    private boolean isCanAnimationAble(MyLayoutParams p) {
        /**
         * 因为下面我们配置了默认值，所以只要判断是不是默认值就好，
         * 如果是默认值证明没有该属性或者不执行动画
         */
        return p.mDiscrollveAlpha||
                p.mDiscrollveScaleX||
                p.mDiscrollveScaleY||
                p.mDisCrollveTranslation!=-1||
                (p.mDiscrollveFromBgColor!=-1&&
                        p.mDiscrollveToBgColor!=-1);
    }

    public static class MyLayoutParams extends LinearLayout.LayoutParams{
        public int mDiscrollveFromBgColor;//背景颜色变化开始值
        public int mDiscrollveToBgColor;//背景颜色变化结束值
        public boolean mDiscrollveAlpha;//是否需要透明度动画
        public int mDisCrollveTranslation;//平移值
        public boolean mDiscrollveScaleX;//是否需要x轴方向缩放
        public boolean mDiscrollveScaleY;//是否需要y轴方向缩放

        public MyLayoutParams(Context context, AttributeSet attrs) {
            /**
             * super 就是解析系统自带的属性
             */
            super(context, attrs);
            /**解析自定义属性**/
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.UScrollParam);
            mDiscrollveAlpha = a.getBoolean(R.styleable.UScrollParam_scroll_alpha, false);
            mDiscrollveScaleX = a.getBoolean(R.styleable.UScrollParam_scroll_scaleX, false);
            mDiscrollveScaleY = a.getBoolean(R.styleable.UScrollParam_scroll_scaleY, false);
            mDisCrollveTranslation = a.getInt(R.styleable.UScrollParam_scroll_translation, -1);
            mDiscrollveFromBgColor = a.getColor(R.styleable.UScrollParam_scroll_fromBgColor, -1);
            mDiscrollveToBgColor = a.getColor(R.styleable.UScrollParam_scroll_toBgColor, -1);
            a.recycle();
        }

    }
}
