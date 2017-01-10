package com.example.qiyue.materialdesignadvance;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_behavior.CoordinatorBehaviorActivity;
import com.example.qiyue.materialdesignadvance.demo.scrolltoolbar.FabScrollListener;
import com.example.qiyue.materialdesignadvance.demo.scrolltoolbar.HideScrollListener;
import com.example.qiyue.materialdesignadvance.demo.scrolltoolbar.RecyclerViewScrollToolBarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ListAdapter adapter;
    private RecyclerView recyclerView;
    protected ImageButton fab;
    Toolbar toolbar;
    private ArrayList<String> datas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = (ImageButton) findViewById(R.id.fab);
        init();
        initListener();
        recyclerView.addOnScrollListener(new FabScrollListener(new HideScrollListener() {
            @Override
            public void onHide() {
                hideAnimator();
            }

            @Override
            public void onShow() {
                showAnimator();
            }
        }));
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getAnimationType()==0) {
                    adapter = new ListAdapter(datas);
                    adapter.setAnimationType(1);
                    adapter.setLayoutType(1);
                    adapter.setOnItemClick(mOnItemClick);
                    recyclerView.setAdapter(adapter);
                }else{
                    adapter = new ListAdapter(datas);
                    adapter.setAnimationType(0);
                    adapter.setLayoutType(0);
                    adapter.setOnItemClick(mOnItemClick);
                    recyclerView.setAdapter(adapter);
                }
               // adapter.notifyDataSetChanged();
            }
        });
    }

    protected abstract int initLayout();


    protected void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    private void init(){
        this.recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.datas = new ArrayList<>();
        initData(datas);
        this.adapter = new ListAdapter(datas);
    }

    private ListAdapter.OnItemClick mOnItemClick;

    private void initListener() {
        this.mOnItemClick = new ListAdapter.OnItemClick() {
            @Override
            public void onClick(View view, int position) {
                initSwitch(position);

            }

        };
        adapter.setOnItemClick(mOnItemClick);
    }


    protected abstract void initData(List<String> datas);

    protected abstract void initSwitch(int position);


    protected void startAty(Class<?>cls){
        startActivity(new Intent(this,cls));
    }

    public void hideAnimator() {
        // 隐藏动画--属性动画
        /**
         * animate 相当于 创建一个ViewPropertyAnimator,调用translationY平移到指定位置
         *
         */
       //-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(3));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) fab.getLayoutParams();

        fab.animate().translationY(fab.getHeight() + layoutParams.bottomMargin).setInterpolator(new AccelerateInterpolator(3));
    }

    public void showAnimator() {
        // 显示动画--属性动画
      //  toolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) fab.getLayoutParams();
        fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3));

    }


}
