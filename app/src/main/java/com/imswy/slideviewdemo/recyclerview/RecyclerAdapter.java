package com.imswy.slideviewdemo.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.imswy.slideviewdemo.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public List<RecyclerDataBean> recyclerDataBeans = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener (OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick (View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick (View view, int position);
    }

    public RecyclerAdapter (List<RecyclerDataBean> recyclerDataBeans) {
        this.recyclerDataBeans = recyclerDataBeans;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder (ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder (final ViewHolder viewHolder, final int position) {
        viewHolder.Index.setText("序号：" + recyclerDataBeans.get(position).getIndex());

        if (onItemClickListener != null) {
            viewHolder.Recycler_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    onItemClickListener.onItemClick(viewHolder.itemView, position);
                }
            });
            viewHolder.Recycler_item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick (View v) {
                    onItemLongClickListener.onItemLongClick(viewHolder.itemView, position);
                    return false;
                }
            });
        }

    }

    //获取数据的数量
    @Override
    public int getItemCount () {
        return recyclerDataBeans.size();
    }

    public RecyclerDataBean getItem (int index) {
        return recyclerDataBeans.get(index);
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Index;
        private RelativeLayout Recycler_item;

        public ViewHolder (View view) {
            super(view);
            Index = view.findViewById(R.id.text_recycler);
            Recycler_item = view.findViewById(R.id.recycler_item);
        }
    }

}
