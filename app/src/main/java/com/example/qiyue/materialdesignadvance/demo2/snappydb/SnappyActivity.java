package com.example.qiyue.materialdesignadvance.demo2.snappydb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.qiyue.materialdesignadvance.R;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

/**
 * 比sp方便查询和新增，同时可以保存对象，数组，是一个noSql数据库，比SqLite轻量级,便于维护
 *
 * https://github.com/nhachicha/SnappyDB
 */
public class SnappyActivity extends AppCompatActivity {

    private DB snappyDB;
    private TextView personField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snappy);

        Person p1 = new Person("qiyue",25);
        try {
            snappyDB = DBFactory.open(this);
            snappyDB.put("Person:1",p1);


            snappyDB.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
//
        personField = (TextView)findViewById(R.id.tv_person_field);
        findViewById(R.id.tv_findPerson).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    snappyDB = DBFactory.open(SnappyActivity.this);
                    Person findP1 = snappyDB.getObject("Person:1",Person.class);
                    snappyDB.close();
                    personField.setText(findP1.toString());
                } catch (SnappydbException e) {
                    e.printStackTrace();
                }
            }
        });





    }

}
