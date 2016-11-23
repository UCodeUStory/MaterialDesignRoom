package com.example.qiyue.materialdesignadvance.demo.paint.filter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.qiyue.materialdesignadvance.ListAdapter;
import com.example.qiyue.materialdesignadvance.R;

import java.util.ArrayList;
import java.util.List;

public class ListFilterActivity extends AppCompatActivity {


    private ListAdapter adapter;
    private RecyclerView recyclerView;

    private void initData(List<String> datas){
        datas.add("透明度滤镜");
        datas.add("浮雕滤镜");
        datas.add("去除红色");
        datas.add("反相效果");
        datas.add("颜色增强");
        datas.add("黑白效果");
        datas.add("红色和绿色交换效果");
        datas.add("复古效果");
        datas.add("调整饱和度");
        datas.add("绕红色系旋转");
        datas.add("过滤颜色");
        datas.add("图片混合滤镜蒙一层");

    }

    private void initSwitch(int position){
        Intent intent;
        switch (position){
            case 0:
                startAty(FilterActivity.class);
                break;
            case 1:
                startAty(EmBossFilterActivity.class);
                break;
            case 2:
                intent = new Intent(this,ColorMatrixOneActivity.class);
                intent.putExtra("flag",0);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this,ColorMatrixOneActivity.class);
                intent.putExtra("flag",1);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(this,ColorMatrixOneActivity.class);
                intent.putExtra("flag",2);
                startActivity(intent);
                break;
            case 5:
                intent = new Intent(this,ColorMatrixOneActivity.class);
                intent.putExtra("flag",3);
                startActivity(intent);
                break;
            case 6:
                intent = new Intent(this,ColorMatrixOneActivity.class);
                intent.putExtra("flag",4);
                startActivity(intent);
                break;
            case 7:
                intent = new Intent(this,ColorMatrixOneActivity.class);
                intent.putExtra("flag",5);
                startActivity(intent);
                break;
            case 8:
                intent = new Intent(this,ColorMatrixOneActivity.class);
                intent.putExtra("flag",6);
                startActivity(intent);
                break;
            case 9:
                intent = new Intent(this,ColorMatrixOneActivity.class);
                intent.putExtra("flag",7);
                startActivity(intent);
                break;
            case 10:
                intent = new Intent(this,ColorMatrixOneActivity.class);
                intent.putExtra("flag",8);
                startActivity(intent);
                break;
            case 11:
                intent = new Intent(this,ColorMatrixOneActivity.class);
                intent.putExtra("flag",9);
                startActivity(intent);
                break;


        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_filter);
        init();
        initListener();
        recyclerView.setAdapter(adapter);
    }

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

    protected void startAty(Class<?>cls){
        startActivity(new Intent(this,cls));
    }
}
