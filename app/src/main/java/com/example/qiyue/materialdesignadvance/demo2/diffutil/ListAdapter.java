package com.example.qiyue.materialdesignadvance.demo2.diffutil;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qiyue.materialdesignadvance.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiyue on 2016/12/26.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.VH> {

    public List<Person> getDatas() {
        return datas;
    }

    public void setDatas(List<Person> datas) {
        this.datas = datas;
    }

    private List<Person> datas = new ArrayList<>();

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Log.i("qiyue","onBindtone");
        holder.tvId.setText(datas.get(position).getId());
        holder.tvName.setText(datas.get(position).getName());
        holder.tvAge.setText(datas.get(position).getAge());

    }

    @Override
    public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
        Log.i("test","onBindTwo");
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            //文艺青年中的文青
            Bundle payload = (Bundle) payloads.get(0);//取出我们在getChangePayload（）方法返回的bundle
            Person bean = datas.get(position);//取出新数据源，（可以不用）
            Log.i("test","age"+bean.getAge());
            for (String key : payload.keySet()) {
                switch (key) {
                    case "KEY_AGE":
                        //这里可以用payload里的数据，不过data也是新的 也可以用
                        holder.tvAge.setText(payload.getString(key));
                        break;
                    case "KEY_NAME":
                        holder.tvName.setText(payload.getString(key));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class VH extends RecyclerView.ViewHolder{
        TextView tvId;
        TextView tvName;
        TextView tvAge;
        public VH(View itemView) {
            super(itemView);
            tvId = (TextView)itemView.findViewById(R.id.tv_id);
            tvName = (TextView)itemView.findViewById(R.id.tv_name);
            tvAge = (TextView)itemView.findViewById(R.id.tv_age);
        }
    }
}

