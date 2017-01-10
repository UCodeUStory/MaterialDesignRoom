package com.example.qiyue.materialdesignadvance.demo2.tech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qiyue.materialdesignadvance.R;

/**
 * Created by qiyue on 2016/10/31.
 */
public class SimpleTagAdapter extends TagAdapter<String> {

    public SimpleTagAdapter(Context context) {
        super(context);
    }

    @Override
    public void getTagView(String bean, ViewHolder viewHolder) {
        viewHolder.tvTag.setText(bean);
    }


}
