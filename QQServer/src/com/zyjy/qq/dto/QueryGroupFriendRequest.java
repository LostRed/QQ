package com.zyjy.qq.dto;

import com.zyjy.qq.pojo.User;
import com.zyjy.qq.service.ServiceController;
import com.zyjy.qq.util.JsonUtil;

public class QueryGroupFriendRequest {
    private String type = JsonUtil.GROUP_FRIEND;
    private User user;

    public QueryGroupFriendRequest() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public QueryGroupFriendRequest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
