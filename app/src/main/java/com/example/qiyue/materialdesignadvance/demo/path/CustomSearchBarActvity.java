package com.example.qiyue.materialdesignadvance.demo.path;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.qiyue.materialdesignadvance.R;

public class CustomSearchBarActvity extends AppCompatActivity {

    private MySearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_search_bar_actvity);
        searchView = (MySearchView)findViewById(R.id.sv);
        searchView.setController(new Controller());

    }

    public void start(View v){
        searchView.startAnimation();
    }
    
    public void reset(View v){
        searchView.resetAnimation();
    }
}
