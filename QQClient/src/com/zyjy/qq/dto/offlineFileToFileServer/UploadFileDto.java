package com.zyjy.qq.dto.offlineFileToFileServer;

import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.util.JsonUtil;

public class UploadFileDto {
    private String type = JsonUtil.UPLOAD_FILE;
    private SendFile sendFile;

    public UploadFileDto() {
    }

    public UploadFileDto( SendFile sendFile) {
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
