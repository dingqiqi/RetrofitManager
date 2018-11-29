package com.lakala.appcomponent.retrofitManager.request;

import com.lakala.appcomponent.retrofitManager.call.RetrofitCall;
import com.lakala.appcomponent.retrofitManager.inter.BaseRequestInter;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by dingqq on 2018/7/18.
 */

public abstract class BaseRequest {

    protected String url;

    protected Map<String, String> heads;

    protected Map<String, String> params;

    public BaseRequest(String url, Map<String, String> heads, Map<String, String> params) {
        this.url = url;
        this.heads = heads;
        this.params = params;
    }

    public abstract RetrofitCall build();

    abstract public BaseRequestInter buildRequest();

    abstract public Call<ResponseBody> buildCall(BaseRequestInter request);
}
