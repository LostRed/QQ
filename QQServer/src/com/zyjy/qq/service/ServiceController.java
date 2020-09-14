package com.zyjy.qq.service;

import com.zyjy.qq.service.offlineFile.DeleteSendFileService;
import com.zyjy.qq.service.offlineFile.DownloadFileService;
import com.zyjy.qq.service.offlineFile.AddSendFileService;
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
        SERVICE_MAP.put(JsonUtil.LOGIN, new LoginService());
        SERVICE_MAP.put(JsonUtil.LOGOFF, new LogoffService());
        SERVICE_MAP.put(JsonUtil.REGISTER, new RegisterService());
        SERVICE_MAP.put(JsonUtil.GROUP_FRIEND, new GroupFriendService());
        SERVICE_MAP.put(JsonUtil.HISTORY, new HistoryService());
        SERVICE_MAP.put(JsonUtil.CHAT, new ChatService());
        SERVICE_MAP.put(JsonUtil.SEND_FILE, new SendFileService());
        SERVICE_MAP.put(JsonUtil.ADD_SEND_FILE, new AddSendFileService());
        SERVICE_MAP.put(JsonUtil.DELETE_SEND_FILE, new DeleteSendFileService());
        SERVICE_MAP.put(JsonUtil.DOWNLOAD_FILE, new DownloadFileService());
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
