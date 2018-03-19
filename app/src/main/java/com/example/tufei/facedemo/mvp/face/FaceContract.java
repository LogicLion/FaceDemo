package com.example.tufei.facedemo.mvp.face;

import com.example.tufei.facedemo.base.IBasePresenter;
import com.example.tufei.facedemo.base.IBaseView;

/**
 * @author wzh
 * @date 2018/1/6
 */
public interface FaceContract {
    interface View extends IBaseView {
        void showToast(String tip);
    }

    interface Presenter extends IBasePresenter<View> {
        void getAccessToken();

        void saveFace(int id);

        void recognizeFace(int id);

        void deleteFace(int id);

        void getFace(int id);
    }

}
