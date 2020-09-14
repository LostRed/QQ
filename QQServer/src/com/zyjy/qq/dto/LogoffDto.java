package com.zyjy.qq.dto;

import com.zyjy.qq.pojo.User;
import com.zyjy.qq.service.ServiceController;
import com.zyjy.qq.util.JsonUtil;

public class LogoffDto {
    private String type = JsonUtil.LOGOFF;
    private String result;
    private User user;

    public LogoffDto() {
    }

    public LogoffDto(String result, User user) {
        this.result = result;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
