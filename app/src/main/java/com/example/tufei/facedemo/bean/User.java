package com.example.tufei.facedemo.bean;

/**
 * 用户信息bean
 * @author wzh
 * @date 2017/12/30
 */
public class User {
    String id;
    String name;
    String evaluate;

    public User(String id, String name, String evaluate) {
        this.id = id;
        this.name = name;
        this.evaluate = evaluate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", evaluate='" + evaluate + '\'' +
                '}';
    }
}
