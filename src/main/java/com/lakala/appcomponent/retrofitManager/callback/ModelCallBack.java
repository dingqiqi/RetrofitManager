package com.lakala.appcomponent.retrofitManager.callback;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lakala.appcomponent.retrofitManager.mode.BaseResponse;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * ResponseCallBack
 * Created by dingqq on 2018/7/13.
 */

public abstract class ModelCallBack<T> extends BaseCallBack<T> {

    @Override
    public T parseResponse(Response<ResponseBody> response) {

        if (response != null) {

            return new Gson().fromJson(response.toString(), new TypeToken<BaseResponse<T>>(){}.getType());
        }

        return null;
    }
}
