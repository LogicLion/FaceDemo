package com.example.tufei.facedemo.mvp.face;

import android.util.Log;

import com.example.tufei.facedemo.bean.User;
import com.example.tufei.facedemo.constants.FaceConstants;
import com.example.tufei.facedemo.utils.BaseTest;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wzh
 * @date 2017/12/29
 */
public class FaceModelTest extends BaseTest {


    private FaceTask mFaceTask;

    private String ID = "1";
    private String NAME = "古天乐";
    private String EVALUATION = "只有太阳才能黑的男人。";
    private String FACE_FILE_NAME = "gutianle.jpg";
    private String FACE_FILE_NAME2 = "gutianle2.jpg";

    private String NO_MATCH_FACE_FILE_NAME = "wuyanzu.jpg";
    private String NO_FACE_FILE_NAME = "noface.png";

    @Before
    public void setUp() throws Exception {

        mFaceTask =new FaceTask(mHttpService);
        mFaceTask.getAccessToken().test();
    }


    @Test
    public void getAccessToken() throws Exception {

        mFaceTask.getAccessToken().test().assertNoErrors().onComplete();
    }

    @Test
    public void testSaveFace() throws Exception {
        byte[] bytes = fileToBytes(FACE_FILE_NAME);
        Log.v("现在的accessToken是:",FaceConstants.ACCESS_TOKEN_VALUE);
        mFaceTask.saveFace(bytes, new User(ID, NAME, EVALUATION)).test().assertNoErrors();

    }

    @Test
    public void testRecognizeFace() throws Exception {
        byte[] bytes = fileToBytes(NO_MATCH_FACE_FILE_NAME);
        mFaceTask.recognizeFace(bytes).test().assertNoErrors();
    }

    @Test
    public void testDeleteFace() throws Exception {
        mFaceTask.deleteFace("1")
                .test()
                .assertNoErrors();
    }

    @Test
    public void testGetFace() throws Exception {
        mFaceTask.getFace("1")
                .test()
                .assertNoErrors();
    }

    private byte[] fileToBytes(String fileName) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        int len;
        byte[] bytes;
        len = inputStream.available();
        bytes = new byte[len];
        inputStream.read(bytes, 0, len);
        inputStream.close();
        return bytes;
    }

}