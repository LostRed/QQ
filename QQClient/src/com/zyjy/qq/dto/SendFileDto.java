package com.zyjy.qq.dto;

import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.util.JsonUtil;

public class SendFileDto {
    private String type = JsonUtil.SEND_FILE;
    private String result;
    private SendFile sendFile;

    public SendFileDto() {
    }

    public SendFileDto(String result, SendFile sendFile) {
        this.result = result;
        this.sendFile = sendFile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public SendFile getFileInfo() {
        return sendFile;
    }

    public void setFileInfo(SendFile sendFile) {
        this.sendFile = sendFile;
    }
}
