package com.example.qiyue.materialdesignadvance.demo.scroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.qiyue.materialdesignadvance.R;

public class CustomSlideMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_slide_menu);
        findViewById(R.id.include).findViewById(R.id.tv_gexingzhuangban).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomSlideMenuActivity.this, "点击Menu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
