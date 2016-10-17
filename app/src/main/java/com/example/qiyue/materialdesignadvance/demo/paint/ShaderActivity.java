package com.example.qiyue.materialdesignadvance.demo.paint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.qiyue.materialdesignadvance.R;

public class ShaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shader);
        TextView tvDescrible = (TextView)findViewById(R.id.tv_describle);
        TextView tvDescrible2 = (TextView)findViewById(R.id.tv_describle_2);
        tvDescrible.setText("自定义View 通过paint BitmapShader渲染实现，" +
                "并通过里面的Matrix实现放大居中显示一个圆形的ImageView");
        tvDescrible2.setText("自定义ImageView 实现圆形显示，这样就可以兼容第三方图片显示框架");
    }
}
