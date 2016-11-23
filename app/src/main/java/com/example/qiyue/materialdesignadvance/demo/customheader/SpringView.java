package com.example.qiyue.materialdesignadvance.demo.customheader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ListView;
import android.widget.OverScroller;

import com.example.qiyue.materialdesignadvance.R;


/**
 * Created by liaoinstan on 2016/3/11.
 */
public class SpringView extends ViewGroup {

    private Context context;
    private LayoutInflater inflater;
    private OverScroller mScroller;
    private OnFreshListener listener;         //监听回调
    private boolean isCallDown = false;     //用于判断是否在下拉时到达临界点
    private boolean isCallUp = false;       //用于判断是否在上拉时到达临界点
    private boolean isFirst = true;         //用于判断是否是拖动动作的第一次move
    private boolean needChange = false;     //是否需要改变样式
    private boolean needResetAnim = false;  //是否需要弹回的动画
    private boolean isFullEnable = false;   //是否超过一屏时才允许上拉，为false则不满一屏也可以上拉，注意样式为isOverlap时，无论如何也不允许在不满一屏时上拉
    private boolean isMoveNow = false;       //当前是否正在拖动
    private long lastMoveTime;
    private boolean enable = true;           //是否禁用（默认可用）

    private int MOVE_TIME = 400;
    private int MOVE_TIME_OVER = 200;

    //是否需要回调接口：TOP 只回调刷新、BOTTOM 只回调加载更多、BOTH 都需要、NONE 都不
    public enum Give {BOTH, TOP, BOTTOM,NONE};
    public enum Type {OVERLAP,FOLLOW};
    private Give give = Give.BOTH;
    private Type type = Type.OVERLAP;
    private Type _type;

    //移动参数：计算手指移动量的时候会用到这个值，值越大，移动量越小，若值为1则手指移动多少就滑动多少px
    private final double MOVE_PARA = 2;
    //最大拉动距离，拉动距离越靠近这个值拉动就越缓慢
    private int MAX_HEADER_PULL_HEIGHT = 600;
    private int MAX_FOOTER_PULL_HEIGHT = 600;
    //储存上次的Y坐标
    private float mLastY;
    private float mLastX;
    //储存第一次的Y坐标
    private float mfirstY;
    //储存手指拉动的总距离
    private float dsY;
    //滑动事件目前是否在本控件的控制中
    private boolean isInControl = false;
    //存储拉动前的位置
    private Rect mRect = new Rect();

    //头尾内容布局
    private View header;
    private View footer;
    private View contentView;

