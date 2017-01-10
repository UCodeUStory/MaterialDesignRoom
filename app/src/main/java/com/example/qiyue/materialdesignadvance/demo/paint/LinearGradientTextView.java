package com.example.qiyue.materialdesignadvance.demo.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader.TileMode;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

public class LinearGradientTextView extends TextView {

	private TextPaint paint;
	private LinearGradient linearGradient;
	private Matrix matrix;
	private float translateX;
	private float deltaX = 20;

	public LinearGradientTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		paint = getPaint();
		//GradientSize=两个文字的大小
		String text = getText().toString();
		float textWidth = paint.measureText(text);
		int GradientSize =(int) (3*textWidth/text.length()); 
		linearGradient = new LinearGradient(0, 0, 300, 0, new int[]{0x22ea0dea,0xffffffff,0x22000000}, new float[]{0,0.5f,1}, TileMode.CLAMP);//边缘融合
		paint.setShader(linearGradient);
		matrix = new Matrix();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		float textWidth = getPaint().measureText(getText().toString());
		translateX += deltaX;
		if(translateX > textWidth + 1|| translateX < 1){
			deltaX = -deltaX;
		}
//		matrix.setScale(sx, sy)
		matrix.setTranslate(translateX, 0);
		linearGradient.setLocalMatrix(matrix);
		
		postInvalidateDelayed(50);
	}

}
