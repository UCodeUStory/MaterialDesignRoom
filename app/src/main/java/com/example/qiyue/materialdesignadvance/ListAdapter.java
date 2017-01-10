package com.example.qiyue.materialdesignadvance;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/8 0008.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.RVHolderView> {

    private List<String> datas = new ArrayList<>();

    public int getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
    }

    public int getAnimationType() {
        return animationType;
    }

    public void setAnimationType(int animationType) {
        this.animationType = animationType;
    }

    private int animationType = 0;
    private int layoutType = 0;
    public ListAdapter(List<String> datas){
        this.datas = datas;


    }

    @Override
    public RVHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutType==1){
            return new RVHolderView(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_two,parent,false));
        }else {
            return new RVHolderView(View.inflate(parent.getContext(), R.layout.item_list, null));
        }
    }

    @Override
    public void onBindViewHolder(RVHolderView holder, int position) {
     /*   if(position%2==0){
            holder.tv_label.setTextColor(0xFFff8706);
        }else{
            holder.tv_label.setTextColor(0xFF0d18e9);
        }*/
          holder.tv_label.setText(datas.get(position));
          holder.tv_count.setText(""+(position+1));
          if(animationType==1) {
              holder.startAnimation();
          }else{
              holder.startAnimation2();
          }

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
        public void onClick(final View v) {
            if (onItemClick!=null){
                onItemClick.onClick(v,getPosition());
            }
        }

        public void startAnimation(){
            ObjectAnimator animation = ObjectAnimator.ofFloat(this.itemView,"scaleX",0.5f,1);
            animation.setDuration(150);
            animation.start();
            ObjectAnimator animation2 = ObjectAnimator.ofFloat(this.itemView,"scaleY",0.5f,1);
            animation2.setDuration(150);
            animation2.start();

        }
        public void startAnimation2(){
            ObjectAnimator animation = ObjectAnimator.ofFloat(this.itemView,"translationX",1000,0);
            animation.setDuration(150);
            animation.start();
            ObjectAnimator animation2 = ObjectAnimator.ofFloat(this.tv_count,"translationX",1000,0);
            animation2.setDuration(350);
            animation2.start();
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
