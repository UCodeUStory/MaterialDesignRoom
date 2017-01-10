package com.example.qiyue.materialdesignadvance.demo2.diffutil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by qiyue on 2016/12/26.
 */

public class DiffCallBack extends DiffUtil.Callback {

    private List<Person> mOldDatas, mNewDatas;//看名字


    public DiffCallBack(List<Person> mOldDatas, List<Person> mNewDatas){
         this.mOldDatas = mOldDatas;
         this.mNewDatas = mNewDatas;
    }
    @Override
    public int getOldListSize() {
        return mOldDatas != null ? mOldDatas.size() : 0;//获取就数据集大小
    }

    @Override
    public int getNewListSize() {
        return mNewDatas != null ? mNewDatas.size() : 0;//获取新数据集大小
    }

    /**
     * 就是用来判断是item是否已经存在，如果存在只进行修改，继续调用areContentsTheSame是否需要修改，
     * 如果不存在就进行正常的插入操作（这里有怎么判断呢？就是根据数据的唯一标识，例如我们person中通过id来确定他是唯一的，
     * 开发过程中只需要根据不能修改的数据作为判别就可以）
     * @param oldItemPosition
     * @param newItemPosition
     */

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        //
        return mOldDatas.get(oldItemPosition).getId().equals(mNewDatas.get(newItemPosition).getId());
    }

    /**
     * 当areItemsTheSame判断是返回true时，才调用此方法（也就是item已经存在只不过再进一步判断是否要更行）
     * @param oldItemPosition
     * @param newItemPosition
     * @return
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Person beanOld = mOldDatas.get(oldItemPosition);
        Person beanNew = mNewDatas.get(newItemPosition);
        if (!beanOld.getName().equals(beanNew.getName())) {
            return false;//如果有内容不同，就返回false
        }
        if (!beanOld.getAge().equals(beanNew.getAge())) {
            return false;//如果有内容不同，就返回false
        }
        return true;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //实现这个方法 就能成为文艺青年中的文艺青年
        // 定向刷新中的部分更新
        // 效率最高
        //只是没有了ItemChange的白光一闪动画，（反正我也觉得不太重要）
        Person oldBean = mOldDatas.get(oldItemPosition);
        Person newBean = mNewDatas.get(newItemPosition);

        //这里就不用比较核心字段了,一定相等
        Bundle payload = new Bundle();
        if (!oldBean.getAge().equals(newBean.getAge())) {
            payload.putString("KEY_AGE", newBean.getAge());
        }
        if (!oldBean.getName().equals(newBean.getName())) {
            payload.putString("KEY_NAME", newBean.getName());
        }

        if (payload.size() == 0)//如果没有变化 就传空
            return null;
        return payload;//
    }
}
