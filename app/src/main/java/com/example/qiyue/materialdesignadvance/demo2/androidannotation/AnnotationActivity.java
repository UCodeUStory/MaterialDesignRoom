package com.example.qiyue.materialdesignadvance.demo2.androidannotation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.qiyue.materialdesignadvance.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 *
 * 最需要注意的一点就是：使用AndroidAnnotations千万要记得，编译的时候会生成一个子类，
 * 这个类的名称就是在原来的类之后加了一个下划线“_”，比如这个例子产生的子类名称为“MainActivity_”，
 * 这就需要你在注册这个Activity的时候，在AndroidManifest.xml中将 AnnotationActivity 改为 MainActivity_ ，
 * 使用的时候也是使用MainActivity_来表示此类，跟上面的SecondActivity_类似。
 另外上面的MainActivity中跳转的时候还传递了参数，可以用@Extra标签来获取传递的值。
 */
@EActivity(R.layout.activity_annotation)
public class AnnotationActivity extends AppCompatActivity {
    public static final String KEY_NAME = "name";
    public static final String KEY_AGE = "age";

    @ViewById(R.id.tv_firstTextView)
    TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @AfterViews
    public void setTextView() {
        mTextView.setText("测试AndroidAnnotation使用");
    }

    @Click(R.id.btn_start_second)
    public void startActivity() {
        Intent intent = new Intent(this, SecondActivity_.class);
        intent.putExtra(KEY_NAME, "Jackie");
        intent.putExtra(KEY_AGE, "18");
        startActivity(intent);
    }
}
