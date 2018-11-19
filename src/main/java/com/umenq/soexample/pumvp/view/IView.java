package com.umenq.soexample.pumvp.view;


import com.umenq.soexample.bean.Bean;

import java.util.List;

public interface IView {

    void getpru(List<Bean.DataBean> list);


    void onFailed(Exception e);
}
