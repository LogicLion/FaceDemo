package com.example.tufei.facedemo.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author wzh
 * @date 2018/1/11
 */
public class BasePresenter {

    CompositeDisposable mCompositeDisposable;

    /**
     * 把所有订阅存储起来
     * @param disposable
     */
    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }

        mCompositeDisposable.add(disposable);
    }

    /**
     * 取消所有的订阅
     */
    public void clearDisposite() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
        mCompositeDisposable = null;
    }
}
