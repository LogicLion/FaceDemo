package com.example.tufei.facedemo.net;

import com.example.tufei.facedemo.bean.AccessToken;
import com.example.tufei.facedemo.bean.FaceHttpResult;
import com.example.tufei.facedemo.bean.FaceRecognitionResult;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.FormBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * @author wzh
 * @date 2017/12/28
 */
public interface HttpService {

    /**
     * 获取使用百度人脸识别时，必须的access_token
     * 当BaseUrl不适用时，用@Url标注,传一个完整的url即可
     *
     * @param accessTokenUrl
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<AccessToken> getAccessToken(@Url String accessTokenUrl, @FieldMap Map<String, String> params);

    /**
     * 人脸注册
     * @param body
     * @return
     */
    @POST("faceset/user/add")
    Observable<FaceHttpResult> saveFace(@Body FormBody body);

    /**
     * 人脸识别
     * @param body
     * @return
     */
    @POST("identify")
    Observable<FaceHttpResult<List<FaceRecognitionResult>>> recognizeFace(@Body FormBody body);

    /**
     * 人脸删除
     * @param body
     * @return
     */
    @POST("faceset/user/delete")
    Observable<FaceHttpResult> deleteFace(@Body FormBody body);

    /**
     * 获取人脸
     * @param body
     * @return
     */
    @POST("faceset/user/get")
    Observable<FaceHttpResult<List<FaceRecognitionResult>>> getFace(@Body FormBody body);
}
