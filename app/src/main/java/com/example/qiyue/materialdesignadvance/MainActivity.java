package com.example.qiyue.materialdesignadvance;

import android.content.Intent;

import com.example.qiyue.materialdesignadvance.demo.appbarLayout_tablayout_viewpager.AppBarTabLayoutViewPagerActivity;
import com.example.qiyue.materialdesignadvance.demo.appbarlayout_collapsingtoolbarlayout.CollapsingToolbarActivity;
import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.CollapsingToolbarLayoutActivity;
import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.ToolbarAnchorActivity;
import com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_appbarlayout.AppbarLayoutActivity;
import com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_behavior.CoordinatorBehaviorActivity;
import com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_custom_behavior.CoordinatorLayoutCustomBehaviorActivity;
import com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_custom_behavior.CoordinatorLayoutScrollBehaviorActivity;
import com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_custom_behavior.RecyclerViewScrollBehaviorActivity;
import com.example.qiyue.materialdesignadvance.demo.custom_animation_frame.CustomAnimationFrameActivity;
import com.example.qiyue.materialdesignadvance.demo.meterial_design_animation.ReveralAnimationActivity;
import com.example.qiyue.materialdesignadvance.demo.parallelviewpager.ParalleViewPagerActivity;
import com.example.qiyue.materialdesignadvance.demo.scrolltoolbar.RecyclerViewScrollToolBarActivity;
import com.example.qiyue.materialdesignadvance.demo.svg_vector.SVGVectorActivity;

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
        datas.add("CollapsingToolBarLayout看来只能和Toolbar实现折叠后固定顶部,所以，通过隐藏Toolbar,来实现自定义固定布局和位置,也可以给Toolbar设置marginTop");
        datas.add("通过Toolbar 通过layout_anchor浮动在某一个View上,实现自定义位置");
        datas.add("使用5.0自带ViewAnimationUtils实现MaterialDesign揭露效果和水波纹效果");
        datas.add("自定义动画框架,模仿CoordinatorLayout");
        datas.add("SVG-Vector实现基本效果矢量图");
        datas.add("分析事件传递机制");
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
            case Constants.APPBARLAYOUT_COLLAPSINGTOOLBAR_TWO:
                startAty(CollapsingToolbarLayoutActivity.class);
                break;
            case Constants.CollAPSINGTOOLBAR_THREE:
                startAty(ToolbarAnchorActivity.class);
                break;
            case Constants.MaterialDesignAnimation:
                startAty(ReveralAnimationActivity.class);
                break;
            case Constants.CUSTOM_ANIMATION_FRAME:
                startAty(CustomAnimationFrameActivity.class);
                break;
            case Constants.SVG_VECTOR:
                startAty(SVGVectorActivity.class);
                break;
        }
    }


}
