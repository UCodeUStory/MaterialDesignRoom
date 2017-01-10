package com.example.qiyue.materialdesignadvance.demo.paint;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.qiyue.materialdesignadvance.R;

import net.qiujuer.genius.blur.StackBlur;

import java.util.Stack;

public class BlurActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur);
        Bitmap bitmap =  BitmapFactory.decodeResource(getResources(), R.drawable.test_filter_1);
        ImageView one = (ImageView) findViewById(R.id.iv_first);
        ImageView two = (ImageView) findViewById(R.id.iv_second);
        ImageView thrid = (ImageView) findViewById(R.id.iv_third);
        /**
         * 可以通过改变radius的值来改变模糊度，值越大，模糊度越大
         * ，radius<=0时则图片不显示；一般radius的值以20左右为佳！
         */
        Bitmap target = StackBlur.blur(bitmap,20,false);
        one.setImageBitmap(target);
        Bitmap target2 = StackBlur.blur(bitmap,20,false);
        two.setImageBitmap(target2);
        Bitmap target3 = StackBlur.blur(bitmap,20,false);
        thrid.setImageBitmap(target3);
    }
}
