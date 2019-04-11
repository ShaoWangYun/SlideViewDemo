package com.imswy.slideviewdemo.recyclerview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.imswy.slideviewdemo.R;
import com.imswy.slideviewdemo.Utils;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recycler_view;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<RecyclerDataBean> recyclerDataBeans = new ArrayList<>();
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initView();
    }

    private void initView(){
        recycler_view = findViewById(R.id.recycler_view);
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recycler_view.setHasFixedSize(true);
        makeData();
        refreshRecyclerView();
    }

    //循环生成一组假数据，作为RecyclerView的子项数据源,后续将会对recyclerDataBeans中的这20条数据进行操作
    private void makeData(){
        for(int i=0; i<20; i++){
            RecyclerDataBean recyclerDataBean = new RecyclerDataBean();
            recyclerDataBean.setIndex(String.valueOf(i));
            recyclerDataBeans.add(recyclerDataBean);
        }
        recyclerAdapter = new RecyclerAdapter(recyclerDataBeans);
        recycler_view.setAdapter(recyclerAdapter);
    }

    //刷新recyclerview
    private void refreshRecyclerView() {
        recyclerAdapter = new RecyclerAdapter(recyclerDataBeans);
        recycler_view.setAdapter(recyclerAdapter);
        //定义recyclerview的子项点击事件
        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Utils.showLog("单击事件","click!!!");
                Utils.showToast(RecyclerViewActivity.this,"您点击了的子项的序号为："+recyclerAdapter.getItem(position).getIndex(),0);
            }
        });

        //定义recyclerview的子项长按时间
        recyclerAdapter.setOnItemLongClickListener(new RecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, final int position) {
                Utils.showLog("长按事件","click!!!");
                AlertDialog.Builder builder = new AlertDialog.Builder(RecyclerViewActivity.this);
                builder.setTitle("警告");
                builder.setCancelable(false);
                builder.setMessage("你想要删除序号为 " + recyclerAdapter.getItem(position).getIndex() + " 的数据么？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            //获取position（便于局部刷新recyclerview）
                            int deleteposition = getPositionFromList(recyclerAdapter.getItem(position).getIndex());
                            //刷新UI
                            recyclerAdapter.notifyItemRemoved(deleteposition);
                            recyclerDataBeans.remove(deleteposition);
                            refreshRecyclerView();
                            //提示用户
                            Utils.showToast(RecyclerViewActivity.this, "删除成功", 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Utils.showToast(RecyclerViewActivity.this, "删除失败", 1);
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }

    //为了避免数据显示重复，每次初始化时都对RecyclerView进行一次初始化
    private void clearRecyclerView(){
        recyclerDataBeans = new ArrayList<>();
        recyclerAdapter = new RecyclerAdapter(recyclerDataBeans);
        recycler_view.setAdapter(recyclerAdapter);
    }

    //根据studentID获取该条数据在list中的下标，便于对recyclerview进行局部刷新
    private int getPositionFromList(String index){
        List<RecyclerDataBean> list = recyclerDataBeans;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getIndex().equals(index)){
                return i;
            }
        }
        return -1;
    }
}
