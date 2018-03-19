package com.example.tufei.facedemo.constants;

/**
 * @author wzh
 * @date 2017/12/28
 */
public class FaceConstants {

    /**
     * 获取token的url
     */
    public static final String ACCESS_TOKEN_URL = "https://aip.baidubce.com/oauth/2.0/token";

    /**
     * 官网获取的 API Key
     */
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_ID_VALUE = "2fGgISyjT2wUwOK8iLOFnG8C";
    /**
     * 官网获取的 Secret Key
     */
    public static final String CLIENT_SECRET = "client_secret";
    public static final String CLIENT_SECRET_VALUE = "FBp8yj2X6YedxHD5p5nsO1XMnkKovCt1";
    /**
     * 获取access_token必须参数 固定为client_credentials
     */
    public static final String GRANT_TYPE = "grant_type";
    public static final String GRANT_TYPE_VALUE = "client_credentials";

    /**
     *baseUrl
     */
    public static final String BASE_URL ="https://aip.baidubce.com/rest/2.0/face/v2/";

    /**
     * 使用百度人脸识别接口时，必须的参数：access_token
     */
    public static final String ACCESS_TOKEN = "access_token";
    public static String ACCESS_TOKEN_VALUE ;

    /**
     * 用户id（由数字、字母、下划线组成），长度限制128B。
     */
    public static final String UID = "uid";

    /**
     * 用户资料，长度限制256B
     */
    public static final String USER_INFO = "user_info";

    /**
     * 用户组id，标识一组用户（由数字、字母、下划线组成），长度限制128B。
     */
    public static final String GROUP_ID = "group_id";
    public static final String GROUP_ID_VALUE = "group_1";

    /**
     * 参数包含append、replace。如果为“replace”，则每次注册时进行替换replace（新增或更新）操作，默认为append操作。
     */
    public static final String ACTION_TYPE = "action_type";
    public static final String ACTION_TYPE_REPLACE = "replace";


    public static final String IMAGE = "image";

    /**
     * 返回用户top数，默认为1。(第一个就是匹配度最高的)
     */
    public static final String USER_TOP_NUM ="user_top_num";
    public static final String USER_TOP_NUM_VALUE ="1";

}
