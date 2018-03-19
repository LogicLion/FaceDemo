package com.example.tufei.facedemo;

import com.example.tufei.facedemo.base.BaseActivity;
import com.example.tufei.facedemo.base.IBasePresenter;
import com.example.tufei.facedemo.mvp.face.FaceActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {



    @Override
    protected IBasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setResourceID() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.btn_face)
    public void onViewClick() {
        startActivity(FaceActivity.class);
    }


}
