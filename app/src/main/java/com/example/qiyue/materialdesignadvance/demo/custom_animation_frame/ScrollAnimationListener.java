package com.example.qiyue.materialdesignadvance.demo.custom_animation_frame;

/**
 * Created by Administrator on 2016/10/13 0013.
 */
public interface ScrollAnimationListener {

    /**
     * 当滑动的时候调用该方法，用来控制里面的控件执行相应的动画
     * @param ratio
     */
    public void onStartAnimation(float ratio);

    /**
     * 重置view的属性----恢复view的原来属性
     */
    public void onResetAnimation();
}
