package com.zyjy.qq.pojo;

public class GroupFriend {
    private int groupFriendId;
    private Group group;
    private User user;

    public GroupFriend() {
    }

    public GroupFriend(int groupFriendId, Group group, User user) {
        this.groupFriendId = groupFriendId;
        this.group = group;
        this.user = user;
    }

    public int getGroupFriendId() {
        return groupFriendId;
    }

    public void setGroupFriendId(int groupFriendId) {
        this.groupFriendId = groupFriendId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
