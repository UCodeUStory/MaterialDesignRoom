package com.example.qiyue.materialdesignadvance.demo2.greendao3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qiyue.materialdesignadvance.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ListViewHolder> {

    public List<User> getDatas() {
        return datas;
    }

    public void setDatas(List<User> datas) {
        this.datas = datas;
    }

    private List<User> datas = new ArrayList<>();

    public UserListAdapter(List<User> datas){
        this.datas = datas;
    }


    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.tv_name.setText(datas.get(position).getName());
        holder.tv_age.setText(datas.get(position).getAge()+"");
        //holder.tv_createTime.setText(datas.get(position).createTime);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name;
        TextView tv_age;
        TextView tv_createTime;
        public ListViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView)itemView.findViewById(R.id.tv_name);
            tv_age = (TextView)itemView.findViewById(R.id.tv_age);
            tv_createTime = (TextView)itemView.findViewById(R.id.tv_createTime);
        }
    }
}
