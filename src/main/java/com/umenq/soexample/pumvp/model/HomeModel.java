package com.umenq.soexample.pumvp.model;



import com.umenq.soexample.net.HttpUtils;
import com.umenq.soexample.net.ICallBack;
import com.umenq.soexample.util.UrlHolder;

import java.lang.reflect.Type;

public class HomeModel {
    public void getData(String url, ICallBack callBack, Type type) {
        HttpUtils.getInstance().get(UrlHolder.RECVIEW, callBack, type);

    }
}
