package com.zyjy.qq.dto;

import com.zyjy.qq.pojo.GroupFriend;
import com.zyjy.qq.service.ServiceController;
import com.zyjy.qq.util.JsonUtil;

import java.util.List;

public class QueryGroupFriendResponse {
    private String type = JsonUtil.GROUP_FRIEND;
    private List<GroupFriend> groupFriendList;

    public QueryGroupFriendResponse() {
    }

    public QueryGroupFriendResponse(List<GroupFriend> groupFriendList) {
        this.groupFriendList = groupFriendList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<GroupFriend> getGroupFriendList() {
        return groupFriendList;
    }

    public void setGroupFriendList(List<GroupFriend> groupFriendList) {
        this.groupFriendList = groupFriendList;
    }
}
