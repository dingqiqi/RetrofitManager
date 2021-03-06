package com.lakala.appcomponent.retrofitManager.request;

import android.text.TextUtils;

import com.lakala.appcomponent.retrofitManager.RetrofitManager;
import com.lakala.appcomponent.retrofitManager.call.RetrofitCall;
import com.lakala.appcomponent.retrofitManager.inter.BaseRequestInter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by dingqq on 2018/7/18.
 */
public class PostFormFileRequest extends BaseRequest {

    private static MediaType DEFAULT_STREAM_TYPE = MediaType.parse("application/octet-stream");

    private MediaType mediaType;

    private List<File> files;

    private String fileKey;

    public PostFormFileRequest(String url, Map<String, String> heads, Map<String, String> params, MediaType mediaType, List<File> files, String fileKey) {
        super(url, heads, params);
        this.mediaType = mediaType;
        this.files = files;
        this.fileKey = fileKey;

        if (TextUtils.isEmpty(fileKey)) {
            this.fileKey = "file";
        }
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

        if (files == null || files.size() == 0) {

            if (params == null) {
                params = new HashMap<>();
            }

            if (heads == null) {
                heads = new HashMap<>();
            }

            return request.doPostForm(url, params, heads);

        } else {
            if (mediaType == null) {
                mediaType = DEFAULT_STREAM_TYPE;
            }

            List<MultipartBody.Part> list = new ArrayList<>();

            for (File file : files) {
                if (file != null && file.exists()) {
                    list.add(MultipartBody.Part.createFormData(fileKey, file.getName(), RequestBody.create(mediaType, file)));
                }
            }

            if (params == null) {
                params = new HashMap<>();
            }

            if (heads == null) {
                heads = new HashMap<>();
            }

            return request.doFormFile(url, params, list, heads);
        }

    }
}
