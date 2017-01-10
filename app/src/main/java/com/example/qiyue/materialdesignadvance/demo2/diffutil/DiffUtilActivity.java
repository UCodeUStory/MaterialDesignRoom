package com.example.qiyue.materialdesignadvance.demo2.diffutil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.qiyue.materialdesignadvance.R;

import java.util.ArrayList;
import java.util.List;

public class DiffUtilActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private List<Person> persons;

    private List<Person> newPersons;

    private ListAdapter adapter;

    private static final int H_CODE_UPDATE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diffutil_activity);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.adapter = new ListAdapter();
        persons  = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Person person = new Person(""+i,"John"+i, String.valueOf(25+i));
            persons.add(person);
        }
        adapter.setDatas(persons);
        mRecyclerView.setAdapter(adapter);


        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.i("test","onclick");
                    initNewData();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case H_CODE_UPDATE:

                    //取出Result
                    DiffUtil.DiffResult diffResult = (DiffUtil.DiffResult) msg.obj;
                    //利用DiffUtil.DiffResult对象的dispatchUpdatesTo（）方法，传入RecyclerView的Adapter，轻松成为文艺青年
                    diffResult.dispatchUpdatesTo(adapter);
                    Log.i("test","update");
                    //别忘了将新数据给Adapter
                    persons = newPersons;
                    adapter.setDatas(persons);
                    break;
            }
        }
    };

    private void initNewData() throws CloneNotSupportedException {
        newPersons = new ArrayList<>();
        for (Person person : persons) {
            newPersons.add(person.clone());
        }
        newPersons.get(0).setAge("Android+");
        newPersons.get(0).setName("jhaha");//模拟修改数据
        Person testBean = newPersons.get(1);//模拟数据位移
        newPersons.remove(testBean);
        newPersons.add(testBean);

        newPersons.add(new Person("32","newName","100"));

        final List<Person> oldDatas = adapter.getDatas();

        /**
         * 当数据量比较大时还是建议开启一个线程
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                //放在子线程中计算DiffResult
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack(oldDatas, newPersons), true);
                Message message = mHandler.obtainMessage(H_CODE_UPDATE);
                message.obj = diffResult;//obj存放DiffResult
                message.sendToTarget();
            }
        }).start();




    }
}
