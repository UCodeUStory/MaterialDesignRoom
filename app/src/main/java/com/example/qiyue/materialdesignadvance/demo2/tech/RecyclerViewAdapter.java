package com.example.qiyue.materialdesignadvance.demo2.tech;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qiyue.materialdesignadvance.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiyue on 2016/10/31.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<TagResult> datas = new ArrayList<TagResult>();

    private Context mContext;

    public RecyclerViewAdapter(Context context,List<TagResult>data){
        this.datas = data;
        this.mContext = context;
    }
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tech_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
         holder.tvTag.setText(datas.get(position).type);
         SimpleTagAdapter adapter = new SimpleTagAdapter(mContext);
         adapter.clearAndAddAll(datas.get(position).datas);
         holder.flowTagLayout.setAdapter(adapter);
         adapter.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public FlowTagLayout flowTagLayout;
        public TextView tvTag;
        public ViewHolder(View itemView) {
            super(itemView);
            flowTagLayout = (FlowTagLayout)itemView.findViewById(R.id.flowTagLayout);
            tvTag = (TextView)itemView.findViewById(R.id.tv_tag);
        }
    }
}
