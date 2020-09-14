package com.zyjy.qq.dto;

import com.zyjy.qq.pojo.User;
import com.zyjy.qq.service.ServiceController;
import com.zyjy.qq.util.JsonUtil;

public class ChatDto {
    private String type = JsonUtil.CHAT;
    private String result;
    private User sendUser;
    private User recvUser;
    private String message;

    public ChatDto() {
    }

    public ChatDto(String result, User sendUser, User recvUser, String message) {
        this.result = result;
        this.sendUser = sendUser;
        this.recvUser = recvUser;
        this.message = message;
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

    public User getSendUser() {
        return sendUser;
    }

    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }

    public User getRecvUser() {
        return recvUser;
    }

    public void setRecvUser(User recvUser) {
        this.recvUser = recvUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
