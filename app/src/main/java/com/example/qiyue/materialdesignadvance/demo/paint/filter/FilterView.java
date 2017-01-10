package com.example.qiyue.materialdesignadvance.demo.paint.filter;

/**
 * Created by qiyue on 2016/10/20.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.qiyue.materialdesignadvance.R;


public class FilterView extends View {


    private Bitmap bitmap;
    private Paint paint;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    private int flag = 0;

    public FilterView(Context context,int flag) {
        super(context);
        this.flag = flag;
        init(context);
    }

    public FilterView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        init(context);
    }

    public FilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FilterView, defStyleAttr, 0);
        flag = a.getInt(R.styleable.FilterView_flag, 0);
        a.recycle();
        init(context);
    }

    private void init(Context context) {
        bitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.test_filter_1);
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        float floatArray[] = new float[0];
        if (flag==0) {
            /**
             * 去除红色
             */
            floatArray = new float[]{
                    0, 0, 0, 0, 0,
                    0, 1, 0, 0, 200,
                    0, 0, 1, 0, 0,
                    0, 0, 0, 1, 0,
            };
        }else if (flag == 1){
            //反相效果
            floatArray = new float[]{
                    -1,0,0,0,255,
                    0,-1,0,0,255,
                    0,0,-1,0,255,
                    0,0,0,1,0,
            };

        }else if(flag == 2){
            //颜色增强（可以起到一个变亮的效果）---矩阵缩放方式
            floatArray =new float[]{
                    1.2f,0,0,0,0,
                    0,1.2f,0,0,0,
                    0,0,1.2f,0,0,
                    0,0,0,1.2f,0,
            };

          //  ColorMatrix matrix = new ColorMatrix();
        //		matrix.set(src)
            //		matrix.reset();//重置
            //色彩缩放
            //		matrix.setScale(1, 1, 1.4f, 1);
        }else if(flag == 3){
            //处理图片为黑白图片（图像学：如何让图片成为灰色即黑白？R+G+B=1）
            /**
             *
             去色原理：只要把RGB三通道的色彩信息设置成一样；即：R＝G
             ＝B，那么图像就变成了灰色，并且，为了保证图像亮度不变，
             同一个通道中的R+G+B=1:如：0.213+0.715+0.072＝1；
             RGB=0.213, 0.715, 0.072；
             三个数字是根据色彩光波频率及色彩心理学计算出来的.
             */
            floatArray = new float[]{
                    0.213f, 0.715f,0.072f,0,0,
                    0.213f, 0.715f,0.072f,0,0,
                    0.213f, 0.715f,0.072f,0,0,
                    0, 		0,		0,	  1f,0,
            };
        }else if(flag == 4){
            //反色效果（比如红色和绿色交换----把第一行和第二行交换）
            floatArray = new float[]{
                0,1f,0,0,0,
                1f,0,0,0,0,
                0,0,1f,0,0,
                0,0,0,1f,0,
            };

        }else if(flag == 5){
            //复古风格
		    floatArray = new float[]{
				1/2f,1/2f,1/2f,0,0,
				1/3f,1/3f,1/3f,0,0,
				1/4f,1/4f,1/4f,0,0,
				0,0,0,1f,0,
			};
        }

        if (flag==6) {
            //饱和度设置（1，是原来不变；0灰色；>1增加饱和度）
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(saturation);
            paint.setColorFilter(new ColorMatrixColorFilter(matrix ));
            canvas.drawBitmap(bitmap, null,
                    new RectF(0, 0, 400, 400*bitmap.getHeight()/bitmap.getWidth()), paint);

        }else if(flag == 7){
            /**
             * axis,代表绕哪一个轴旋转，0,1,2
             * 	(0红色轴，1绿色，2蓝色)
             * degrees：旋转的度数
             */
            ColorMatrix matrix = new ColorMatrix();
            matrix.setRotate(0,progress);
            paint.setColorFilter(new ColorMatrixColorFilter(matrix ));
            canvas.drawBitmap(bitmap, null,
                    new RectF(0, 0, 400, 400*bitmap.getHeight()/bitmap.getWidth()), paint);
        }else if(flag == 8){
            ColorMatrix matrix = new ColorMatrix();
            /**
             *
             * mul,multiply相乘 ---缩放
             * add，相加---平移
             */
            paint.setColorFilter(new LightingColorFilter(0x00ff00, 0xff0000));
          //  paint.setColorFilter(new LightingColorFilter(0xffffff, (int) progress));
//		paint.setColorFilter(new LightingColorFilter(0xffffff, 0x000000));
            canvas.drawBitmap(bitmap, null,
                    new RectF(0, 0, 400, 400*bitmap.getHeight()/bitmap.getWidth()), paint);
        }else if(flag == 9){
            ColorMatrix matrix = new ColorMatrix();

            paint.setColorFilter(new LightingColorFilter(0x00ff00, 0xff0000));
            canvas.drawBitmap(bitmap, null,
                    new RectF(0, 0, 400, 400*bitmap.getHeight()/bitmap.getWidth()), paint);
        }else if(flag == 10){
            paint.setColorFilter(new PorterDuffColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, null,
                    new RectF(0, 0, 400, 400*bitmap.getHeight()/bitmap.getWidth()), paint);
        }else{
            ColorMatrix matrix = new ColorMatrix(floatArray);
            paint.setColorFilter(new ColorMatrixColorFilter(matrix));
            canvas.drawBitmap(bitmap, null,
                    new RectF(0, 0, 400, 400 * bitmap.getHeight() / bitmap.getWidth()), paint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }
    private int width = 0;
    private int height = 0;
    private float progress = 0f;
    private float saturation = 0f;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
			saturation += 0.3f;
			progress += 20f;
              //  progress = 0xff0000;
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
              //  progress = 0x000000;
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }
}
