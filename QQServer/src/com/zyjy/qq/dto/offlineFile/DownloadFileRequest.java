package com.zyjy.qq.dto.offlineFile;

import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.JsonUtil;

public class DownloadFileRequest {
    private String type = JsonUtil.DOWNLOAD_FILE;
    private User user;

    public DownloadFileRequest() {
    }

    public DownloadFileRequest(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
