package com.example.qiyue.materialdesignadvance.demo2.circlepassword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.qiyue.materialdesignadvance.MainActivity;
import com.example.qiyue.materialdesignadvance.R;

public class CirclePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_password);
        CirclePasswordView circlePasswordView
                = (CirclePasswordView) findViewById(R.id.circlePassword);
        circlePasswordView.setListener(new CirclePasswordView.PassWordListener() {
            @Override
            public void rightPassword() {
                Toast.makeText(CirclePasswordActivity.this,"密码正确",Toast.LENGTH_SHORT);
                startActivity(new Intent(CirclePasswordActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}
