
package com.example.qiyue.materialdesignadvance.demo.parallelviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.qiyue.materialdesignadvance.R;

/**
 * 平行空间效果，通过transformer 实现页面的位置监听，计算动画和颜色
 * 布局通过两层背景达到切换效果，底部通过ImageView 和一个水平不可滚动scrollView实现
 */
public class ParalleViewPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private int[] layouts = {
            R.layout.welcome_first,
            R.layout.welcome_three,
            R.layout.welcome_second
//			R.layout.welcome1,
//			R.layout.welcome_three,
//			R.layout.welcome_second
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paralle_view_pager);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        WelcomePagerAdapter adapter = new WelcomePagerAdapter(getSupportFragmentManager());
        System.out.println("offset:"+mViewPager.getOffscreenPageLimit());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(adapter);
       // WelcompagerTransformer transformer = new WelcompagerTransformer();
        ViewPagerTransformer transformer = new ViewPagerTransformer();
        mViewPager.setPageTransformer(true, transformer);
        mViewPager.setOnPageChangeListener(transformer);
    }

    class WelcomePagerAdapter extends FragmentPagerAdapter {

        public WelcomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = new TranslateFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("layoutId", layouts[position]);
            bundle.putInt("pageIndex", position);
            f.setArguments(bundle );
            return f;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 3;
        }




    }

}
