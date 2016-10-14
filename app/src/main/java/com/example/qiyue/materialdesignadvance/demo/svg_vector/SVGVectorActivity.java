package com.example.qiyue.materialdesignadvance.demo.svg_vector;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.qiyue.materialdesignadvance.R;

public class SVGVectorActivity extends AppCompatActivity {

    private ImageView ivOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svgvector);
        ivOne = (ImageView) findViewById(R.id.iv_svg_one);
        ivOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView)v;
                Drawable drawable = imageView.getDrawable();
                if (drawable instanceof Animatable){
                    ((Animatable) drawable).start();
                }
            }
        });

    }

}
