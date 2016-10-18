package com.example.qiyue.materialdesignadvance.demo.hotfix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.qiyue.materialdesignadvance.R;

/**
 * 1.第一步  根build.gradle   classpath 'com.android.tools.build:gradle:1.2.3'
             classpath 'cn.jiajixin.nuwa:gradle:1.2.2'
   2. app 下build.gradle
         apply plugin: 'cn.jiajixin.nuwa'

         buildTypes {
             release {
             minifyEnabled true
             proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
             }
             debug {
             minifyEnabled true
             proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
             }
         }

         compile 'cn.jiajixin.nuwa:nuwa:1.0.0'
   3.   自定义application
         @Override
         protected void attachBaseContext(Context base) {
         super.attachBaseContext(base);
         Nuwa.init(this);
         Nuwa.loadPatch(this, Environment.getExternalStorageDirectory().getAbsolutePath().concat("/patch.jar"));
         }
    4. AndroidManifest.xml
         <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
         <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    5..运行并安装apk，会在app/build/outputs/目录下自动生成一个叫nuwa的目录：
         拷贝此目录到一个指定的目录，比如：
         C:/Users/Documents/nuwa
    6.. 在修复完bug的code base下执行如下命令：
         gradlew clean nuwaDebugPatch -P NuwaDir=F:\hot\nuwa
         然后在app/build/outputs/nuwa/debug/目录下会自动生成patch.jar文件：
    7.adb push app/build/outputs/nuwa/debug/patch.jar /sdcard/
    8.退出app,,重启app,完成修复

 */
public class HotFixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_fix);
        TextView textView = (TextView)findViewById(R.id.tv_textView);
        textView.setText("rightright");
    }
}
