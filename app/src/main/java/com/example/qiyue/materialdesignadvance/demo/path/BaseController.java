package com.example.qiyue.materialdesignadvance.demo.path;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

/**
 * 控制
 * 1.开始开启动画
 * 2.结束动画
 * 3.绘制
 *
 *
 * EIT造型
 *
 * 这个Controller 持有Engine引用也即是View，为了就是获取必要参数
 *
 * 这个Controller 属于
 *
 * 必须重写就用abstract *
 *
 */
public abstract class BaseController {
    public static final int STATE_ANIM_NONE = 0;
    public static final int STATE_ANIM_START = 1;
    public static final int STATE_ANIM_STOP = 2;
    public static final int DEFAULT_ANIM_TIME = 5000;
    public static final float DEFAULT_ANIM_STARTF = 0;
    public static final float DEFAULT_ANIM_ENDF = 1;
	private MySearchView mySearchView;
	public int mState = STATE_ANIM_NONE;
	
	public abstract void draw(Canvas canvas,Paint paint);
	
	public void startAnim(){
		
	}
	
	public void resetAnim(){
		
	}
	
	public int getWidth(){
		return mySearchView.getWidth();
	}
	public int getHeight(){
		return mySearchView.getHeight();
	}
	
	public void setSearchView(MySearchView mySearchView){
		this.mySearchView = mySearchView;
	}

	public float mpro = -1;
	public ValueAnimator startViewAnimation(){
		ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
		valueAnimator.setDuration(800l);
		valueAnimator.setInterpolator(new LinearInterpolator());
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				mpro = (float) animation.getAnimatedValue();
				mySearchView.invalidate();
			}
		});
		
		valueAnimator.start();
		mpro = 0;
		return valueAnimator;
	}

	public void resetViewAnimation(){
		mySearchView.invalidate();
	}
	
}
