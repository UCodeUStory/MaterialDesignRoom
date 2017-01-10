package com.example.qiyue.materialdesignadvance;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.example.qiyue.materialdesignadvance.demo2.greendao3.dao.DaoMaster;


/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class MApplication extends Application {


    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    private DaoMaster daoMaster;

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * note-db是数据哭名
         */
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "note-db", null);
        /**
         * 这个Master代码一个数据库连接
         */
        this.daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        Nuwa.init(this);
//        Nuwa.loadPatch(this, Environment.getExternalStorageDirectory().getAbsolutePath().concat("/patch.jar"));
    }




}
