package com.imswy.slideviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.imswy.slideviewdemo.gridview.GridViewActivity;
import com.imswy.slideviewdemo.listview.ListViewActivity;
import com.imswy.slideviewdemo.recyclerview.RecyclerViewActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_ListView, btn_RecyclerView, btn_GridView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView () {
        btn_ListView = findViewById(R.id.btn_ListView);
        btn_ListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });

        btn_RecyclerView = findViewById(R.id.btn_RecyclerView);
        btn_RecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
            }
        });

        btn_GridView = findViewById(R.id.btn_GridView);
        btn_GridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(MainActivity.this, GridViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
