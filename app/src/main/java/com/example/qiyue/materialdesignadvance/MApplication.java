package com.example.qiyue.materialdesignadvance;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import cn.jiajixin.nuwa.Nuwa;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Nuwa.init(this);
        Nuwa.loadPatch(this, Environment.getExternalStorageDirectory().getAbsolutePath().concat("/patch.jar"));
    }
}
