package com.example.qiyue.materialdesignadvance.demo.parallelviewpager;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.qiyue.materialdesignadvance.R;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
public class ViewPagerTransformer implements ViewPager.PageTransformer, ViewPager.OnPageChangeListener {
    private static final float ROT_MOD = -18f;
    private int pageIndex;
    private boolean pageChanged = true;
    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        System.out.println("position:"+position+", positionOffset:"+positionOffset);
    }

    @Override
    public void onPageSelected(int position) {
        pageIndex = position;
        pageChanged = true;
        System.out.println("position_selected:"+position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 这个方法ViewPager在滚动的时候，有几个页面就会调用几页，
     * 第一个页面会传递过来一个position 0，第二个传递过来一个1，第三个传递过来一个2，一次类推
     * 当滚动的时候第一个页面position逐渐变为-1，其他页面也会逐渐减1
     * 因此当前页面完全显示时始终是0，在左边为负数依次递减，在右边的为整数依次递增
     * @param page 当前ViewPager容器
     * @param position
     */
    @Override
    public void transformPage(View page, float position) {

        Log.i("qiyue","position" + position);
        ViewGroup v = (ViewGroup) page.findViewById(R.id.rl);
        View bg_container = v.findViewById(R.id.bg_container);
        View first_background = v.findViewById(R.id.imageView0);
        View second_background = v.findViewById(R.id.imageView0_2);
        final MyScrollView scrollView = (MyScrollView) v.findViewById(R.id.mscv);


        //设置整个viewpager的背景颜色
       caculateColor(v,position);
        /**
         * 当移出去
         */
        if(position == 0) {
            /**
             * 避免在一个页面触发多次动画
             */
            if(pageChanged) {
                ObjectAnimator animator_bg1 = ObjectAnimator.ofFloat(first_background, "translationX", 0, -first_background.getWidth());
                animator_bg1.setDuration(400);
                animator_bg1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                       // Log.i("qiyue","animation.getAnimatedFraction()="+animation.getAnimatedFraction());
                        scrollView.smoothScrollTo((int) (scrollView.getWidth() * animation.getAnimatedFraction()), 0);
                    }
                });
                animator_bg1.start();

                ObjectAnimator animator_bg2 = ObjectAnimator.ofFloat(second_background, "translationX", second_background.getWidth(),0);
                animator_bg2.setDuration(400);
                animator_bg2.start();
                pageChanged = false;
            }
        }
        /**
         * 一定要让一次切换结束后背景位置要复原
         */
        if (position==-1||position==1){
            first_background.setTranslationX(0);
            second_background.setTranslationX(0);
            scrollView.smoothScrollTo(0, 0);
        }

        if(position<1 && position>-1) {
            final float width = first_background.getWidth();
            final float height = first_background.getHeight();
            final float rotation = ROT_MOD * position * -1.25f;
        //    Log.i("qiyue", "rotation=" + rotation);
            /**
             * 设置旋转中心点
             */
            bg_container.setPivotX(width * 0.5f);
            bg_container.setPivotY(height);
            bg_container.setRotation(rotation);
        }
    }

    public void caculateColor(View view,float position) {
        int bg1_green = view.getContext().getResources().getColor(R.color.bg1_green);
        int bg2_blue = view.getContext().getResources().getColor(R.color.bg2_blue);

        Integer tag = (Integer) view.getTag();
        View parent = (View) view.getParent();
        //颜色估值器
        ArgbEvaluator evaluator = new ArgbEvaluator();
        int color = bg1_green;
        if (tag.intValue() == pageIndex) {
            switch (pageIndex) {
                case 0:
                    color = (int) evaluator.evaluate(Math.abs(position), bg1_green, bg2_blue);
                    break;
                case 1:
                    color = (int) evaluator.evaluate(Math.abs(position), bg2_blue, bg1_green);
                    break;
                case 2:
                    color = (int) evaluator.evaluate(Math.abs(position), bg1_green, bg2_blue);
                    break;
                default:
                    break;
            }
            /**
             * 	//设置整个viewpager的背景颜色
             */
            parent.setBackgroundColor(color);

        }
    }
}
