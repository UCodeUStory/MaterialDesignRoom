package com.example.qiyue.materialdesignadvance.demo.customviewpager;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.qiyue.materialdesignadvance.R;

import java.util.ArrayList;
import java.util.List;

public class CustomViewPagerActivity extends AppCompatActivity {

    private int colors[] = {0xFF442344,0xFF412444
            ,0xFF411444,0xFF444564,0xFF23444};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_pager);
   /*     CustomViewPager customViewPager = (CustomViewPager) findViewById(R.id.customViewPager);
        List<LinearLayout> views = new ArrayList<>();
        for (int i=0;i<5;i++){
            LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.viewpager_item,null);
            view.findViewById(R.id.imageView).setBackgroundColor(colors[i]);
            views.add(view);
        }
        CustomViewPagerAdapter adapter = new CustomViewPagerAdapter();
        adapter.setDatas(views);
        customViewPager.setAdapter(adapter);*/
    }
}
