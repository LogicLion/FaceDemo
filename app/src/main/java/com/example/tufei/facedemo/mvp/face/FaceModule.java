package com.example.tufei.facedemo.mvp.face;

import com.example.tufei.facedemo.di.ActivityScoped;

import dagger.Binds;
import dagger.Module;

/**
 * @author wzh
 * @date 2017/12/28
 */
@Module
public abstract class FaceModule {
    @ActivityScoped
    @Binds
   abstract FaceContract.Presenter FacePresenter(FacePresenter facePresenter);
}
