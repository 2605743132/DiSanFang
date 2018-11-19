package com.umenq.soexample.pumvp.presenter;


import com.google.gson.reflect.TypeToken;
import com.umenq.soexample.bean.Bean;
import com.umenq.soexample.net.ICallBack;
import com.umenq.soexample.pumvp.model.HomeModel;
import com.umenq.soexample.pumvp.view.IView;
import com.umenq.soexample.util.UrlHolder;

import java.io.IOException;
import java.lang.reflect.Type;

public class HomePesenter {

    private IView iv;
    private HomeModel model;
    public void attach(IView iv){
        this.iv = iv;
        model = new HomeModel();
    }
    public void getProduct(){

        Type type = new TypeToken<Bean>() {
        }.getType();
        model.getData(UrlHolder.RECVIEW, new ICallBack() {
            @Override
            public void onSuccess(Object o) {
                        Bean bean = (Bean) o;
                        if (bean != null){
                            iv.getpru(bean.getData());
                        }
                    }


                    @Override
            public void onFailed(IOException e) {
                iv.onFailed(e);
            }

        },type);
    }
    public void onDestroy(){
        if (iv != null){
            iv = null;
        }
    }
}
