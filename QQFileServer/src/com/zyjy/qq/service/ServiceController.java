package com.zyjy.qq.service;

import com.zyjy.qq.util.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务控制器
 */
public class ServiceController {
    /**
     * 业务集合
     */
    private static final Map<String, InService> SERVICE_MAP = new HashMap<>();

    static {
        SERVICE_MAP.put(JsonUtil.UPLOAD_FILE, new UploadFileService());
        SERVICE_MAP.put(JsonUtil.DOWNLOAD_FILE, new DownFileService());
    }

    /**
     * 获取业务对象
     *
     * @param type 英文类型
     * @return 业务对象
     */
    public static InService getService(String type) {
        return SERVICE_MAP.get(type);
    }
}
