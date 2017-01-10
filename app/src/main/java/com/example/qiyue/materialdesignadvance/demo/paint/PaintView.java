package com.example.qiyue.materialdesignadvance.demo.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class PaintView extends View {

	public PaintView(Context context) {
		super(context);
		mPaint = new Paint();
	}

	private Paint mPaint;
	private Paint mPaint2;

	public PaintView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		mPaint2 = new Paint();
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//重置
		mPaint.reset();
		mPaint.setColor(Color.RED);
//		mPaint.setAlpha(255);
		//设置画笔的样式
//		mPaint.setStyle(Paint.Style.FILL);//填充内容
//		mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		mPaint.setStyle(Paint.Style.STROKE);//描边
		//画笔的宽度
		mPaint.setStrokeWidth(50);
		//线帽
		//mPaint.setStrokeCap(Paint.Cap.BUTT);//没有
		//mPaint.setStrokeCap(Paint.Cap.ROUND);//圆形
		//mPaint.setStrokeCap(Paint.Cap.SQUARE);//方形
		
//		mPaint.setStrokeJoin(Paint.Join.MITER);//锐角
//		mPaint.setStrokeJoin(Paint.Join.ROUND);//圆弧
//		mPaint.setStrokeJoin(Paint.Join.BEVEL);//直线
		
//		canvas.drawCircle(100, 100, 100, mPaint);

		mPaint.reset();
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Paint.Style.STROKE);//描边
		mPaint.setStrokeWidth(50);

		Path path = new Path();
		path.moveTo(100,60);
		path.lineTo(300,60);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		canvas.drawPath(path,mPaint);
		path.close();

		mPaint.reset();
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Paint.Style.STROKE);//描边
		mPaint.setStrokeWidth(50);
		path.moveTo(100,160);
		path.lineTo(300,160);
		mPaint.setStrokeCap(Paint.Cap.SQUARE);
		canvas.drawPath(path,mPaint);
		path.close();


		path.moveTo(100, 260);
		path.lineTo(300, 260);
		path.lineTo(100, 500);
		mPaint.setStrokeJoin(Paint.Join.MITER);
		canvas.drawPath(path, mPaint);
		
		path.moveTo(100, 400);
		path.lineTo(300, 500);
		path.lineTo(100, 700);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		canvas.drawPath(path, mPaint);
//		
		path.moveTo(100, 800);
		path.lineTo(300, 800);
		path.lineTo(100, 1100);
		mPaint.setStrokeJoin(Paint.Join.BEVEL);
		canvas.drawPath(path, mPaint);
	}
	
	

}
