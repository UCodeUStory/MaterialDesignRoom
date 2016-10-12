package com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.qiyue.materialdesignadvance.R;

/**
 * Created by Administrator on 2016/10/12 0012.
 */
public class CircleViewBehavior extends CoordinatorLayout.Behavior<CircleImageView> {

    private float mDependencyStartYPosition = 0f;

    private float mChildStartYPosition = 0f;

    private float mChildStartXPosition = 0f;
    private float mChildWidth = 0f;
    private float mChidHeight = 0f;

    /**保存固定值**/

    private float mFinalChildStartYPosition = 0f;
    private float mFinalChildWidth = 0f;
    private float mFinalChildHeight = 0f;

    public CircleViewBehavior(Context context, AttributeSet attrs) {
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        return dependency instanceof Toolbar;
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
        if (mDependencyStartYPosition == 0){
            mDependencyStartYPosition = dependency.getY();
        }

        if (mChildStartYPosition == 0){
            mChildStartYPosition = child.getY();
            mFinalChildStartYPosition = child.getY();
        }

        if (mChildStartXPosition == 0){
            mChildStartXPosition = child.getX();
        }
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if (mChildWidth == 0){
            mChildWidth = lp.width;
            mFinalChildWidth = lp.width;
        }
        if (mChidHeight == 0){
            mChidHeight = lp.height;
            mFinalChildHeight = lp.height;
            L.i("mFinalChildHeight11111="+mFinalChildHeight);
        }
        float percentageY = mChildStartYPosition/mDependencyStartYPosition;
        float percentageX = mChildStartXPosition/mDependencyStartYPosition;
        float percentageWidth = mChildWidth/mDependencyStartYPosition/2;
        float percentageHeight = mChidHeight/mDependencyStartYPosition/2;
        float dy = (dependency.getY()-mDependencyStartYPosition);
        L.i("dy="+dy);
        float percent = Math.abs(dy/mDependencyStartYPosition);
        L.i(dependency.getY()+"dependency.getY()");


        TextView view = (TextView)dependency.findViewById(R.id.textView);
        if (dependency.getY()==0){

            L.i("mFinalChildHeight="+mFinalChildHeight);

            child.setY(0);
            child.setX(0);
       //    child.layout(60,60,child.getRight(),child.getBottom());
            L.i("layout-info"+child.getTop()+"child.getY="+child.getY()+"child.getHeight="+child.getHeight()+"child.getWidth="+child.getWidth());
           /* if (child.getTop()!=565){
                child.layout(child.getLeft(),565,child.getRight(),child.getBottom());
            }*/
           // child.setX(0);
            view.setVisibility(View.VISIBLE);
        }else if(dependency.getY()>0){
            /**
             * 这里限制150,是因为快速滑动会有bug，不能正常缩小
             */
           // lp.width = (int) (mChildWidth+percentageWidth*dy);
            if ((mChidHeight+percentageHeight*dy)<150){
                lp.height = 100;
                lp.width = 100;
                L.i("1111111111111="+(mChidHeight+percentageHeight*dy));
                child.setLayoutParams(lp);
            }else{
                lp.height = (int) (mChidHeight+percentageHeight*dy);
                L.i("222222222222="+(mChidHeight+percentageHeight*dy));
                lp.width = (int) (mChildWidth+percentageWidth*dy);
                child.setLayoutParams(lp);
                view.setVisibility(View.GONE);
            }
           // lp.height = (int) (mChidHeight+percentageHeight*dy);
            child.setY((mChildStartYPosition+percentageY*dy));
            child.setX((mChildStartXPosition+percentageX*dy));
            child.setLayoutParams(lp);
        }

        caculateColor(dependency,percent);

        return true;
    }

    public void caculateColor(View view,float position) {
        int bg1_green = view.getContext().getResources().getColor(R.color.toolBar_1);
        int bg2_blue = view.getContext().getResources().getColor(R.color.toolBar_2);

        ArgbEvaluator evaluator = new ArgbEvaluator();
        int color = bg1_green;

        color = (int) evaluator.evaluate(Math.abs(position), bg1_green, bg2_blue);
        view.setBackgroundColor(color);
    }
}
