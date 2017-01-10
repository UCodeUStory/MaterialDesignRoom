package com.example.qiyue.materialdesignadvance.demo2.tech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.qiyue.materialdesignadvance.R;
import com.example.qiyue.materialdesignadvance.demo.collapsingtoolbarlayout_two.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiyue
 */
abstract class TagAdapter<T> extends BaseAdapter {

    private final Context mContext;
    private final List<T> mDataList;

    public TagAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
    }

    public List<T> getDataList() {
        return mDataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView== null){
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.tech_tag_item, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvTag = (TextView) convertView.findViewById(R.id.tv_tag);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        getTagView(getDataList().get(position),viewHolder);

        return convertView;
    }

    public abstract void getTagView(T bean,ViewHolder viewHolder);

    public void onlyAddAll(List<T> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<T> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }

    class ViewHolder{
        public TextView tvTag;
    }
}
