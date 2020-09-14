package com.zyjy.qq.util;

import net.sf.json.JSONObject;

public class JsonUtil {
    public static final String LOGIN = "LOGIN";
    public static final String LOGOFF = "LOGOFF";
    public static final String NORMAL = "NORMAL";
    public static final String ABNORMAL = "ABNORMAL";
    public static final String REGISTER = "REGISTER";
    public static final String GROUP_FRIEND = "GROUP_FRIEND";
    public static final String HISTORY = "HISTORY";
    public static final String CHAT = "CHAT";
    //在线文件
    public static final String SEND_FILE = "SEND_FILE";
    public static final String WAITING = "WAITING";
    public static final String ACCEPT = "ACCEPT";
    public static final String REFUSE = "REFUSE";
    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    //离线文件
    public static final String ADD_SEND_FILE = "ADD_SEND_FILE";
    public static final String DELETE_SEND_FILE = "DELETE_SEND_FILE";
    public static final String UPLOAD_FILE = "UPLOAD_FILE";
    public static final String DOWNLOAD_FILE = "DOWNLOAD_FILE";

    public static String toJsonString(Object object) {
        return JSONObject.fromObject(object).toString();
    }

    public static JSONObject toJsonObject(String jsonString) {
        return JSONObject.fromObject(jsonString);
    }
}
