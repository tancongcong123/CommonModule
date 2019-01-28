package com.jx.commonlibrary.http;

import com.jx.commonlibrary.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class ApiManager {

    private static ApiManager apiManager;
    private String baseUrl;
    private OkHttpClient customClient;

    public static ApiManager getInstance(){
        synchronized (ApiManager.class){
            if (apiManager==null){
                apiManager = new ApiManager();
            }
        }
        return apiManager;
    }

    public void setBaseUrl(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl(){
        return baseUrl;
    }

    private OkHttpClient getDefaultClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        if (BuildConfig.DEBUG){
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        return new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS) //设置超时时间
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    public Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                // 针对rxjava2.x Service接口默认返回Call<T> 要想返回Observable<T>,需得加上这一句
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(customClient==null?getDefaultClient():customClient)
                // 可以接收自定义的Gson，将Json结果并解析成DAO,当然也可以不传
                .addConverterFactory(JxGsonConverterFactory.create())
                .build();
    }

    public void setCustomClient(OkHttpClient customClient) {
        this.customClient = customClient;
    }
}
