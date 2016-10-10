package com.example.qiyue.materialdesignadvance;

import android.content.Intent;

import com.example.qiyue.materialdesignadvance.demo.appbarLayout_tablayout_viewpager.AppBarTabLayoutViewPagerActivity;
import com.example.qiyue.materialdesignadvance.demo.appbarlayout_collapsingtoolbarlayout.CollapsingToolbarActivity;
import com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_appbarlayout.AppbarLayoutActivity;
import com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_behavior.CoordinatorBehaviorActivity;
import com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_custom_behavior.CoordinatorLayoutCustomBehaviorActivity;
import com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_custom_behavior.CoordinatorLayoutScrollBehaviorActivity;
import com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_custom_behavior.RecyclerViewScrollBehaviorActivity;
import com.example.qiyue.materialdesignadvance.demo.parallelviewpager.ParalleViewPagerActivity;
import com.example.qiyue.materialdesignadvance.demo.scrolltoolbar.RecyclerViewScrollToolBarActivity;

import java.util.List;

public class MainActivity extends BaseActivity {

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(List<String> datas) {
        datas.add("监听RecyclerView滚动，显示和隐藏toolbar和floatButton");
        datas.add("通过CoordinatorLayout和Behavior监听滑动");
        datas.add("自定义Behavior监听View的大小和位置");
        datas.add("自定义Behavior监听NestScrollView的滚动");
        datas.add("自定义Behavior监听RecyclerView的滚动");
        datas.add("自定义ViewPager实现平行空间引导页");
        datas.add("通过AppBarLayout实现子控件跟随滚动");
        datas.add("AppBarLayout和TabLayout和ViewPager实现滚动Tab效果");
        datas.add("通过AppBarLayout和CollapsingToolbarLayout实现折叠");
    }

    @Override
    protected void initSwitch(int position) {
        switch (position) {
            case Constants.RECYCLERVIEW_SCROLL_HIDE_TOOLBAR:
                startAty(RecyclerViewScrollToolBarActivity.class);
                break;
            case Constants.COORDINATOR_BEHAVIOR:
                startAty(CoordinatorBehaviorActivity.class);
                break;
            case Constants.COORDINATOR_CUSTOM_POSITION_BEHAVIOR:
                startAty(CoordinatorLayoutCustomBehaviorActivity.class);
                break;
            case Constants.COORDINATOR_CUSTOM_SCROLL_BEHAVIOR:
                startAty(CoordinatorLayoutScrollBehaviorActivity.class);
                break;
            case Constants.COORDINATOR_CUSTOM_SCROLL_RECYCLER_BEHAVIOR:
                startAty(RecyclerViewScrollBehaviorActivity.class);
                break;
            case Constants.PARALLE_SPLASH_VIEWPAGER:
                startAty(ParalleViewPagerActivity.class);
                break;
            case Constants.APPBARLAYOUT:
                startAty(AppbarLayoutActivity.class);
                break;
            case Constants.APPBARLAYOUT_TABLAYOUT_VIEWPAGER:
                startAty(AppBarTabLayoutViewPagerActivity.class);
                break;
            case Constants.APPBARLAYOUT_COLLAPSINGTOOLBAR:
                startAty(CollapsingToolbarActivity.class);
                break;
        }
    }


}
