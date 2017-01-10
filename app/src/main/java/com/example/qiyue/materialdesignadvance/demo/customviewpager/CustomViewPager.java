package com.example.qiyue.materialdesignadvance.demo.customviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Scroller;

import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

/**
 * Created by qiyue on 2016/10/23.
 */
public class CustomViewPager extends ViewGroup {
    private ViewPager viewPager;
    private Scroller mScroller;
    private int childCount;
    int position = 1;
    private float downx;
    private float downy;
    /**
     * 滑动多少出发自动滑动
     * * */

    private int slop = 200;
    public CustomViewPager(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context,null,true);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 这两个参数是父容器给予的边界参数，或者说是建议，父容器的允许宽高都可以
     *
     * 我们这里自定义的是ViewGroup,这里通过ViewGroup父类参数测量自己子类的参数，然后再设置自己的宽高
     * 告诉父类我的真实的需要
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        childCount = getChildCount();
        int size = childCount;
        for (int i = 0; i < size; ++i) {
            final View child = getChildAt(i);
            /**
             * 这里面的源码就是通过孩子的layoutparam也就是布局中的参数，获取宽高
             * 结合自身ViewGroup的Padding创建一个measureSpec最后
             * 调用孩子的measure(,)方法
             * 这里为什么测量，因为我在onlayout的时候我需要宽高
             * getMeasureWidth...,我们可以直接调用getWidth()获取孩子宽高吗，是不能的
             * getWidth() 是用布局后的左边界减去右边界，这里还有没有布局所以获得不到
             * 猜想是这样的
             */
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }

        setMeasuredDimension(getMDefaultSize(0,widthMeasureSpec),getMDefaultSize(0,heightMeasureSpec));

        /**
         * 不是设置，就取决于layout后结果
         *  setMeasuredDimension();
         */
    }
    /**
     * 从左到有右边布局
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int size = getChildCount();
            for (int i = 0; i < size; ++i) {
                final View child = getChildAt(i);
                child.layout(i*child.getMeasuredWidth(), 0, (i+1)*child.getMeasuredWidth(), child.getMeasuredHeight());
                child.setClickable(true);
            }
        }
        //左边界
       // leftBound = getChildAt(0).getLeft();
        //右边界
       // rightBound = getChildAt(getChildCount()-1).getRight();
    }


    /**
     * 系统源码有这个方法getDefaultSize，这里只是看一下
     * match_parent和wrap_content 都是返回直接返回测量宽高
     * @param size
     * @param measureSpec
     * @return
     */
    public static int getMDefaultSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("ricky", "onIntercept");
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ViewParent parent = getParent();
                parent.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                return true;

            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }


    private float startX ;
    private float startY ;
    private float dx;
    private float dy;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                //super.dispatchTouchEvent(ev);
                //return true;
            case MotionEvent.ACTION_MOVE:
                dx = ev.getX() - startX;
                dy = ev.getY() - startY;
                //dx>0右滑   <0左滑
                /**
                 * 大于这个距离才算滑动
                 * ViewConfiguration.getTouchSlop()
                 */
                if(Math.abs(dx) - Math.abs(dy)  > ViewConfiguration.getTouchSlop()){
                    if (getScrollX()-dx>getMeasuredWidth()*(childCount-1)){
                        L.i("到达右边界");
                        scrollTo(getMeasuredWidth()*(childCount-1),0);
                    }else if(getScrollX()-dx<0){
                        L.i("到达左边界");
                        scrollTo(0,0);
                    }else{
                        L.i("正常移动="+"position="+position+"getScrollX()="+getScrollX()+"-dx="+(-dx));
                        this.scrollBy((int) -dx, 0);
                    }

                    /**
                     * 因为用scrollBy 这里必须重写获得当前的点，不然效果就不一样了
                     */
                    startX = ev.getX();
                    startY = ev.getY();
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                L.i("position="+position);
                if (position == 1) {
                    /**
                     * getScrollX()一开始是0
                     * 如果getScrollX滑动rightChild宽度一半就继续滚动剩余部分，否者关闭
                     */
                    L.i("getScrollX="+getScrollX());
                    int tap = getScrollX();
                    if (tap>slop) {
                        mScroller.startScroll(getScrollX(), getScrollY(), getMeasuredWidth() - getScrollX(), 0);
                        position++;
                    }else{
                        mScroller.startScroll(getScrollX(), getScrollY(), - getScrollX(), 0);
                    }
                }else if (position<childCount){
                    /**
                     * getScrollX()一开始是0
                     * 如果getScrollX滑动rightChild宽度一半就继续滚动剩余部分，否者关闭
                     */
                    L.i("getScrollX2="+getScrollX());
                    //向左滑动
                    if (getScrollX()<getMeasuredWidth()*(position-1)){
                        int tap = (getMeasuredWidth() * (position-1) - getScrollX());
                        L.i("tap-left="+tap);
                        if (tap >slop){
                            mScroller.startScroll(getScrollX(), getScrollY(), -(getMeasuredWidth()-tap), 0);
                            position--;
                        }else{
                            mScroller.startScroll(getScrollX(), getScrollY(), tap, 0);
                        }
                        //向右滑动
                    }else {
                        int tap = getMeasuredWidth() - (getMeasuredWidth() * position - getScrollX());
                        if (tap > slop) {
                            mScroller.startScroll(getScrollX(), getScrollY(), getMeasuredWidth() * position - getScrollX(), 0);
                            position++;
                        } else {
                            mScroller.startScroll(getScrollX(), getScrollY(), -tap, 0);
                        }
                    }
                    /**必须调用invalidate
                     * **/
                }else if (position == childCount){
                    //向左滑动
                    if (getScrollX()<getMeasuredWidth()*(position-1)){
                        int tap = (getMeasuredWidth() * (position-1) - getScrollX());
                        L.i("tap-left="+tap);
                        if (tap >slop){
                            mScroller.startScroll(getScrollX(), getScrollY(), -(getMeasuredWidth()-tap), 0);
                            position--;
                        }else{
                            mScroller.startScroll(getScrollX(), getScrollY(), tap, 0);
                        }
                    }
                }
                invalidate();
                startX = 0;
                startY = 0;
                dx = 0;
                dy = 0;
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }



    /**
     * 在开启滑动的情况下（mScroller.startScroll），滑动的过程当中此方法会被不断调用
     */
    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
/*    CustomViewPagerAdapter customAdapter;
    public void setAdapter(CustomViewPagerAdapter customAdapter){
        for (int i = 0; i < customAdapter.size(); i++) {
            L.i("setAdapter");
            addView(customAdapter.getDatas().get(i));
        }
        requestLayout();
        //invalidate();
    }*/
}
