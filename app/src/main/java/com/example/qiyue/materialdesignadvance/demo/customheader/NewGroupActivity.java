package com.example.qiyue.materialdesignadvance.demo.customheader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.qiyue.materialdesignadvance.R;

public class NewGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);
        HeaderViewGroup springview = (HeaderViewGroup)findViewById(R.id.springview);
        springview.setHeader();
    }
}
