package com.example.qiyue.materialdesignadvance.demo2.json;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.qiyue.materialdesignadvance.R;
import com.google.gson.Gson;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonActivity extends AppCompatActivity {

    private int count = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        final TextView gsonSpeed = (TextView)findViewById(R.id.tv_gson_speed) ;
        findViewById(R.id.btn_test_gson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long startTime = System.currentTimeMillis();
                /**
                 * 模拟3.9Mjson,平均时间3700ms
                 */
                for (int i=0;i<count;i++) {
                    Gson gson = new Gson();
                    JsonBean jsonBean = gson.fromJson(json, JsonBean.class);
                }
                long endTime = System.currentTimeMillis();
                long userTime = endTime - startTime;
                gsonSpeed.setText(userTime+"");
            }
        });

        final TextView fastJsonSpeed = (TextView)findViewById(R.id.tv_fastjson_speed);
        findViewById(R.id.btn_test_fastjson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startTime = System.currentTimeMillis();
                /**
                 * 模拟3.9Mjson,平均时间200ms以下
                 */
                for (int i=0;i<count;i++) {
                    JSON.parseObject(json,JsonBean.class);
                }
                long endTime = System.currentTimeMillis();
                long userTime = endTime - startTime;
                fastJsonSpeed.setText(userTime+"");



            }
        });

        final TextView jackson_speed = (TextView)findViewById(R.id.tv_jackson_speed);
        findViewById(R.id.btn_test_jackson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startTime = System.currentTimeMillis();
                ObjectMapper mapper = new ObjectMapper();
                /**
                 * 模拟3.9Mjson,平均时间300ms以下
                 */
                for (int i=0;i<count;i++) {
                    try {
                        mapper.readValue(json,JsonBean.class);
                    } catch (IOException e) {
                        showToast("解析异常");
                        e.printStackTrace();
                    }
                }
                long endTime = System.currentTimeMillis();
                long userTime = endTime - startTime;
                jackson_speed.setText(userTime+"");
            }
        });
    }

    private void showToast(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }


    public String json = "{\n" +
            "\"status\": \"2000\",\n" +
            "\"msg\": \"Successful!\",\n" +
            "\"data\": [{\n" +
            "\"details\": [{\n" +
            "\"distance\": 2847,\n" +
            "\"nextLat\": 39.994076,\n" +
            "\"nextLong\": 116.47764,\n" +
            "\"nexti\": \"MeloDev\",\n" +
            "\"status\": 4\n" +
            "}],\n" +
            "\"distance\": 2847,\n" +
            "\"imageUrl\": \"\",\n" +
            "\"overview\": \"长期原创Android博客\",\n" +
            "\"source\": \"http://www.jianshu.com/users/f5909165c1e8/latest_articles\",\n" +
            "\"status\": \"SUCCESSFUL\"\n" +
            "}, {\n" +
            "\"details\": [{\n" +
            "\"distance\": 2769,\n" +
            "\"nextLat\": 39.97691,\n" +
            "\"nextLong\": 116.46019,\n" +
            "\"nexti\": \"MeloDev\",\n" +
            "\"status\": 4\n" +
            "}],\n" +
            "\"distance\": 2769,\n" +
            "\"imageUrl\": \"\",\n" +
            "\"overview\": \"喜欢请加关注\",\n" +
            "\"source\": \"http://www.jianshu.com/users/f5909165c1e8/latest_articles\",\n" +
            "\"status\": \"SUCCESSFUL\"\n" +
            "}]\n" +
            "}";
}
