package com.example.tufei.facedemo.mvp.face;

import com.example.tufei.facedemo.utils.BaseTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author wzh
 * @date 2018/1/13
 */
public class FacePresenterTest extends BaseTest {

    private FacePresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        FaceTask faceTask = new FaceTask(mHttpService);
        mPresenter = new FacePresenter(mContext, faceTask);
        mPresenter.getAccessToken();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAccessToken() throws Exception {
        mPresenter.getAccessToken();
    }

    @Test
    public void saveFace() throws Exception {
        mPresenter.saveFace(1);
    }

    @Test
    public void recognizeFace() throws Exception {
    }

    @Test
    public void deleteFace() throws Exception {
    }

    @Test
    public void getFace() throws Exception {
    }

}