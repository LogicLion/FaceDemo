package com.example.tufei.facedemo.di;

import com.example.tufei.facedemo.MainActivity;
import com.example.tufei.facedemo.mvp.face.FaceActivity;
import com.example.tufei.facedemo.mvp.face.FaceModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author wzh
 * @date 2017/12/28
 */
@Module
public abstract class ActivityBindingModule {

    /**
     * 如果你的Activity不需要任何Module，即Activity简单到不需要注入任何东西的时候，也要在这里声明它
     * 除非你的Activity不再是继承自{@link dagger.android.support.DaggerAppCompatActivity}
     * @return
     */
    @ContributesAndroidInjector()
    abstract MainActivity mainActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = FaceModule.class)
    abstract FaceActivity faceActivity();
}
