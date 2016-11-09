package com.example.qiyue.materialdesignadvance.demo.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

/**
 * Created by qiyue on 2016/10/26.
 */
public class CanvasView extends View {
    Paint paint = new Paint();
    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int currentHeight = 0;
    private int count = 1;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 绘制椭圆
         */
        paint.setColor(Color.RED);
        paint.setTextSize(40);

        drawText(canvas,"canvas.drawPoint绘制一个点");
        currentHeight = currentHeight+50;
        paint.setStrokeWidth(30);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(50,currentHeight,paint);

        drawText(canvas,"canvas.drawLine绘制一条线");
        currentHeight = currentHeight+50;
        canvas.drawLine(10,currentHeight,600,currentHeight,paint);

        drawText(canvas,"canvas.drawRect绘制一个矩形");
        currentHeight = currentHeight+50;
        Rect rect = new Rect(10,currentHeight,400,currentHeight+=50);
        canvas.drawRect(rect,paint);

        drawText(canvas,"canvas.drawCircle绘制一个圆形");
        currentHeight = currentHeight+50;
        canvas.drawCircle(100,currentHeight+=100,80,paint);
        currentHeight = currentHeight + 50;


        currentHeight = currentHeight + 50;
        drawText(canvas,"canvas.drawOval绘制一个椭圆形");
        currentHeight = currentHeight + 50;
        RectF rectF = new RectF(10,currentHeight,400,currentHeight+=200);
        canvas.drawOval(rectF,paint);

        currentHeight = currentHeight + 50;
        drawText(canvas,"canvas.drawArc绘制一个扇形");
        currentHeight = currentHeight + 50;
        RectF rectF1 = new RectF(10,currentHeight,400,currentHeight+=400);
        canvas.drawArc(rectF1,180,300,true,paint);

        currentHeight = currentHeight + 50;
        drawText(canvas,"canvas.drawArc绘制一个角度圆");
        currentHeight = currentHeight + 50;
        RectF rectF2 = new RectF(10,currentHeight,400,currentHeight+=400);
        canvas.drawArc(rectF2,180,300,false,paint);

        //canvas.drawColor(Color.BLUE);
        currentHeight = currentHeight + 50;
        drawText(canvas,"canvas.drawColor/drawARGB绘制画布颜色");

        currentHeight = currentHeight+50;
        drawText(canvas,"canvas.drawRoundRect绘制一个圆角矩形");
        currentHeight = currentHeight+50;
        RectF roundRect = new RectF(10,currentHeight,400,currentHeight+=100);
        canvas.drawRoundRect(roundRect,20,20,paint);

        currentHeight = currentHeight+50;
        drawText(canvas,"canvas.translate平移100绘制一个矩形");
        currentHeight = currentHeight+50;
        canvas.save();
        canvas.translate(100,0);
        Rect tran_rect = new Rect(0,currentHeight,400,currentHeight+=400);
        canvas.drawRect(tran_rect,paint);

        currentHeight = currentHeight+50;
        drawText(canvas,"canvas.translate后再次绘制");
        currentHeight = currentHeight+50;
        Rect tran_rect2 = new Rect(0,currentHeight,200,currentHeight+=200);
        canvas.drawRect(tran_rect2,paint);


        /**
         *  "结论：平移之前的效果保持不变，平移后绘制的效果
         *  仍然是平移的效果，也就是说Android机制是
         *  每次执行canvas.drawXXX"
         *  后会在当前位置生成一个新的画布下次操作也都是
         *   针对这个画布";
         *
         *
         */
        canvas.restore();
        currentHeight = currentHeight+50;
        drawText(canvas,"通过canvas.save和canvas.restore解决这个问题");

        currentHeight = currentHeight+50;
        drawText(canvas,"通过rotate实现画布旋转");
        canvas.save();
        currentHeight = currentHeight+50;
        canvas.rotate(-45,50,currentHeight);
        Rect tran_rect3= new Rect(50,currentHeight,200,currentHeight+=200);
        canvas.drawRect(tran_rect3,paint);
        canvas.restore();

        canvas.save();
        currentHeight = currentHeight+50;
        drawText(canvas,"通过canvas.skew实现画布倾斜");

        canvas.skew(0f,0.5f);

        Rect tran_rect4 = new Rect(50,currentHeight,200,currentHeight+=200);
        canvas.drawRect(tran_rect4,paint);
        canvas.restore();

        currentHeight = currentHeight+50;
        drawText(canvas,"通过canvas.scale实现画布缩放");
        currentHeight = currentHeight+50;
        canvas.save();
        //sx,sy：分别对x/y方向的一个缩放系数,画布的缩放会导致里面所有的绘制的东西都会有一个缩放效果
		canvas.scale(2.5f, 1f);
        Rect scale_rect = new Rect(50,currentHeight,200,currentHeight+=200);
        canvas.drawRect(scale_rect,paint);
        canvas.restore();


        canvas.save();
        currentHeight = currentHeight+50;
        drawText(canvas,"通过canvas.clipRect裁剪");
        currentHeight = currentHeight+50;
        paint.setStyle(Paint.Style.FILL);
        Rect clipRect = new Rect(50,currentHeight,200,currentHeight+=250);
        canvas.drawRect(clipRect,paint);
        canvas.clipRect(new Rect(50,currentHeight-250,100,currentHeight-100));
        canvas.drawColor(Color.YELLOW);
        canvas.restore();
        paint.reset();


        paint.setColor(Color.RED);
        paint.setTextSize(40);
        currentHeight = currentHeight+50;
        drawText(canvas,"通过Region裁剪");
        currentHeight = currentHeight+50;
        RectF pathrect = new RectF(50,currentHeight,200,currentHeight+=250);
        Path path = new Path();
        path.addRect(pathrect, Path.Direction.CCW);
      //  canvas.drawPath(path,paint);
        Region region = new Region(50,currentHeight-250,200,currentHeight-100);
		Region region1 = new Region();
		region1.setPath(path, region);//path的椭圆区域和矩形区域进行交集
		//结合区域迭代器使用（得到图形里面的所有的矩形区域）
		RegionIterator iterator = new RegionIterator(region1);

		Rect rectp = new Rect();
		while (iterator.next(rectp)) {
			canvas.drawRect(rectp, paint);
		}
//
//        Rect rectF4 = new Rect(50,currentHeight-250,200,currentHeight-30);
//        //合并
//		region.union(rectF4);
//		region.op(rectF4, Region.Op.INTERSECT);//交集部分





        L.i("currentHeight="+currentHeight);
    }



    private void drawText(Canvas canvas,String text){
        /**
         * y等于本身字的大小加上上边界顶部距离，所以这里text是40，上边界就是30
         */
        paint.reset();
        paint.setColor(Color.BLUE);
        paint.setTextSize(40);
        paint.setAntiAlias(true);
        canvas.drawText(count+"."+text,10,currentHeight+=70,paint);
        count ++;
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
    }
}
