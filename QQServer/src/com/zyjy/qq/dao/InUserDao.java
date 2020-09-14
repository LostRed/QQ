package com.zyjy.qq.dao;

import com.zyjy.qq.pojo.User;

/**
 * 用户信息数据库访问接口
 */
public interface InUserDao {
    /**
     * 根据输入的QQ号和密码查询用户
     *
     * @param inputUserId 输入的QQ号
     * @param inputPwd    输入的密码
     * @return 用户对象
     */
    User selectUser(int inputUserId, String inputPwd);

    /**
     * 根据输入的用户对象新增用户
     *
     * @param inputUser 输入的用户对象
     * @return 用户对象
     */
    User addUser(User inputUser);
}
