package com.example.qiyue.materialdesignadvance.demo.paint;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.net.Uri;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qiyue.materialdesignadvance.R;
import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class PaintActivity extends AppCompatActivity {

    private TextView mTextView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        mTextView = (TextView) findViewById(R.id.tv_textView);
        mTextView.setText(" 1.Paint 设置颜色\n 2.Paint Style 填充还是描边，描边的宽度是多少、\n " +
                "3.Paint 笔帽setStrokeCap是园还是方\n"+
        " 4.Paint设置相交处的处理setSttrokeJoin是锐角弧线还是直线\n");

        final int bg1_green = getResources().getColor(R.color.paint_demo_bg_1);
        final int bg2_blue = getResources().getColor(R.color.paint_demo_bg_2);

        final ArgbEvaluator evaluator = new ArgbEvaluator();
        final LinearLayout lly = (LinearLayout)findViewById(R.id.lly_container);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1,100);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //animation.getAnimatedFraction();  这个是从0到1
                //animation.getAnimatedValue();是100
                L.i("fraction="+animation.getAnimatedFraction());
                int color = (int) evaluator.evaluate(Math.abs(animation.getAnimatedFraction()), bg1_green, bg2_blue);
                lly.setBackgroundColor(color);
            }
        });
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(Integer.MAX_VALUE);
        valueAnimator.start();

    }


}
