package com.xxxiao.beauty.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/16.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<T> mList;
    private LayoutInflater mInflater;

    private int mLayoutResID;

    public BaseRecyclerAdapter(Context context, int resid, ArrayList<T> list) {
        mLayoutResID = resid;
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(mInflater.inflate(mLayoutResID, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class BaseViewHolder extends RecyclerView.ViewHolder {

        BaseViewHolder(View itemView) {
            super(itemView);
            initItemView(itemView);
        }

        private void bindItemView(final int position) {
            final T item = mList.get(position);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(position, item);
                }
            });

        }
    }

    public abstract void initItemView(View v);

    public abstract void bindItemView(View v, int position);

    public abstract void onItemClick(int position, T item);


}
