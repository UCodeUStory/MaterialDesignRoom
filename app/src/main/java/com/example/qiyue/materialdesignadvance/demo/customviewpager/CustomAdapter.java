package com.example.qiyue.materialdesignadvance.demo.customviewpager;

import android.graphics.LinearGradient;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiyue on 2016/10/24.
 */
public abstract class CustomAdapter<T extends LinearLayout> {

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    private List<T> datas = new ArrayList<>();

    public int size(){
        return datas.size();
    }

}
