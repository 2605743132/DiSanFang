package com.umenq.soexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImgdong;
    /**
     * 动起来兄弟
     */
    private Button mDong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        initView();
        initData();
        initDonghua();
    }

    private void initDonghua() {


    }

    private void initData() {
        String tou = getIntent().getStringExtra("tou");
        Glide.with(this).load(tou).into(mImgdong);

    }

    private void initView() {

        mImgdong = (ImageView) findViewById(R.id.imgdong);
        mDong = (Button) findViewById(R.id.dong);
        mDong.setOnClickListener(this);
        mImgdong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.dong:
                break;

        }
    }
}
