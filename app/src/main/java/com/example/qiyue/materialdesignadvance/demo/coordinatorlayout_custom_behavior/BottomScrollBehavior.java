package com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_custom_behavior;

/**
 * Created by Administrator on 2016/10/9 0009.
 */

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by qiyue on 2016/10/9 0009.
 */
public class BottomScrollBehavior extends CoordinatorLayout.Behavior<View> {

    public BottomScrollBehavior(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    /**
     *      * 当观察的View（RecyclerView）发生滑动的开始的时候回调的
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes  滚动方向
     * @return
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {

        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }


    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);

        /*CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        params.topMargin +=dy;
        child.setLayoutParams(params);*/
        int scrollY = Math.abs(child.getScrollY()-dy);
        Log.i("qiyue","dy="+child.getScrollY());
        if (child.getScrollY()-dy>0){
            child.scrollTo(0,0);
        }else if(scrollY>child.getHeight()) {
            child.scrollTo(0,-child.getHeight());
        }else{
            child.scrollBy(0,-dy);
        }
    }

    /**
     *用户按下触屏、快速移动后松开
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param velocityX
     * @param velocityY
     * @param consumed
     * @return
     */
    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        if(velocityY>0){
            child.scrollTo(0,-child.getHeight());
        }else{
            child.scrollTo(0,0);
        }
        return true;
    }
}
