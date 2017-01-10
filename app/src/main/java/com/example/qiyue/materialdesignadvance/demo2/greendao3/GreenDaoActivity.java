package com.example.qiyue.materialdesignadvance.demo2.greendao3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.qiyue.materialdesignadvance.MApplication;
import com.example.qiyue.materialdesignadvance.R;
import com.example.qiyue.materialdesignadvance.demo2.greendao3.dao.DaoSession;
import com.example.qiyue.materialdesignadvance.demo2.greendao3.dao.UserDao;

import org.androidannotations.annotations.App;

import java.util.ArrayList;
import java.util.List;

/**
 * http://blog.csdn.net/njweiyukun/article/details/51893092
 */
public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private UserListAdapter userListAdapter;
    private DaoSession daoSession;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greendao);
        /**
         * DaoSession可操作以让我们使用一些Entity的基本操作和获取Dao类，DaoSession可以创建多个，每一个都是属于同一个数据库连接的。
         */
        this.daoSession = ((MApplication)getApplication()).getDaoMaster().newSession();
        this.userDao = daoSession.getUserDao();

        initView();
        /**
         * 初始化10条数据
         */
        initData();




    }

    private void initData() {
        List<User> userList = queryAll();

        if (userList!=null && userList.size()==0) {
            for (int i = 0; i < 10; i++) {
                Log.i("ustory","insert-i="+i);
                User user = new User(null, "qiyue" + i, 20 + i);
                userDao.insert(user);
            }
        }

        List<User> users = queryAll();
        userListAdapter.setDatas(users);
        userListAdapter.notifyDataSetChanged();

        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_delete_qiyue5).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);

    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<User> userlist = new ArrayList<>();
        userListAdapter = new UserListAdapter(userlist);
        mRecyclerView.setAdapter(userListAdapter);
    }


    private List<User> queryAll(){

        List<User> userList = userDao.queryBuilder()
                .where(UserDao.Properties.Id.notEq(999))
                .orderAsc(UserDao.Properties.Id)
                .limit(100)
                .build().list();
        return userList;
    }

    private boolean insertOne(){
        List<User> userList = userDao.queryBuilder()
                .where(UserDao.Properties.Id.notEq(999))
                .orderAsc(UserDao.Properties.Id)
                .limit(100)
                .build().list();
        User user = new User(null, "qiyue" + (userList.size()+1), 20 + userList.size()+1);
        userDao.insert(user);
        return true;
    }

    private boolean updateOne(){
        //   User findUser = userDao.queryBuilder().where(UserDao.Properties.Name.eq("wyk")).build().unique();
        List<User> queryList =  userDao.queryBuilder().where(UserDao.Properties.Name.eq("qiyue1")).build().list();
        for (User findUser : queryList) {
            if(findUser != null) {
                findUser.setName("new");
                userDao.update(findUser);
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "用户不存在", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    private boolean deleteOne(){
        User findUser2 = userDao.queryBuilder().where(UserDao.Properties.Name.eq("qiyue2")).build().unique();
        if(findUser2 != null){
            userDao.deleteByKey(findUser2.getId());
        }
       return true;
    }

    @Override
    public void onClick(View v) {
        List<User> users = null;
        switch (v.getId()){
            case R.id.btn_insert:
                insertOne();
                users = queryAll();
                userListAdapter.setDatas(users);
                userListAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_update:
                updateOne();
                users = queryAll();
                userListAdapter.setDatas(users);
                userListAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_delete_qiyue5:
                deleteOne();
                users = queryAll();
                userListAdapter.setDatas(users);
                userListAdapter.notifyDataSetChanged();
                break;
        }
    }
}