    public SpringView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflater = LayoutInflater.from(context);
        mScroller = new OverScroller(context);

    }
    private int headerResoureId;
    private int footerResoureId;

    @Override
    protected void onFinishInflate() {
        contentView = getChildAt(0);
        if (contentView==null) {
            return;
        }
        setPadding(0,0,0,0);
        contentView.setPadding(0,0,0,0);
        if (headerResoureId!=0){
            inflater.inflate(headerResoureId, this, true);
            header = getChildAt(getChildCount()-1);
        }
        if (footerResoureId!=0){
            inflater.inflate(footerResoureId, this, true);
            footer = getChildAt(getChildCount()-1);
            footer.setVisibility(INVISIBLE);
        }

        contentView.bringToFront(); //把内容放在最前端

        super.onFinishInflate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getChildCount()>0){
            for (int i=0;i<getChildCount();i++){
                View child = getChildAt(i);
                measureChild(child,widthMeasureSpec,heightMeasureSpec);
            }
        }
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (contentView!=null) {
            if(type==Type.OVERLAP){
                if (header!=null) {
                    header.layout(0, 0, getWidth(), header.getMeasuredHeight());
                }
                if (footer!=null) {
                    footer.layout(0, getHeight() - footer.getMeasuredHeight(), getWidth(), getHeight());
                }
            }else if(type==Type.FOLLOW){
                if (header!=null) {
                    header.layout(0, -header.getMeasuredHeight(), getWidth(), 0);
                }
                if (footer!=null) {
                    footer.layout(0, getHeight(), getWidth(), getHeight() + footer.getMeasuredHeight());
                }
            }
            contentView.layout(0, 0, contentView.getMeasuredWidth(), contentView.getMeasuredHeight());
        }
    }

    private float dy;
    private float dx;
    private boolean isNeedMyMove;
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
       // dealMulTouchEvent(event);
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                Log.i("qiyue","ACTION_DOWN222222222222");
                final float x = event.getX();
                final float y = event.getY();
                mLastX = x;
                mLastY = y;
                hasCallFull = false;
                hasCallRefresh = false;
                mfirstY = event.getY();
                boolean isTop = isChildScrollToTop();
                boolean isBottom = isChildScrollToBottomFull(isFullEnable);
                if (isTop||isBottom) isNeedMyMove = false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("qiyue","ACTION_MOVE222222222222");
                float x2 = event.getX();
                float y2 = event.getY();
                dx = x2 - mLastX;
                dy = y2 - mLastY;
                mLastY = y2;
                mLastX = x2;
                dsY += dy;
                isMoveNow = true;
                isNeedMyMove = isNeedMyMove();
                if(isNeedMyMove && !isInControl){
                    //把内部控件的事件转发给本控件处理
                    isInControl = true;
                    event.setAction(MotionEvent.ACTION_CANCEL);
                    MotionEvent ev2 = MotionEvent.obtain(event);
                    dispatchTouchEvent(event);
                    ev2.setAction(MotionEvent.ACTION_DOWN);
                    return dispatchTouchEvent(ev2);
                }
                break;
            case MotionEvent.ACTION_UP:
                isMoveNow = false;
                lastMoveTime = System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return isNeedMyMove && enable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (contentView==null){
            return false;
        }
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                Log.i("qiyue","ACTION_DOWN");
                isFirst = true;
                //if (!mScroller.isFinished()) mScroller.abortAnimation();//不需要处理
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("qiyue","ACTION_MOVE");
                if (isNeedMyMove){
                    needResetAnim = false;      //按下的时候关闭回弹
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
                    Log.i("qiyue","isNeedMyMove");
                    //回调onDropAnim接口
                  //  callOnDropAnim();
                    //回调callOnPreDrag接口
                  //  callOnPreDrag();
                    //回调onLimitDes接口
                  //  callOnLimitDes();
                    isFirst = false;
                }
                else {
                    Log.i("qiyue","isnotnotnotnotnotnot notnotnotnotnotnotnotnotn");
                    //手指在产生移动的时候（dy!=0）才重置位置
                    if (dy!=0 && isFlow()) {
                        resetPosition();
                        //把滚动事件交给内部控件处理
                        event.setAction(MotionEvent.ACTION_DOWN);
                        dispatchTouchEvent(event);
                        isInControl = false;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                needResetAnim = true;      //松开的时候打开回弹
                isFirst = true;
                _firstDrag = true;
             //   restSmartPosition();
                dsY = 0;
                dy = 0;
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    private void doMove(){
        if (type==Type.OVERLAP){
            //记录移动前的位置
            if (mRect.isEmpty()) {
                mRect.set(contentView.getLeft(), contentView.getTop(),contentView.getRight(), contentView.getBottom());
            }
            //根据下拉高度计算位移距离，（越拉越慢）
            int movedy;
            if (dy>0) {
                movedy = (int) ((float) ((MAX_HEADER_PULL_HEIGHT - contentView.getTop()) / (float) MAX_HEADER_PULL_HEIGHT) * dy / MOVE_PARA);
            }else {
                movedy = (int) ((float) ((MAX_FOOTER_PULL_HEIGHT - (getHeight()-contentView.getBottom())) / (float) MAX_FOOTER_PULL_HEIGHT) * dy / MOVE_PARA);
            }
            int top = contentView.getTop() + movedy;
            contentView.layout(contentView.getLeft(), top, contentView.getRight(), top + contentView.getMeasuredHeight());
        }else if(type==Type.FOLLOW){
            //根据下拉高度计算位移距离，（越拉越慢）
            int movedx;
            if (dy>0) {
                movedx = (int) ((float) ((MAX_HEADER_PULL_HEIGHT + getScrollY()) / (float) MAX_HEADER_PULL_HEIGHT) * dy / MOVE_PARA);
            }else {
                movedx = (int) ((float) ((MAX_FOOTER_PULL_HEIGHT - getScrollY()) / (float) MAX_FOOTER_PULL_HEIGHT) * dy / MOVE_PARA);
            }
            scrollBy(0, (int)(-movedx));
        }
    }

    private boolean _firstDrag = true;

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
        //在滚动动画完全结束后回调接口
        //滚动回调过程中mScroller.isFinished会多次返回true，导致判断条件被多次进入，设置标志位保证只调用一次
        if (!isMoveNow && type==Type.FOLLOW && mScroller.isFinished()){
            if (isFullAnim) {
                if (!hasCallFull){
                    hasCallFull = true;
                }
            } else {
                if (!hasCallRefresh) {
                    hasCallRefresh = true;
                }
            }
        }
    }
    private int callFreshORload = 0;
    private boolean isFullAnim;
    private boolean hasCallFull = false;
    private boolean hasCallRefresh = false;

    /**
     * 判断是否需要由该控件来控制滑动事件
     */
    private boolean isNeedMyMove() {
        if (contentView==null){
            return false;
        }
        if (Math.abs(dy)<Math.abs(dx)){
            return false;
        }
        boolean isTop = isChildScrollToTop();
        boolean isBottom = isChildScrollToBottomFull(isFullEnable);     //false不满一屏也算在底部，true不满一屏不算在底部
        if (type==Type.OVERLAP){
            if (header!=null) {
                if (isTop && dy > 0 || contentView.getTop() > 0 + 20) {
                    return true;
                }
            }
            if (footer!=null) {
                if (isBottom && dy < 0 || contentView.getBottom() < mRect.bottom - 20) {
//                    if (isFullScrean()&&!isFullEnable)
//                        return true;
//                    else
//                        return false;
                    return true;
                }
            }
        }else if(type==Type.FOLLOW){
            if (header!=null) {
                //其中的20是一个防止触摸误差的偏移量
                if (isTop && dy > 0 || getScrollY() < 0 - 20) {
                    return true;
                }
            }
            if (footer!=null) {
                if (isBottom && dy < 0 || getScrollY() > 0 + 20) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 重置控件位置到初始状态
     */
    private void resetPosition() {
        isFullAnim = true;
        isInControl = false;    //重置位置的时候，滑动事件已经不在控件的控制中了
        if (type==Type.OVERLAP){

            contentView.layout(mRect.left, mRect.top, mRect.right, mRect.bottom);
        }else if(type==Type.FOLLOW){
            mScroller.startScroll(0, getScrollY(), 0, -getScrollY(),MOVE_TIME);
            invalidate();
        }
        //mRect.setEmpty();
    }

    /**
     * 判断目标View是否滑动到顶部-还能否继续滑动
     * @return
     */
    private boolean isChildScrollToTop() {
        return !ViewCompat.canScrollVertically(contentView, -1);
    }

    /**
     * 是否滑动到底部
     * @return
     */
    private boolean isChildScrollToBottomFull(boolean isFull) {
        return !ViewCompat.canScrollVertically(contentView, 1);
    }

    /**
     * 判断当前状态是否拉动顶部
     */
    private boolean isTop(){
        if (type==Type.OVERLAP){
            return contentView.getTop()>0;
        }else if (type==Type.FOLLOW){
            return getScrollY()<0;
        }else
            return false;
    }
    private boolean isBottom(){
        if (type==Type.OVERLAP){
            return contentView.getTop()<0;
        }else if(type==Type.FOLLOW){
            return getScrollY()>0;
        }else
            return false;
    }
    private boolean isFlow(){
        if (type==Type.OVERLAP){
            return contentView.getTop()<30 && contentView.getTop()>-30;
        }else if (type==Type.FOLLOW){
            return getScrollY()>-30 && getScrollY()<30;
        }else
            return false;
    }

    /**
     * 切换Type的方法，之所以不暴露在外部，是防止用户在拖动过程中调用造成布局错乱
     * 所以在外部方法中设置标志，然后在拖动完毕后判断是否需要调用，是则调用
     */
    private void changeType(Type type){
        this.type = type;
        if (header!=null&& header.getVisibility()!=INVISIBLE) header.setVisibility(INVISIBLE);
        if (footer!=null&& footer.getVisibility()!=INVISIBLE) footer.setVisibility(INVISIBLE);
        requestLayout();
        needChange = false;
    }

    /**
     * 重置控件位置，暴露给外部的方法，用于在刷新或者加载完成后调用
     */
    public void onFinishFreshAndLoad(){
        if (!isMoveNow && needResetAnim) {
            boolean needTop = isTop() && (give == Give.TOP || give == Give.BOTH);
            boolean needBottom = isBottom() && (give == Give.BOTTOM || give == Give.BOTH);
            if (needTop || needBottom) {
                if (contentView instanceof ListView) {
                    //((ListView) contentView).smoothScrollByOffset(1);
                    //刷新后调用，才能正确显示刷新的item，如果调用上面的方法，listview会被固定在底部
//                    ((ListView) contentView).smoothScrollBy(-1,0);
                }
                resetPosition();
            }
        }
    }

    /**
     * 设置监听
     */
    public void setListener(OnFreshListener listener) {
        this.listener = listener;
    }

    /**
     * 改变样式的对外接口
     */
    public void setType(Type type) {
        if (isTop()||isBottom()){
            //如果当前用户正在拖动，直接调用changeType()会造成布局错乱
            //设置needChange标志，在执行完拖动后再调用changeType()
            needChange = true;
            //把参数保持起来
            _type = type;
        }else {
            changeType(type);
        }
    }

    /**
     * 回调接口
     */
    public interface OnFreshListener{
        /**
         * 下拉刷新，回调接口
         */
        void onRefresh();
        /**
         * 上拉加载，回调接口
         */
        void onLoadmore();
    }

    public void setHeader() {
        View headerView = inflater.inflate(R.layout.default_header,this,true);
        this.header = getChildAt(getChildCount() - 1);
        contentView.bringToFront(); //把内容放在最前端
        requestLayout();
    }

}
