package com.example.tufei.facedemo.net;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author wzh
 * @date 2017/12/29
 */
@Module
public class NetModule {

    @Singleton
    @Provides
    public HttpService provideHttpService() {
        return RetrofitFactory.createRetrofit();
    }
}
