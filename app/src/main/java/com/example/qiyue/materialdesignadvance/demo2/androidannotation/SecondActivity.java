package com.example.qiyue.materialdesignadvance.demo2.androidannotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.qiyue.materialdesignadvance.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_second)
public class SecondActivity extends AppCompatActivity {

    @Extra(AnnotationActivity.KEY_NAME)
    String mName;
    @Extra(AnnotationActivity.KEY_AGE)
    String mAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @ViewById(R.id.tv_extra)
    TextView mTextView;


    @AfterViews
    public void setTextView() {
        mTextView.setText(mName+":"+mAge);
    }
}
