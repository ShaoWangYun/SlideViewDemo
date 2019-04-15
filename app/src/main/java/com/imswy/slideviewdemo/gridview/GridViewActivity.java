package com.imswy.slideviewdemo.gridview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.imswy.slideviewdemo.R;
import com.imswy.slideviewdemo.Utils;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {

    private GridView grid_view;
    private GridAdapter gridAdapter;
    private List<GridDataBean> gridDataBeans = new ArrayList<>();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        initView();
    }

    private void initView () {
        grid_view = findViewById(R.id.grid_view);
        makeData();
        refreshListView();
    }

    //循环生成一组假数据，作为RecyclerView的子项数据源,后续将会对recyclerDataBeans中的这20条数据进行操作
    private void makeData () {
        for (int i = 0; i < 20; i++) {
            GridDataBean gridDataBean = new GridDataBean();
            gridDataBean.setIndex(String.valueOf(i));
            gridDataBeans.add(gridDataBean);
        }
    }

    private void refreshListView () {

        gridAdapter = new GridAdapter(GridViewActivity.this, gridDataBeans);
        grid_view.setAdapter(gridAdapter);

        grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                Utils.showLog("单击事件", "click!!!");
                Utils.showToast(GridViewActivity.this, "您点击了的子项的序号为：" + gridAdapter.getItem(position).getIndex(), 0);
            }
        });

        grid_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick (AdapterView<?> parent, View view, final int position, long id) {
                Utils.showLog("长按事件", "click!!!");
                AlertDialog.Builder builder = new AlertDialog.Builder(GridViewActivity.this);
                builder.setTitle("警告");
                builder.setCancelable(false);
                builder.setMessage("你想要删除序号为 " + gridAdapter.getItem(position).getIndex() + " 的数据么？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick (DialogInterface dialog, int which) {
                        try {
                            //获取position（便于局部刷新recyclerview）
                            int deleteposition = getPositionFromList(gridAdapter.getItem(position).getIndex());
                            //刷新UI
                            gridDataBeans.remove(deleteposition);
                            refreshListView();
                            gridAdapter.notifyDataSetChanged();
                            //提示用户
                            Utils.showToast(GridViewActivity.this, "删除成功", 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Utils.showToast(GridViewActivity.this, "删除失败", 1);
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick (DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                return true;
            }
        });
    }

    //根据studentID获取该条数据在list中的下标，便于对recyclerview进行局部刷新
    private int getPositionFromList (String index) {
        List<GridDataBean> list = gridDataBeans;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIndex().equals(index)) {
                return i;
            }
        }
        return -1;
    }

}
