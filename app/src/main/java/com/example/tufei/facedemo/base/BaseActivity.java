package com.example.tufei.facedemo.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * @author wzh
 * @date 2017/12/28
 */
public abstract class BaseActivity extends DaggerAppCompatActivity {
    IBasePresenter mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setResourceID());
        //绑定view布局
        ButterKnife.bind(this);

        //设置presenter
        mPresenter =bindPresenter();
        if (mPresenter != null) {
            mPresenter.onAttachView((IBaseView) this);
        }

        //初始化布局数据
        initData();
    }

    /**
     * 设置presenter{@link IBasePresenter}
     * @return
     */
    protected abstract  IBasePresenter bindPresenter();

    /**
     * 界面跳转
     * @param clazz
     */
    public void startActivity(@NonNull Class clazz) {
        Intent intent = new Intent();
        intent.setClass(this,clazz);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDetachView();
        }
        super.onDestroy();
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置布局资源文件ID
     * @return 资源文件ID
     */
    protected abstract int setResourceID();

}
