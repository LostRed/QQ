package com.zyjy.qq.dto;

import com.zyjy.qq.pojo.Chat;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.JsonUtil;

import java.util.List;

public class QueryHistoryResponse {
    private String type = JsonUtil.HISTORY;
    private User sendUser;
    private User recvUser;
    private List<Chat> chatList;

    public QueryHistoryResponse() {
    }

    public QueryHistoryResponse(User sendUser, User recvUser, List<Chat> chatList) {
        this.sendUser = sendUser;
        this.recvUser = recvUser;
        this.chatList = chatList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }
}
