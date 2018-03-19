package com.example.tufei.facedemo.di;

import android.app.Application;

import com.example.tufei.facedemo.App;
import com.example.tufei.facedemo.net.HttpService;
import com.example.tufei.facedemo.net.NetModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author wzh
 * @date 2017/12/28
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        ApplicationModule.class,
        NetModule.class})
public interface AppComponent extends AndroidInjector<App> {

    HttpService httpService();

    /**
     * 必须写的模板代码。
     * 给予我们语法糖，让我们可以做DaggerAppComponent.builder().application(this).build().inject(this);
     * 永远不必实例化任何module，或者说我们将会传递application给那些module。
     * Application将会提供给我们的app。
     *
     * Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
     * never having to instantiate any modules or say which module we are passing the application to.
     * Application will just be provided into our app graph now.
     */
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
