package com.example.qiyue.materialdesignadvance;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.qiyue.materialdesignadvance.demo.coordinatorlayout_behavior.CoordinatorBehaviorActivity;
import com.example.qiyue.materialdesignadvance.demo.scrolltoolbar.RecyclerViewScrollToolBarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ListAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        init();
        initListener();
        recyclerView.setAdapter(adapter);
    }

    protected abstract int initLayout();


    protected void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    private void init(){
        this.recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> datas = new ArrayList<>();
        initData(datas);
        this.adapter = new ListAdapter(datas);
    }


    private void initListener() {
        adapter.setOnItemClick(new ListAdapter.OnItemClick() {
            @Override
            public void onClick(View view, int position) {
                initSwitch(position);

            }

        });
    }


    protected abstract void initData(List<String> datas);

    protected abstract void initSwitch(int position);


    protected void startAty(Class<?>cls){
        startActivity(new Intent(this,cls));
    }
}
