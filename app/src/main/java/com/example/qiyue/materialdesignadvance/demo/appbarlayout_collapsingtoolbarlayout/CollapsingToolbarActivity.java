package com.example.qiyue.materialdesignadvance.demo.appbarlayout_collapsingtoolbarlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.qiyue.materialdesignadvance.R;

/**
 * 3.CollapsingToolbarLayout
 可以实现Toolbar折叠效果.

 注意:1.AppBarLayout设置固定高度，并且要实现折叠效果必须比toolbar的高度要高。
 2.CollapsingToolbarLayout最好设置成match_parent

 app:layout_collapseMode="parallax"
 parallax:视差模式，在折叠的时候会有折叠视差效果。
 一般搭配layout_collapseParallaxMultiplier=“0.5”视差的明显程度
 be between 0.0 and 1.0.
 none:没有任何效果，往上滑动的时候toolbar会首先被固定并推出去。
 pin:固定模式，在折叠的时候最后固定在顶端。
 */
public class CollapsingToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);
    }
}
