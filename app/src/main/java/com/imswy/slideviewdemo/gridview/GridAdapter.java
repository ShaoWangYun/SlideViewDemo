package com.imswy.slideviewdemo.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.imswy.slideviewdemo.R;
import com.imswy.slideviewdemo.listview.ListDataBean;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends BaseAdapter {

    private Context context = null;
    private List<GridDataBean> list = new ArrayList<>();

    public GridAdapter (Context context, List<GridDataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount () {
        return list.size();
    }

    @Override
    public GridDataBean getItem (int position) {
        return list.get(position);
    }

    @Override
    public long getItemId (int position) {
        return position;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.grid_item, null, true);
            mHolder.Index = (TextView) convertView.findViewById(R.id.text_grid);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        String index = list.get(position).getIndex();
        mHolder.Index.setText(index);
        return convertView;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public class ViewHolder {
        private TextView Index;
    }

}
