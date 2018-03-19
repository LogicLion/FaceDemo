package com.example.tufei.facedemo.base;

/**
 * @author wzh
 * @date 2018/1/6
 */
public interface IBasePresenter<T extends IBaseView> {
    void onAttachView(T view);

    void onDetachView();
}
