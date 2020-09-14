package com.zyjy.qq.dao;

import com.zyjy.qq.pojo.Chat;
import com.zyjy.qq.pojo.User;

import java.util.List;

/**
 * 聊天记录信息数据库访问接口
 */
public interface InChatDao {
    /**
     * 根据用户及对方用户查询聊天记录
     *
     * @param user      本用户
     * @param otherUser 对方用户
     * @return 聊天记录信息集合
     */
    List<Chat> queryChat(User user, User otherUser);

    /**
     * 根据聊天信息对象新增聊天信息
     *
     * @param chat 聊天信息对象
     * @return 受影响的记录数
     */
    int addChat(Chat chat);
}
