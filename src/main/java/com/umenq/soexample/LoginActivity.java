package com.umenq.soexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.umeng.socialize.UMShareAPI;

import com.umeng.socialize.bean.SHARE_MEDIA;

import com.umenq.soexample.util.DengLU;

import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 请输入用户名
     */
    private EditText mEditName;
    /**
     * 请输入密码
     */
    private EditText mEditPwd;
    /**
     * QQ登录
     */
    private ImageView mBtnQq;
    /**
     * 微信登录
     */
    private ImageView mBtnWx;
    private UMShareAPI umShareAPI;
    private DengLU denglu;
    /**
     * 登录
     */
    private Button mBtnDeng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    private void initData() {
        umShareAPI = UMShareAPI.get(this);
        denglu = new DengLU();


    }

    private void initView() {
        mEditName = (EditText) findViewById(R.id.edit_name);
        mEditPwd = (EditText) findViewById(R.id.edit_pwd);
        mBtnQq = (ImageView) findViewById(R.id.btn_qq);
        mBtnQq.setOnClickListener(this);
        mBtnWx = (ImageView) findViewById(R.id.btn_wx);
        mBtnWx.setOnClickListener(this);
        mEditName.setOnClickListener(this);
        mEditPwd.setOnClickListener(this);
        mBtnDeng = (Button) findViewById(R.id.btn_deng);
        mBtnDeng.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_qq:

                umShareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, denglu.authListener);

                denglu.setResuse(new DengLU.Resuse() {
                    @Override
                    public void Resu(Map<String, String> map) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("name", map.get("name"));
                        intent.putExtra("touxiang", map.get("profile_image_url"));
                        startActivity(intent);
                    }
                });


                break;
            case R.id.btn_wx:
                umShareAPI.getPlatformInfo(this, SHARE_MEDIA.WEIXIN, denglu.authListener);
                break;

            case R.id.btn_deng:
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);


                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }


}
