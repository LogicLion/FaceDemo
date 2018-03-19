package com.example.tufei.facedemo.bean;

/**
 * @author wzh
 * @date 2017/12/30
 * 请求百度人脸接口时返回的json的实体类封装
 */
public class FaceHttpResult<T> {

    /**
     * 成功时，error_code为0
     */
    private int error_code;
    /**
     * 成功时，error_msg为null
     */
    private String error_msg;
    private long log_id;
    private int result_num;
    private T result;

    public int getError_code() {
        return error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public long getLog_id() {
        return log_id;
    }

    public int getResult_num() {
        return result_num;
    }

    public T getResult() {
        return result;
    }
}
