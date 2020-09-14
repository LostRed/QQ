package com.zyjy.qq.dto;

import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.JsonUtil;

public class QueryHistoryRequest {
    private String type = JsonUtil.HISTORY;
    private User sendUser;
    private User recvUser;

    public QueryHistoryRequest() {
    }

    public QueryHistoryRequest(User sendUser, User recvUser) {
        this.sendUser = sendUser;
        this.recvUser = recvUser;
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
}
