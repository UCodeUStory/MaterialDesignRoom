package com.example.qiyue.materialdesignadvance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.qiyue.materialdesignadvance.demo.scrolltoolbar.RecyclerViewScrollToolBarActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> datas = new ArrayList<>();
        datas.add("监听RecyclerView滚动，显示和隐藏toolbar和floatButton");
        datas.add("Demo2");
        ListAdapter adapter = new ListAdapter(datas);
        adapter.setOnItemClick(new ListAdapter.OnItemClick() {
            @Override
            public void onClick(View view, int position) {
                switch (position){
                    case Constants.RECYCLERVIEW_SCROLL_HIDE_TOOLBAR:
                        startActivity(new Intent(MainActivity.this, RecyclerViewScrollToolBarActivity.class));
                        break;
                }
            }
        });
        recyclerView.setAdapter(adapter);

    }

    private void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
