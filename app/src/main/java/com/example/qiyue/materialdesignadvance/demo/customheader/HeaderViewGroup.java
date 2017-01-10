package com.example.qiyue.materialdesignadvance.demo.customheader;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by qiyue on 2016/10/28.
 */
public class HeaderViewGroup extends BaseViewGroup {

    public HeaderViewGroup(Context context) {
        super(context);
    }

    public HeaderViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                Log.i("ustory","dispatchTouchEvent-down");
                final float x = event.getX();
                final float y = event.getY();
                mLastX = x;
                mLastY = y;
                boolean isTop = isChildScrollToTop();
                if (isTop) isNeedMyMove = false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("ustory","dispatchTouchEvent-ACTION_MOVE");
                float x2 = event.getX();
                float y2 = event.getY();
                dx = x2 - mLastX;
                dy = y2 - mLastY;
                mLastY = y2;
                mLastX = x2;
                dsY += dy;
                isMoveNow = true;
                isNeedMyMove = isNeedMyMove();
                /**
                 * 为什么两个条件，当isNeedMyMove为false 时，如果手不松开事件还是会到
                 * onTouchEvent里面
                 */
                if(isNeedMyMove && !isInControl){
                    Log.i("ustory","dispatchTouchEvent-ACTION_MOVE-isNeedMyMove=true");
                    //把内部控件的事件转发给本控件处理
                    /**
                     * 为什么要先走取消，再走down
                     */
                    isInControl = true;
                    event.setAction(MotionEvent.ACTION_CANCEL);
                    MotionEvent ev2 = MotionEvent.obtain(event);
                    dispatchTouchEvent(event);
                    event.setAction(MotionEvent.ACTION_DOWN);
                    return dispatchTouchEvent(event);
                    /**
                     * return true 其实就可以，
                     * 也可以要求再走一个down 再进行移动都是没问题的
                     */
                   // return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.i("ustory","dispatchTouchEvent-ACTION_UP");
               /* if (dy!=0 && isFlow()) {
                    resetPosition();
                }*/
                isMoveNow = false;
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i("ustory","dispatchTouchEvent-ACTION_CANCEL");
                break;
        }
        Log.i("ustory","super.dispatchTouchEvent(event);");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (contentView==null){
            return false;
        }
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                Log.e("ustory","onTouchEvent-ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("ustory","onTouchEvent-ACTION_MOVE");
                if (isNeedMyMove){
                    Log.e("ustory","onTouchEvent-ACTION_MOVE开始移动");
                    //执行位移操作
                    doMove();
                    //下拉的时候显示header并隐藏footer，上拉的时候相反
                    if (isTop()){
                        if (header!=null && header.getVisibility() != View.VISIBLE) header.setVisibility(View.VISIBLE);
                        if (footer!=null && footer.getVisibility() != View.INVISIBLE) footer.setVisibility(View.INVISIBLE);
                    }else if(isBottom()){
                        if (header!=null && header.getVisibility() != View.INVISIBLE) header.setVisibility(View.INVISIBLE);
                        if (footer!=null && footer.getVisibility() != View.VISIBLE) footer.setVisibility(View.VISIBLE);
                    }
                } else {
                    Log.e("ustory","onTouchEvent isNeedMyMove is false");
                    /**只要手移动不松开，当最后拦截不满足时还是会调用这个方法
                     * 因此要做判断，当最后条件不满足时，并且达到划出的范围
                     * 就手动传递一个Down事件给dispatchTouchEvent，这样就能保证
                     * 头布局和子布局连续滑动，
                     *   event.setAction(MotionEvent.ACTION_DOWN);//把滚动事件交给子控件处理
                     dispatchTouchEvent(event);
                     * 但是会出现一个问题，就是快速滑动会照成一些误差，导致getScrollY不等于0
                     * 因此在这里做一个恢复可以直接scrollTo但是效果不好
                     * 用一个startScroll动画，
                     *
                     * 快速滑动没划出处屏幕并且在误差范围内，可以用此方法还原
                     * 如果快速滑动划出屏幕范围就会
                     * * **/

                    //手指在产生移动的时候（dy!=0）才重置位置
                    if (dy!=0 && isFlow()) {
                        resetPosition();
                        event.setAction(MotionEvent.ACTION_DOWN);//把滚动事件交给子控件处理
                        dispatchTouchEvent(event);//手动调用也只是初始化值吧
                        isInControl = false;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                resetHandle();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;

    }

    private void resetHandle() {
        Log.i("resethandler","getScrollY()="+getScrollY()+"MAX_HEADER_PULL_HEIGHT="+MAX_HEADER_PULL_HEIGHT);
        if (Math.abs(getScrollY())<MAX_HEADER_PULL_HEIGHT/2) {
            Log.i("resethandler",">>>>>>>>>>>>>");
            mScroller.startScroll(0, getScrollY(), 0,-getScrollY(),400);
            invalidate();
        }else{
            displayHeader();
        }
        dsY = 0;
        dy = 0;
    }

    private void displayHeader() {
        mScroller.startScroll(0, getScrollY(), 0, -(MAX_HEADER_PULL_HEIGHT+getScrollY()),400);
        invalidate();
    }

    /**
     * 还是有一个bug，就是快速滑动的时候就会把底部给拉上来
     */
    private void doMove(){
         if(type==Type.FOLLOW){
            //根据下拉高度计算位移距离，（越拉越慢）
            int movedx;
            Log.i("ustory","doMove dy="+dy + "getScrollY="+getScrollY());
            if (dy>0) {
                movedx = (int) ((float) ((MAX_HEADER_PULL_HEIGHT + getScrollY()) / (float) MAX_HEADER_PULL_HEIGHT) * dy / MOVE_PARA);
            }else {
                movedx = (int) ((float) ((MAX_FOOTER_PULL_HEIGHT - getScrollY()) / (float) MAX_FOOTER_PULL_HEIGHT) * dy / MOVE_PARA);
            }
            scrollBy(0, (int)(-movedx));
        }
    }

    /**
     * 重置控件位置到初始状态，这个事件可以小一点
     */
    private void resetPosition() {
        isInControl = false;    //重置位置的时候，滑动事件已经不在控件的控制中了
        if(type==Type.FOLLOW){
            mScroller.startScroll(0, getScrollY(), 0, -getScrollY(),400);
            invalidate();
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }

}
