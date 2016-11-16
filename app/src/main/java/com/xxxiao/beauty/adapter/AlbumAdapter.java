package com.xxxiao.beauty.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xxxiao.beauty.App;
import com.xxxiao.beauty.R;
import com.xxxiao.beauty.model.Album;
import com.xxxiao.beauty.util.ImageUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/17.
 */

public class AlbumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Album> mList;
    private LayoutInflater mInflater;

    private AdapterInterface mInterface;

    public AlbumAdapter(ArrayList<Album> list) {
        mList = list;
        mInflater = LayoutInflater.from(App.getContext());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(mInflater.inflate(R.layout.item_album, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            MyHolder h = (MyHolder) holder;
            h.bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class MyHolder extends RecyclerView.ViewHolder {

        ImageView mIv;

        MyHolder(View itemView) {
            super(itemView);
            mIv = (ImageView) itemView.findViewById(R.id.iv);
        }

        private void bind(final int position) {
            final Album item = mList.get(position);
            ImageUtils.load(item.cover, mIv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mInterface != null) {
                        mInterface.onItemClick(position, item);
                    }
                }
            });
        }
    }

    public void setInterface(AdapterInterface i) {
        mInterface = i;
    }

    public interface AdapterInterface {
        void onItemClick(int position, Album item);
    }
}
