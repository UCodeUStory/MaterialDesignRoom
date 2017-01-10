package com.example.qiyue.materialdesignadvance.demo.customheader;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.OverScroller;

import com.example.qiyue.materialdesignadvance.R;

/**
 * Created by ustory on 2016/10/28.
 */
public abstract class BaseViewGroup extends ViewGroup{

    protected Context context;
    protected LayoutInflater inflater;
    protected OverScroller mScroller;
    //头尾内容布局
    protected View header;
    protected View footer;
    protected View contentView; //主内容
    public enum Type {OVERLAP,FOLLOW};
    protected Type type = Type.FOLLOW;

    protected float dy;  //每次移动的距离
    protected float dx;
    protected float mLastX;  //每次移动的最后坐标
    protected float mLastY;
    protected float mfirstY; //储存第一次的Y坐标
    protected float dsY;  //储存手指拉动的总距离
    protected boolean isInControl = false;  //滑动事件目前是否在本控件的控制中
    protected boolean isNeedMyMove;//是否需要自己移动
    protected boolean isMoveNow = false;
    protected Rect mRect = new Rect();     //存储拉动前的位置


    //移动参数：计算手指移动量的时候会用到这个值，值越大，移动量越小，若值为1则手指移动多少就滑动多少px
    protected final double MOVE_PARA = 2;
    //最大拉动距离，拉动距离越靠近这个值拉动就越缓慢
    protected int MAX_HEADER_PULL_HEIGHT = 600;
    protected int MAX_FOOTER_PULL_HEIGHT = 600;

    public BaseViewGroup(Context context) {
        super(context);
    }

    public BaseViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inflater = LayoutInflater.from(context);
        mScroller = new OverScroller(context);
    }

    public BaseViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getChildCount()>0){
            for (int i=0;i<getChildCount();i++){
                View child = getChildAt(i);
                measureChild(child,widthMeasureSpec,heightMeasureSpec);
            }
        }
        /**
         * 不能使用getHeight,因为这个参数是layout之后才有，和top bottom有关
         */
        MAX_HEADER_PULL_HEIGHT = header.getMeasuredHeight();
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (contentView!=null) {
            if(type == Type.OVERLAP){
                if (header!=null) {
                    header.layout(0, 0, getWidth(), header.getMeasuredHeight());
                }
                if (footer!=null) {
                    footer.layout(0, getHeight() - footer.getMeasuredHeight(), getWidth(), getHeight());
                }
            }else if(type == Type.FOLLOW){
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

    @Override
    protected void onFinishInflate() {
        contentView = getChildAt(0);
        if (contentView==null) {
            return;
        }
        setPadding(0,0,0,0);
        contentView.setPadding(0,0,0,0);
        contentView.bringToFront(); //把内容放在最前端
        super.onFinishInflate();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (isNeedMyMove){
            Log.i("ustory","拦截事件");
        }else{
            Log.i("ustory","未拦截事件-交给子View");
        }
        return isNeedMyMove ;
    }

    /**
     * 判断目标View是否滑动到顶部-还能否继续滑动
     * @return
     */
    protected boolean isChildScrollToTop() {
        return !ViewCompat.canScrollVertically(contentView, -1);
    }

    /**
     * 是否滑动到底部
     * @return
     */
    protected boolean isChildScrollToBottomFull(boolean isFull) {
        return !ViewCompat.canScrollVertically(contentView, 1);
    }

    /**
     * 判断当前状态是否拉动顶部
     */
    protected boolean isTop(){
        if (type==Type.OVERLAP){
            return contentView.getTop()>0;
        }else if (type==Type.FOLLOW){
            return getScrollY()<0;
        }else
            return false;
    }
    protected boolean isBottom(){
        if (type==Type.OVERLAP){
            return contentView.getTop()<0;
        }else if(type==Type.FOLLOW){
            return getScrollY()>0;
        }else
            return false;
    }

    /**
     * 这里即使快速滑动产生的误差值，30这个值还好，一般手不会这么快
     * 如果为了需求可以调整，比如50
     * @return
     */
    protected boolean isFlow(){
        if (type==Type.OVERLAP){
            return contentView.getTop()<30 && contentView.getTop()>-30;
        }else if (type==Type.FOLLOW){
            Log.i("ustory","isFlow getScrollY()="+getScrollY());
            return getScrollY()>-50 && getScrollY()<50;
        }else
            return false;
    }

    /**
     * 判断是否需要由该控件来控制滑动事件
     *
     * 分析判断条件
     * 1.第一个判断条件就是必须是垂直滑动
     * 2.我们判断子控件滑动是否到达顶部，通过isTop字段标识
     * 3.如果单单通过isTop 实践证明会出现头部局可以被拉出和隐藏，但滚动的内容区无法滚动
     *  因为isTop在dispatchmove里判断始终为true,onIntercept判断为真，事件到达不了内容区
     * 4.通过isTop 再加上dy>0(也就是下拉)，这是可以实现头布局被拉出的效果，却不能隐藏，
     *  因为dy 上推的时候dy<0(那么这样可以吗 isTop&&dy>0||isTop&&dy<0,试一下，我发现好脑残，
     *  这个dy只要滑动就会产生，怎么可能dy=0等于0,除非水平滑动，实际效果丑陋无比)
     * 5.getScrollY()这个我还没用,头布局出来和隐藏过程他是小于0到等于0的过程，那是不是我们只通过
     * 这个条件判断不等于0就拦截了（实际发现一开始等于0怎么办，头布局根本出不来，又在分析
     *  isTop&&getScrollY!=0 可不可以,还是一开始移动不了getScrollY等于0），
     *  因此一开始的滑动不能用getScrollY判断，
     *
     * 6因此一开始我肯定是下拉的，我通过(isTop&&dy>0)判断没问题，上推的时候我通过getScrollY()<0就可以
     *  当getScrollY()逐渐变小等于0时候就不拦截,交给ContentView
     */
    protected boolean isNeedMyMove() {
        if (contentView==null){
            return false;
        }
        Log.i("ustory","dy ="+dy+"dx="+dx);
        if (Math.abs(dy)<Math.abs(dx)){
            Log.i("ustory","dy < dx");
            return false;
        }
        boolean isTop = isChildScrollToTop();
        if (isTop){
            Log.i("ustory","ContentView scroll top");
        }else{
            Log.i("ustory","ContentView is scroll");
        }
        if(type==Type.FOLLOW){
            if (header!=null) {
                //其中的20是一个防止触摸误差的偏移量
                Log.i("ustory","isNeedMyMove getScrollY()="+getScrollY());
                Log.i("ustory","isNeedMyMove dy="+dy);
               // if (isTop && dy > 0 || getScrollY() < 0 - 20) {
               // if (isTop && dy > 0 || isTop && dy < 0 ) {
               // if (isTop && getScrollY()!=0 ) {
                if (isTop && dy > 0 || getScrollY() < 0){
                    Log.i("ustory","拦截条件成立");
                    return true;
                }
            }
        }
        Log.i("ustory","拦截条件不成立");
        return false;
    }

    public void setHeader() {
        View headerView = inflater.inflate(R.layout.default_header,this,true);
        this.header = getChildAt(getChildCount() - 1);
        contentView.bringToFront(); //把内容放在最前端
        requestLayout();
        Log.i("headerview",""+this.header.getHeight());
    }
}