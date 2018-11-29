package com.lakala.appcomponent.retrofitManager.callback;

import com.lakala.appcomponent.retrofitManager.mode.HttpResponse;

import java.io.IOException;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * ResponseCallBack
 * Created by dingqq on 2018/7/13.
 */

public abstract class ResponseCallBack extends BaseCallBack<HttpResponse> {

    @Override
    public HttpResponse parseResponse(retrofit2.Response<ResponseBody> response) {
        return getResponse(response);
    }

    private HttpResponse getResponse(retrofit2.Response<ResponseBody> response) {
        if (response == null) {
            return null;
        }

        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setCode(response.code());
        httpResponse.setMessage(response.message());

        ResponseBody body = response.body();
        if (body != null) {
            try {
                httpResponse.setBody(body.string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return httpResponse;
    }
}
