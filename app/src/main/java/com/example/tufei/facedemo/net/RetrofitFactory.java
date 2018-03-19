package com.example.tufei.facedemo.net;

import com.example.tufei.facedemo.constants.FaceConstants;
import com.example.tufei.facedemo.constants.NetConstants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author wzh
 * @date 2017/12/29
 */
class RetrofitFactory {
    public static HttpService createRetrofit() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(FaceConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(creatHttpClient())
                .build();
        HttpService httpService  = retrofit.create(HttpService.class);
        return  httpService;
    }

    private static OkHttpClient creatHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(NetConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(NetConstants.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/json; charset=UTF-8")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();

        return httpClient;
    }
}
