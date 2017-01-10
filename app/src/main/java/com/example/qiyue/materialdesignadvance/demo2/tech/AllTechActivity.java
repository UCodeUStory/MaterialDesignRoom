package com.example.qiyue.materialdesignadvance.demo2.tech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.qiyue.materialdesignadvance.R;

import java.util.ArrayList;
import java.util.List;

public class AllTechActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ArrayList<TagResult> tagResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tech);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tagResults = new ArrayList<>();

        createData(count);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,tagResults);
        recyclerView.setAdapter(adapter);

    }
    private void createData(int count) {
        for (int i=0;i<count;i++){
            TagResult result = new TagResult();
            tagResults.add(result);
        }

        for (int j=0;j<tagResults.size();j++){
            initData(j,tagResults.get(j));
        }
    }



    int count = 3;


    private void initData(int j, TagResult tagResult) {
        switch (j){
            case 0:
                tagResult.type = "数据库";
                tagResult.datas = new ArrayList<String>();
                tagResult.datas.add("SQLite");
                tagResult.datas.add("SnappyDB");
                break;
            case 1:
                tagResult.type = "数据库框架";
                tagResult.datas = new ArrayList<String>();
                tagResult.datas.add("OrmLite");
                tagResult.datas.add("GreenDao");
                break;
            case 2:
                tagResult.type = "缓存策略";
                tagResult.datas = new ArrayList<String>();
                tagResult.datas.add("软引用");
                tagResult.datas.add("LruCache");
                tagResult.datas.add("SharePreferences");
                tagResult.datas.add("file");
                tagResult.datas.add("数据库");
                break;
            case 3:
                tagResult.type = "缓存策略";
                tagResult.datas = new ArrayList<String>();
                tagResult.datas.add("软引用");
                tagResult.datas.add("LruCache");
                tagResult.datas.add("SharePreferences");
                tagResult.datas.add("file");
                tagResult.datas.add("数据库");
                break;
        }
    }
}
