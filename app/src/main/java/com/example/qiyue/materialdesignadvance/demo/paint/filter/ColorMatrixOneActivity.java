package com.example.qiyue.materialdesignadvance.demo.paint.filter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.qiyue.materialdesignadvance.R;

public class ColorMatrixOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int flag = getIntent().getIntExtra("flag",0);
        if(flag == 0){
            setContentView(new FilterView(this,0));
        }else if(flag == 1){
            setContentView(new FilterView(this,1));
        }else if(flag == 2){
            setContentView(R.layout.activity_color_matrix_one);
        }else if(flag == 3){
            setContentView(new FilterView(this,3));
        }else if(flag == 4){
            setContentView(new FilterView(this,4));
        }else if(flag == 5){
            setContentView(new FilterView(this,5));
        }else if(flag == 6){
            setContentView(new FilterView(this,6));
        }else if(flag == 7){
            setContentView(new FilterView(this,7));
        }else if(flag == 8){
            setContentView(new FilterView(this,8));
        }else if(flag == 9){
            setContentView(new FilterView(this,9));
        }else if(flag == 10){
            setContentView(new FilterView(this,10));
        }


    }
}
