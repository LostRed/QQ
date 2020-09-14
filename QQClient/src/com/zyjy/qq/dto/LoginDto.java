package com.zyjy.qq.dto;

import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.JsonUtil;

public class LoginDto {
    private String type = JsonUtil.LOGIN;
    private User user;

    public LoginDto() {
    }

    public LoginDto(User user) {
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
