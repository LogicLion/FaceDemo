package com.example.tufei.facedemo.mvp.face;

import android.view.View;
import android.widget.Toast;

import com.example.tufei.facedemo.R;
import com.example.tufei.facedemo.base.BaseActivity;
import com.example.tufei.facedemo.base.IBasePresenter;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * @author wzh
 * @date 2018/1/6
 */
public class FaceActivity extends BaseActivity implements FaceContract.View {
    @Inject
    FaceContract.Presenter mPresenter;


    @Override
    protected IBasePresenter bindPresenter() {
        return mPresenter;
    }

    @Override
    protected void initData() {
        mPresenter.getAccessToken();
    }

    @Override
    protected int setResourceID() {
        return R.layout.activity_face;
    }

    @OnClick({R.id.btn_save_gutianle,
            R.id.btn_save_wuyanzu,
            R.id.btn_delete_gutianle,
            R.id.btn_delete_wuyanzu,
            R.id.btn_get_gutianle,
            R.id.btn_get_wuyanzu,
            R.id.btn_recognize_gutianle,
            R.id.btn_recognize_wuyanzu,
            R.id.btn_recognize_noface})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save_gutianle:
                mPresenter.saveFace(1);
                break;
            case R.id.btn_save_wuyanzu:
                mPresenter.saveFace(2);
                break;
            case R.id.btn_get_gutianle:
                mPresenter.getFace(1);
                break;
            case R.id.btn_get_wuyanzu:
                mPresenter.getFace(2);
                break;
            case R.id.btn_delete_gutianle:
                mPresenter.deleteFace(1);
                break;
            case R.id.btn_delete_wuyanzu:
                mPresenter.deleteFace(2);
                break;
            case R.id.btn_recognize_gutianle:
                mPresenter.recognizeFace(1);
                break;
            case R.id.btn_recognize_wuyanzu:
                mPresenter.recognizeFace(2);
                break;
            case R.id.btn_recognize_noface:
                mPresenter.recognizeFace(3);
                break;
            default:
                break;
        }
    }

    @Override
    public void showToast(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
    }
}
