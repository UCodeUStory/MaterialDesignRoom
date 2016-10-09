package com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_custom_behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class DependentBehavior extends CoordinatorLayout.Behavior<View> {

    /**
     * 必须重写否则报错
     * @param context
     * @param attrs
     */
    public DependentBehavior(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    /**
     * 此方法返回true，才表示观察，之后onDependentViewChanged才会被调用
     * @param parent
     * @param child  被设置behavior View
     * @param dependency   被观察的View
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        /**
         * 限制只有是TextView 时才被观察
         */
        return dependency instanceof TextView;
    }

    /**
     * 监听View 的状态变化
     * @param parent
     * @param child
     * @param dependency
     * @return true if the Behavior changed the child view's size or position, false otherwise
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        /**
         * offset补偿的意思
         */
        int offset = dependency.getTop() - child.getTop();
        /**
         * ViewCompat
         */
        ViewCompat.offsetTopAndBottom(child, offset);
        return true;
    }
}
