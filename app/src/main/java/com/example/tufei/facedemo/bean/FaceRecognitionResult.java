package com.example.tufei.facedemo.bean;

import java.util.List;

/**
 * 人脸识别结果封装
 * @author wzh
 * @date 2017/12/31
 */
public class FaceRecognitionResult {


    /**
     * uid : 1
     * scores : [100]
     * group_id : group_1
     * user_info : {"id":"1","name":"古天乐","evaluate":"只有太阳才能黑的男人。"}
     */
    private String uid;
    private String group_id;
    private String user_info;
    private List<Double> scores;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getUser_info() {
        return user_info;
    }

    public void setUser_info(String user_info) {
        this.user_info = user_info;
    }

    public List<Double> getScores() {
        return scores;
    }

    public void setScores(List<Double> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "FaceRecognitionResult{" +
                "uid='" + uid + '\'' +
                ", group_id='" + group_id + '\'' +
                ", user_info='" + user_info + '\'' +
                ", scores=" + scores +
                '}';
    }
}
