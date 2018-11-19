package com.umenq.soexample.net;



import java.io.IOException;

public interface ICallBack {


    void onSuccess(Object o);
    void onFailed(IOException e);



}
