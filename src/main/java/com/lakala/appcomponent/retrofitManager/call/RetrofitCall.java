package com.lakala.appcomponent.retrofitManager.call;

import com.lakala.appcomponent.retrofitManager.callback.BaseCallBack;
import com.lakala.appcomponent.retrofitManager.request.BaseRequest;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * RetrofitCall
 * Created by dingqq on 2018/7/18.
 */

public class RetrofitCall {

    private BaseRequest mBaseRequest;

    private Call<ResponseBody> mCall;

    private Executor mExecutor = Executors.newFixedThreadPool(1);

    public RetrofitCall(BaseRequest baseBuilder) {
        this.mBaseRequest = baseBuilder;
    }

    public Call<ResponseBody> getCall() {
        return mCall;
    }

    /**
     * 异步执行
     *
     * @param callBack 回调
     */
    public void execute(final BaseCallBack callBack) {
        if (callBack == null) {
            return;
        }

        mCall = mBaseRequest.buildCall(mBaseRequest.buildRequest());

        if (mCall != null) {
            mCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                    if (!call.isCanceled()) {
                        if (response.isSuccessful()) {
                            mExecutor.execute(new Runnable() {
                                @Override
                                public void run() {
                                    callBack.onSuccess(callBack.parseResponse(response));
                                }
                            });

                        } else {
                            callBack.onFail(response.code(), response.message(), new IllegalArgumentException(response.message()));
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, final Throwable t) {
                    if (!call.isCanceled()) {
                        callBack.onFail(-1, t.getMessage(), t);
                    }
                }
            });

        } else {
            throw new IllegalArgumentException("buildRequest is null");
        }

    }

    /**
     * 同步执行
     *
     * @return 返回值
     * @throws IOException 异常
     */
    public Response<ResponseBody> execute() throws IOException {
        mCall = mBaseRequest.buildCall(mBaseRequest.buildRequest());

        if (mCall != null) {
            return mCall.execute();
        } else {
            throw new IllegalArgumentException("buildRequest is null");
        }
    }

    public void cancel() {
        if (mCall != null) {
            mCall.cancel();
        }
    }
}
