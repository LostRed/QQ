package com.zyjy.qq.dto;

import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.util.JsonUtil;

public class DownloadFileDto {
    private String type = JsonUtil.DOWNLOAD_FILE;
    private SendFile sendFile;

    public DownloadFileDto() {
    }

    public DownloadFileDto(SendFile sendFile) {
        this.sendFile = sendFile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SendFile getSendFile() {
        return sendFile;
    }

    public void setSendFile(SendFile sendFile) {
        this.sendFile = sendFile;
    }
}
