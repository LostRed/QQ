package com.zyjy.qq.dto.offlineFile;

import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.util.JsonUtil;

public class AddSendFileDto {
    private String type = JsonUtil.ADD_SEND_FILE;
    private SendFile sendFile;

    public AddSendFileDto() {
    }

    public AddSendFileDto(SendFile sendFile) {
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
