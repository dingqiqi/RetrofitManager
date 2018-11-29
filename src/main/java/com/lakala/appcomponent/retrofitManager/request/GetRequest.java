package com.lakala.appcomponent.retrofitManager.request;


import com.lakala.appcomponent.retrofitManager.RetrofitManager;
import com.lakala.appcomponent.retrofitManager.call.RetrofitCall;
import com.lakala.appcomponent.retrofitManager.inter.BaseRequestInter;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by dingqq on 2018/7/18.
 */
public class GetRequest extends BaseRequest {

    public GetRequest(String url, Map<String, String> heads, Map<String, String> params) {
        super(url, heads, params);
    }

    @Override
    public RetrofitCall build() {
        return new RetrofitCall(this);
    }

    @Override
    public BaseRequestInter buildRequest() {
        return RetrofitManager.getInstance().getRetrofit().create(BaseRequestInter.class);
    }

    @Override
    public Call<ResponseBody> buildCall(BaseRequestInter request) {
        if (request == null) {
            return null;
        }

        if (heads == null) {
            heads = new HashMap<>();
        }

        return request.doGet(url, heads);
    }
}
