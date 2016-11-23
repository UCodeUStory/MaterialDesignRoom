package com.example.qiyue.materialdesignadvance.demo2.texture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.qiyue.materialdesignadvance.R;

public class TextureActivity extends AppCompatActivity {

    private CustomTextureView first;
    private CustomTextureView second;
    private CustomTextureView third;
    private CustomTextureView forth;
    private CustomTextureView fifth;
    private CustomTextureView sixth;
    private CustomTextureView seventh;
    private CustomTextureView nigth;
    private CustomTextureView nineth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texture);
        first = (CustomTextureView)findViewById(R.id.first);
        first.startTime(11,30,10);
        second = (CustomTextureView)findViewById(R.id.second);
        second.startTime(10,30,10);
        third = (CustomTextureView)findViewById(R.id.third);
        third.startTime(9,30,10);
        forth = (CustomTextureView)findViewById(R.id.forth);
        forth.startTime(8,30,10);
        fifth = (CustomTextureView)findViewById(R.id.fifth);
        fifth.startTime(7,30,10);
        sixth = (CustomTextureView)findViewById(R.id.sixth);
        sixth.startTime(6,30,10);
        seventh = (CustomTextureView)findViewById(R.id.seventh);
        seventh.startTime(5,30,10);
        nigth = (CustomTextureView)findViewById(R.id.nigth);
        nigth.startTime(4,30,10);
        nineth = (CustomTextureView)findViewById(R.id.nineth);
        nineth.startTime(0,1,10);

    }
}
