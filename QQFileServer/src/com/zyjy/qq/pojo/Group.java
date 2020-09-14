package com.zyjy.qq.pojo;

public class Group {
    private int groupId;
    private User user;
    private String groupName;

    public Group() {
    }

    public Group(int GroupId, User user, String groupName) {
        this.groupId = GroupId;
        this.user = user;
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
