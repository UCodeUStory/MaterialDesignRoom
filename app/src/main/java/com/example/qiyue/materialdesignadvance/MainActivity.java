package com.example.qiyue.materialdesignadvance;

import com.example.qiyue.materialdesignadvance.demo.animation.BallActivity;
import com.example.qiyue.materialdesignadvance.demo.animation.GifAnimationActivity;
import com.example.qiyue.materialdesignadvance.demo.appbarLayout_tablayout_viewpager.AppBarTabLayoutViewPagerActivity;
import com.example.qiyue.materialdesignadvance.demo.appbarlayout_collapsingtoolbarlayout.CollapsingToolbarActivity;
import com.example.qiyue.materialdesignadvance.demo.canvas.CanvasActivity;
import com.example.qiyue.materialdesignadvance.demo.chart.ChartActivity;
import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.CollapsingToolbarLayoutActivity;
import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.ToolbarAnchorActivity;
import com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_appbarlayout.AppbarLayoutActivity;
import com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_behavior.CoordinatorBehaviorActivity;
import com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_custom_behavior.CoordinatorLayoutCustomBehaviorActivity;
import com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_custom_behavior.CoordinatorLayoutScrollBehaviorActivity;
import com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_custom_behavior.RecyclerViewScrollBehaviorActivity;
import com.example.qiyue.materialdesignadvance.demo.custom_animation_frame.CustomAnimationFrameActivity;
import com.example.qiyue.materialdesignadvance.demo.customdrawable.CustomDrawableActivity;
import com.example.qiyue.materialdesignadvance.demo.customheader.CustomAddHeaderViewActivity;
import com.example.qiyue.materialdesignadvance.demo.customheader.NewGroupActivity;
import com.example.qiyue.materialdesignadvance.demo.customviewpager.CustomViewPagerActivity;
import com.example.qiyue.materialdesignadvance.demo.hotfix.HotFixActivity;
import com.example.qiyue.materialdesignadvance.demo.meterial_design_animation.ReveralAnimationActivity;
import com.example.qiyue.materialdesignadvance.demo.nine_gride_password.NineGridPasswordActivity;
import com.example.qiyue.materialdesignadvance.demo.paint.BlurActivity;
import com.example.qiyue.materialdesignadvance.demo.paint.ComposeShaderActivity;
import com.example.qiyue.materialdesignadvance.demo.paint.LinearGradientActivity;
import com.example.qiyue.materialdesignadvance.demo.paint.PaintActivity;
import com.example.qiyue.materialdesignadvance.demo.paint.RadialSweepGradientActivity;
import com.example.qiyue.materialdesignadvance.demo.paint.ShaderActivity;
import com.example.qiyue.materialdesignadvance.demo.paint.filter.ListFilterActivity;
import com.example.qiyue.materialdesignadvance.demo.parallelviewpager.ParalleViewPagerActivity;
import com.example.qiyue.materialdesignadvance.demo.path.CustomSearchBarActvity;
import com.example.qiyue.materialdesignadvance.demo.path.CustomWaveActivity;
import com.example.qiyue.materialdesignadvance.demo.path.WaveActivity;
import com.example.qiyue.materialdesignadvance.demo.path.pathMeasure.PathAnimationActivity;
import com.example.qiyue.materialdesignadvance.demo.path.pathMeasure.PathMeasureActivity;
import com.example.qiyue.materialdesignadvance.demo.path.pathMeasure.ZanAnimationActivity;
import com.example.qiyue.materialdesignadvance.demo.radarview.RadarActivity;
import com.example.qiyue.materialdesignadvance.demo.scroller.CustomSlideMenu2Activity;
import com.example.qiyue.materialdesignadvance.demo.scroller.CustomSlideMenuActivity;
import com.example.qiyue.materialdesignadvance.demo.scrolltoolbar.RecyclerViewScrollToolBarActivity;
import com.example.qiyue.materialdesignadvance.demo.svg_vector.SVGVectorActivity;
import com.example.qiyue.materialdesignadvance.demo2.androidannotation.AnnotationActivity;
import com.example.qiyue.materialdesignadvance.demo2.androidannotation.AnnotationActivity_;
import com.example.qiyue.materialdesignadvance.demo2.circlepassword.CirclePasswordActivity;
import com.example.qiyue.materialdesignadvance.demo2.dbexecutor.DBExecutorActivity;
import com.example.qiyue.materialdesignadvance.demo2.diffutil.DiffUtilActivity;
import com.example.qiyue.materialdesignadvance.demo2.greendao3.GreenDaoActivity;
import com.example.qiyue.materialdesignadvance.demo2.inputpassword.CustomPasswordActivity;
import com.example.qiyue.materialdesignadvance.demo2.json.JsonActivity;
import com.example.qiyue.materialdesignadvance.demo2.loadmorewrapper.LoadMoreWrapperActivity;
import com.example.qiyue.materialdesignadvance.demo2.snappydb.SnappyActivity;
import com.example.qiyue.materialdesignadvance.demo2.sortlistadapter.SortedListActivity;
import com.example.qiyue.materialdesignadvance.demo2.surfaceview.SurfaceViewActivity;
import com.example.qiyue.materialdesignadvance.demo2.tech.AllTechActivity;
import com.example.qiyue.materialdesignadvance.demo2.texture.TextureActivity;
import com.example.qiyue.materialdesignadvance.demo2.webviewjs.WebActivity;

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
       // datas.add("分析事件传递机制demo写在别处");
        datas.add("自定义HorizontalScrollView实现侧滑效果");
        datas.add("自定义LinearLayout实现侧滑效果");
        datas.add("自定义ViewGroup实现侧滑效果");
        datas.add("自定义ViewGroup加上ViewDragHelper实现侧滑效果");
        datas.add("自定义ViewGroup实现ViewPager");
        datas.add("Paint完全解析");
        datas.add("Paint图片渲染器");
        datas.add("Paint线性渲染器");
        datas.add("Nuaw热修复解坑之路");
        datas.add("Paint环形渲染器和梯度渲染器");
        datas.add("Paint组合渲染器");
        datas.add("Paint滤镜");
        datas.add("canvas高级");
        datas.add("自定义实现雷达效果");
        datas.add("自定义实现九宫格解锁效果");
        datas.add("自定义柱状图");
        datas.add("属性动画应用");
        datas.add("高斯模糊");
        datas.add("WebView双向通信（代码混淆时功能就不好使,因此在proguard-rules.pro过滤掉）");
        datas.add("自定义容器实现万能添加头布局");
        datas.add("自定义容器实现添加头布局-通过ViewHelperDrag");
        datas.add("自定义FlowTagLayout");
        datas.add("技术总结");
        datas.add("自定义Drawable非xml实现揭露效果");
        datas.add("自定义SearchView");
        datas.add("绘制贝塞尔曲线");
        datas.add("自定义水波纹View");
        datas.add("PathMeasure高级");
        datas.add("PathAnimation");
        datas.add("自定义直播点赞效果");
        datas.add("SurfaceView解决更细的帧和阻塞UI问题");
        datas.add("自定义TextureView实现时钟");
        datas.add("自定义PasswordView");
        datas.add("自定义CirclePasswordView");
        datas.add("数据传递效率优化");
        datas.add("优化RecyclerView更新工具之DiffUtil");
        datas.add("优化RecyclerView排序工具之SortListAdapterCallBack");
        datas.add("AndroidAnnotation");
        datas.add("Android中noSql-SnappyDB");
        datas.add("GreenDAO3");
        datas.add("DBExecutor");
        datas.add("LoadMoreWrapper");

        // datas.add("Android-gif-drawable");
        datas.add("混淆配置减小apk大小");
        datas.add("性能优化宏观和微观");

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
            case Constants.CUSTOM_SLIDLE_MENU:
                startAty(CustomSlideMenuActivity.class);
                break;
            case Constants.CUSTOM_SLIDE_MENU_2:
                startAty(CustomSlideMenu2Activity.class);
                break;
            case Constants.CUSTOM_VIEWPAGER:
                startAty(CustomViewPagerActivity.class);
                break;
            case Constants.PAINT:
                startAty(PaintActivity.class);
                break;
            case Constants.SHADER_BITMAPSHADER:
                startAty(ShaderActivity.class);
                break;
            case Constants.SHADER_LINEARGRADIENT:
                startAty(LinearGradientActivity.class);
                break;
            case Constants.HOT_FIX:
                startAty(HotFixActivity.class);
                break;
            case Constants.FILTER:
                startAty(ListFilterActivity.class);
                break;
            case Constants.CANVAS:
                startAty(CanvasActivity.class);
                break;
            case Constants.CIRCLE_SWEEP:
                startAty(RadialSweepGradientActivity.class);
                break;
            case Constants.COMPOSE_SHADER:
                startAty(ComposeShaderActivity.class);
                break;
            case Constants.RADAR_VIEW:
                startAty(RadarActivity.class);
                break;
            case Constants.NINE_GRID_PASSWORD:
                startAty(NineGridPasswordActivity.class);
                break;
            case Constants.HISTOGRAM:
                startAty(ChartActivity.class);
                break;
            case Constants.PROPERTY_ANIMATION:
                startAty(BallActivity.class);
                break;
            case Constants.BLUR_VIEW:
                startAty(BlurActivity.class);
                break;
            case Constants.WEB_VIEW_JS_ANDROID:
                startAty(WebActivity.class);
                break;

            case Constants.ADD_HEADERVIEW_FOOTERVIEW:
                startAty(NewGroupActivity.class);
                break;
            case Constants.TECH_ALL:
                startAty(AllTechActivity.class);
                break;
            case Constants.CUSTOM_DRAWABLE:
                startAty(CustomDrawableActivity.class);
                break;
            case Constants.CUSTOM_SEARCHVIEW:
                startAty(CustomSearchBarActvity.class);
                break;
            case Constants.LEARN_CURVE:
                startAty(CustomWaveActivity.class);
                break;
            case Constants.CUSTOM_WAVE:
                startAty(WaveActivity.class);
                break;
            case Constants.LEARN_PATHMEASURE:
                startAty(PathMeasureActivity.class);
                break;
            case Constants.PATH_ANIMATION:
                startAty(PathAnimationActivity.class);
                break;
            case Constants.ZAN_ANIMATION:
                startAty(ZanAnimationActivity.class);
                break;
            case Constants.SUFRACEVIEW:
               // startAty(SurfaceViewActivity.class);
                break;
            case Constants.CUSTOM_TEXTURE:
                startAty(TextureActivity.class);
                break;
            case Constants.CUSTOM_INPUTPASSWORD:
                startAty(CustomPasswordActivity.class);
                break;
            case Constants.CUSTOM_CIRCLE_PASSWORD_VIEW:
                startAty(CirclePasswordActivity.class);
                break;
            case Constants.EFFECTIVE_JSON:
                startAty(JsonActivity.class);
                break;
            case Constants.DIFFUTIL:
                startAty(DiffUtilActivity.class);
                break;
            case Constants.SORTLIST:
                startAty(SortedListActivity.class);
                break;
            case Constants.ANDROID_ANNOTATION:
                startAty(AnnotationActivity_.class);
                break;
            case Constants.SNAPPY:
                startAty(SnappyActivity.class);
                break;
            case Constants.GREENDAO:
                startAty(GreenDaoActivity.class);
                break;
            case Constants.DBEXECUTOR:
                startAty(DBExecutorActivity.class);
                break;
            case Constants.LOADMOREWRAPPER:
                startAty(LoadMoreWrapperActivity.class);
                break;
         /*   case Constants.ANDROID_GIF:
                startAty(GifAnimationActivity.class);
                break;*/

        }
    }



}
