package com.zyjy.qq.dao;

import com.zyjy.qq.pojo.Group;
import com.zyjy.qq.pojo.User;

import java.util.List;
/**
 * 分组信息数据库访问接口
 */
public interface InGroupDao {
    /**
     * 根据用户对象该用户的分组信息对象
     *
     * @param user 用户对象
     * @return 分组信息对象
     */
    List<Group> queryGroup(User user);
}
