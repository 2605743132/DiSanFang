package com.umenq.soexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.umenq.soexample.adapter.MyAdapter;
import com.umenq.soexample.bean.Bean;
import com.umenq.soexample.pumvp.presenter.HomePesenter;
import com.umenq.soexample.pumvp.view.IView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {

    private CircleImageView mImg;
    /**
     * 去问人体
     */
    private TextView mMyname;
    private RecyclerView mRv;
    /**
     * 加
     */
    private Button mBtnJia;
    /**
     * 减
     */
    private Button mJian;
    private List<Bean.DataBean> integers;
    private MyAdapter adapter;
    private HomePesenter homePesenter;
    private String touxiang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClick();
        initRecyler();

       initItem();

    }

    private void initRecyler() {

        homePesenter = new HomePesenter();
        homePesenter.attach(this);
        homePesenter.getProduct();
        integers = new ArrayList<>();
        mRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new MyAdapter(this, integers);
        mRv.setAdapter(adapter);
    }

 private void initItem() {

        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(MainActivity.this,ShowActivity.class);
                intent.putExtra("tou",touxiang);
                startActivity(intent);

            }
        });

    }

    //获取登录界面传过来的头像和name并赋值
    private void initClick() {
        String name = getIntent().getStringExtra("name");
       touxiang = getIntent().getStringExtra("touxiang");
//        mImg.setImageURI(Uri.parse(touxiang));
        Glide.with(this).load(touxiang).into(mImg);
        mMyname.setText(name);

    }

    private void initView() {
        mImg = (CircleImageView) findViewById(R.id.img);
        mMyname = (TextView) findViewById(R.id.myname);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mBtnJia = (Button) findViewById(R.id.btn_jia);
        mBtnJia.setOnClickListener(this);
        mJian = (Button) findViewById(R.id.jian);
        mJian.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_jia:

                adapter.notifyItemInserted(5);
                adapter.notifyDataSetChanged();

                break;
            case R.id.jian:
                integers.remove(5);
                adapter.notifyItemRemoved(5);
                adapter.notifyDataSetChanged();
                break;
        }
    }
    @Override
    public void getpru(List<Bean.DataBean> list) {
        if (list != null){
            integers.clear();
            integers.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailed(Exception e) {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (homePesenter != null){
            homePesenter.onDestroy();
        }
    }
}
