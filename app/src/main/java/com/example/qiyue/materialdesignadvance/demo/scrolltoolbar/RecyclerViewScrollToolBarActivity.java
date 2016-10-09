package com.example.qiyue.materialdesignadvance.demo.scrolltoolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.qiyue.materialdesignadvance.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewScrollToolBarActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ImageButton fab;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_scroll_tool_bar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        fab = (ImageButton) findViewById(R.id.fab);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("监听滚动的RecyclerView让toolbar隐藏");
        List<String> datas = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            datas.add("Android" + i);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addOnScrollListener(new FabScrollListener(new HideScrollListener() {
            @Override
            public void onHide() {
                hideAnimator();
            }

            @Override
            public void onShow() {
                showAnimator();
            }
        }));
        FabRecyclerAdapter adapter = new FabRecyclerAdapter(datas);
        mRecyclerView.setAdapter(adapter);


    }

    public void hideAnimator() {
        // 隐藏动画--属性动画
        /**
         * animate 相当于 创建一个ViewPropertyAnimator,调用translationY平移到指定位置
         *
         */
        toolbar.animate().translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(3));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) fab.getLayoutParams();

        fab.animate().translationY(fab.getHeight() + layoutParams.bottomMargin).setInterpolator(new AccelerateInterpolator(3));
    }

    public void showAnimator() {
        // 显示动画--属性动画
        toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) fab.getLayoutParams();
        fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));

    }
    /**
     * ViewPropertyAnimator提供了一种简单的方式来为View的部分属性设置动画，
     * 使用一个单一的Animator对象。它表现的更像 ObjectAnimator，
     * 因为他也要修改View对象的相应的属性值，但是当为多个属性同时设置动画时，它比 ObjectAnimator更高效，
     * 下面的代码片段显示了使用多个ObjectAnimator，单个ObjectAnimator，
     * 以及ViewPropertyAnimator来同时为View的x,y属性设置动画的情形：
     */

    /**1111
     * ObjectAnimator animX = ObjectAnimator.ofFloat(myView, "x", 50f);
     ObjectAnimator animY = ObjectAnimator.ofFloat(myView, "y", 100f);
     AnimatorSet animSetXY = new AnimatorSet();
     animSetXY.playTogether(animX, animY);
     animSetXY.start();
     */

    /**2222
     * PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", 50f);
     PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", 100f);
     ObjectAnimator.ofPropertyValuesHolder(myView, pvhX, pvyY).start();
     */

    /**333    链式调用，简洁，高效
     * myView.animate().x(50f).y(100f);
     */
}
