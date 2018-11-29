package com.lakala.appcomponent.retrofitManager.builder;

import android.net.Uri;

import com.lakala.appcomponent.retrofitManager.call.RetrofitCall;
import com.lakala.appcomponent.retrofitManager.inter.ParamInter;
import com.lakala.appcomponent.retrofitManager.request.GetRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * GetBuilder
 * Created by dingqq on 2018/7/18.
 */

public class GetBuilder extends BaseBuilder<GetBuilder> implements ParamInter<BaseBuilder> {

    @Override
    public GetBuilder addParam(String key, String value) {
        if (params == null) {
            params = new HashMap<>();
        }
        params.put(key, value);

        return this;
    }

    @Override
    public GetBuilder params(Map<String, String> params) {
        this.params = params;
        return this;
    }

    private void appendParams() {
        if (params != null) {
            Uri.Builder builder = Uri.parse(url).buildUpon();

            for (String key : params.keySet()) {
                builder.appendQueryParameter(key, params.get(key));
            }

            url = builder.build().toString();
        }
    }

    @Override
    public RetrofitCall build() {
        appendParams();

        return new GetRequest(url, heads, params).build();
    }


}
