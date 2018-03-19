package com.example.tufei.facedemo.mvp.face;

import android.util.Log;

import com.example.tufei.facedemo.bean.AccessToken;
import com.example.tufei.facedemo.bean.FaceHttpResult;
import com.example.tufei.facedemo.bean.FaceRecognitionResult;
import com.example.tufei.facedemo.bean.User;
import com.example.tufei.facedemo.constants.FaceConstants;
import com.example.tufei.facedemo.net.HttpService;
import com.example.tufei.facedemo.utils.Base64Util;
import com.example.tufei.facedemo.utils.RxUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.FormBody;

/**
 * @author wzh
 * @date 2017/12/28
 */
public class FaceTask {
    private HttpService mHttpService;
    private final static int minMatchScore=80;

    @Inject
    public FaceTask(HttpService httpService) {
        mHttpService = httpService;
    }

    /**
     * 使用百度人脸识别时，需要获取accessToken
     *
     * @return
     */
    public Observable<AccessToken> getAccessToken() {
        Map<String, String> params = new HashMap<>(3);
        params.put(FaceConstants.GRANT_TYPE, FaceConstants.GRANT_TYPE_VALUE);
        params.put(FaceConstants.CLIENT_ID, FaceConstants.CLIENT_ID_VALUE);
        params.put(FaceConstants.CLIENT_SECRET, FaceConstants.CLIENT_SECRET_VALUE);

        return mHttpService.getAccessToken(FaceConstants.ACCESS_TOKEN_URL, params)
                .compose(RxUtil.io_Main())
                .doOnNext(accessToken -> FaceConstants.ACCESS_TOKEN_VALUE = accessToken.getAccess_token())
                .retry(3);

    }

    /**
     * 保存人脸
     *
     * @param image
     * @param user
     * @return
     */
    public Observable<FaceHttpResult> saveFace(byte[] image, User user) {
        String json = new Gson().toJson(user);

        FormBody formBody = new FormBody.Builder()
                .add(FaceConstants.ACCESS_TOKEN, FaceConstants.ACCESS_TOKEN_VALUE)
                .add(FaceConstants.UID, user.getId())
                .add(FaceConstants.GROUP_ID, FaceConstants.GROUP_ID_VALUE)
                .add(FaceConstants.USER_INFO, json)
                .add(FaceConstants.IMAGE, Base64Util.encode(image))
                .add(FaceConstants.ACTION_TYPE, FaceConstants.ACTION_TYPE_REPLACE)
                .build();

        return mHttpService.saveFace(formBody).compose(RxUtil.io_Main())
                .flatMap(faceHttpResult -> {
                            if (faceHttpResult.getError_msg() != null || faceHttpResult.getError_code() != 0) {
                                return Observable.error(new Exception(faceHttpResult.getError_msg()));
                            } else {
                                return Observable.just(faceHttpResult);
                            }
                        }
                );
    }


    /**
     * 人脸识别
     *
     * @param image
     * @return
     */
    public Observable<User> recognizeFace(byte[] image) {
        FormBody body = new FormBody.Builder()
                .add(FaceConstants.ACCESS_TOKEN, FaceConstants.ACCESS_TOKEN_VALUE)
                .add(FaceConstants.IMAGE, Base64Util.encode(image))
                .add(FaceConstants.USER_TOP_NUM, FaceConstants.USER_TOP_NUM_VALUE)
                .add(FaceConstants.GROUP_ID, FaceConstants.GROUP_ID_VALUE)
                .build();

        return mHttpService.recognizeFace(body).compose(RxUtil.io_Main())
                .flatMap(recognizeResult -> {

                    if (recognizeResult.getError_msg() != null || recognizeResult.getError_code()!=0) {
                        return Observable.error(new Exception("识别错误:"+recognizeResult.getError_msg()));
                    }else{
                        FaceRecognitionResult result = recognizeResult.getResult().get(0);
                        if (result.getScores().get(0) < minMatchScore) {
                            return Observable.error(new Exception("匹配度不够:匹配度"+result.getScores().get(0)));
                        }else{
                            Gson gson = new Gson();
                            User user = gson.fromJson(result.getUser_info(), User.class);
                            Log.v("测试","匹配度"+result.getScores()+"匹配信息"+result.getUser_info());
                            return Observable.just(user);
                        }
                    }
                });

    }


    /**
     * 删除人脸
     *
     * @param id
     * @return
     */
    public Observable<FaceHttpResult> deleteFace(String id) {
        FormBody body = new FormBody.Builder()
                .add(FaceConstants.ACCESS_TOKEN, FaceConstants.ACCESS_TOKEN_VALUE)
                .add(FaceConstants.GROUP_ID, FaceConstants.GROUP_ID_VALUE)
                .add(FaceConstants.UID, id)
                .build();

        return mHttpService.deleteFace(body).compose(RxUtil.io_Main())
                .flatMap(faceHttpResult -> {
                    if (faceHttpResult.getError_code() != 0 || faceHttpResult.getError_msg() != null) {
                        return Observable.error(new Exception(faceHttpResult.getError_msg()));
                    } else {
                        return Observable.just(faceHttpResult);
                    }
                });
    }

    /**
     * 获取人脸
     *
     * @param id
     * @return
     */
    public Observable<User> getFace(String id) {
        FormBody body = new FormBody.Builder()
                .add(FaceConstants.ACCESS_TOKEN, FaceConstants.ACCESS_TOKEN_VALUE)
                .add(FaceConstants.GROUP_ID, FaceConstants.GROUP_ID_VALUE)
                .add(FaceConstants.UID, id)
                .build();

        return mHttpService.getFace(body).compose(RxUtil.io_Main())
                .flatMap(faceHttpResult -> {
                    if (faceHttpResult.getError_code() != 0 || faceHttpResult.getError_msg() != null) {
                        return Observable.error(new Exception("获取失败" + faceHttpResult.getError_msg()));
                    } else {
                        FaceRecognitionResult result = faceHttpResult.getResult().get(0);
                        Gson gson = new Gson();
                        User user = gson.fromJson(result.getUser_info(), User.class);
                        Log.v("获取成功，人脸数据：", result.getUser_info());
                        return Observable.just(user);
                    }
                });
    }
}
