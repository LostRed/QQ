package com.zyjy.qq.dao;

import com.zyjy.qq.pojo.Group;
import com.zyjy.qq.pojo.GroupFriend;
import com.zyjy.qq.pojo.User;

import java.util.List;

/**
 * 分组好友数据库访问接口
 */
public interface InGroupFriendDao {
    /**
     * 根据分组信息查询分组好友信息
     *
     * @param group 分组信息对象
     * @return 分组好友信息对象
     */
    List<GroupFriend> queryGroupFriend(Group group);

    /**
     * 根据用户对象查询分组好友信息
     *
     * @param user 用户对象
     * @return 分组好友信息对象
     */
    List<GroupFriend> queryGroupFriend(User user);
}
