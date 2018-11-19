package com.umenq.soexample.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DengLU {
    private Resuse resuse;
    private static final String TAG = "DengLU";
    public UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }


        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> map) {
              resuse.Resu(map);

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

              }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
                }
    };

    public interface Resuse{
        void Resu(Map<String,String> map);

    }

    public void setResuse(Resuse resuse) {
        this.resuse = resuse;
    }
}
