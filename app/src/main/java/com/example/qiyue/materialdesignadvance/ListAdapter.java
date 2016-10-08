package com.example.qiyue.materialdesignadvance;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/8 0008.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.RVHolderView> {

    private List<String> datas = new ArrayList<>();

    public ListAdapter(List<String> datas){
        this.datas = datas;
    }

    @Override
    public RVHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RVHolderView(View.inflate(parent.getContext(),R.layout.item_list,null));
    }

    @Override
    public void onBindViewHolder(RVHolderView holder, int position) {
        if(position%2==0){
            holder.tv_label.setTextColor(0xFF21ac05);
        }else{
            holder.tv_label.setTextColor(0xFF0d18e9);
        }
          holder.tv_label.setText(datas.get(position));
          holder.tv_count.setText(""+(position+1));

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class RVHolderView extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tv_label;
        public TextView tv_count;
        public RVHolderView(View itemView) {
            super(itemView);
            tv_label = (TextView)itemView.findViewById(R.id.tv_lable);
            tv_count = (TextView)itemView.findViewById(R.id.tv_count);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClick!=null){
                onItemClick.onClick(v,getPosition());
            }
        }
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    private OnItemClick onItemClick;
    public interface OnItemClick{
        public void onClick(View view,int position);
    }
}
