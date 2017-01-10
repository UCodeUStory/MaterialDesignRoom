package com.example.qiyue.materialdesignadvance.demo.custom_animation_frame;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

/**
 * Created by qiyue on 2016/10/13 0013.
 */
public class UScrollView extends ScrollView {

    private UContentView mContent;

    public UScrollView(Context context) {
        super(context);
    }

    public UScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        // TODO Auto-generated method stub
        super.onFinishInflate();
        View content = getChildAt(0);
        mContent = (UContentView) content;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO Auto-generated method stub
        super.onSizeChanged(w, h, oldw, oldh);
        /**这个就是自定义Linearlayout**/
        View first = mContent.getChildAt(0);
        first.getLayoutParams().height = getHeight();
    }

    /**
     *  动画控制就是根据划出来到完全出来时对此View做动画
     * @param l
     * @param t 滑动出去的高度
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        /**
         * 获取自身高度
         */
        int scrollViewHeight = getHeight();
        L.i("scrollViewHeight="+scrollViewHeight);
        //监听滑动----接口---->控制scroll的属性
        /**
         * 遍历每一个子View 判断是否实现了动画监听接口
         */
        for (int i = 0; i < mContent.getChildCount(); i++) {//遍历MyLinearLayout里面所有子控件(MyViewGroup)
            View child = mContent.getChildAt(i);
            if (!(child instanceof ScrollAnimationListener)) {
                /**
                 * 跳过本次循环，break,就会直接跳出了
                 */
                continue;
            }

            //ratio:0~1
            ScrollAnimationListener scrollAnimationListener = (ScrollAnimationListener) child;
            //1.child离scrollview顶部的高度 a
            int arriveScrollTop = child.getTop();
            int childHeight = child.getHeight();

            //2.得到scrollview滑出去的高度  t,
            //3.得到child离屏幕顶部的高度  discrollvableAbsoluteTop
            /**
             * child距离控件顶端距离arriveScrollTop
             * 划出的高度   t
             * 距离屏幕顶端距离discrollvableAbsoluteTop
             */
            int discrollvableAbsoluteTop = arriveScrollTop - t;
            //什么时候执行动画？当child滑进屏幕的时候
            if (discrollvableAbsoluteTop <= scrollViewHeight) {
                int visibleGap = scrollViewHeight - discrollvableAbsoluteTop;
                //确保ratio是在0~1，超过了1 也设置为1
                scrollAnimationListener.onStartAnimation(clamp(visibleGap / (float) childHeight, 1f, 0f));
            } else {//否则，就恢复到原来的位置
                scrollAnimationListener.onResetAnimation();
            }
        }
    }

    public static float clamp(float value, float max, float min){
        return Math.max(Math.min(value, max), min);
    }

}
