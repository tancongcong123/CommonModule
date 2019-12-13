package com.jx.commonlibrary.test;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {
    @GET
    Observable<String> getString(@Url String url, @QueryMap Map<String, Object> map);
}
