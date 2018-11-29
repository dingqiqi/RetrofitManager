package com.lakala.appcomponent.retrofitManager.mode;

/**
 * Created by dingqq on 2018/7/18.
 */

public class BaseResponse<T> {

    int code;
    String message;
    T body;
}
