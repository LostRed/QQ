package com.zyjy.qq.dao;

import com.zyjy.qq.pojo.Group;
import com.zyjy.qq.pojo.GroupFriend;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 分组好友数据库访问
 */
public class GroupFriendDaoImpl implements InGroupFriendDao {
    /**
     * 根据分组信息查询分组好友信息
     *
     * @param group 分组信息对象
     * @return 分组好友信息对象
     */
    @Override
    public List<GroupFriend> queryGroupFriend(Group group) {
        String sql = "SELECT * FROM V_GROUP_FRIEND WHERE GROUP_ID = ?";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<GroupFriend> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, group.getGroupId());
            rs = ps.executeQuery();
            while (rs.next()) {
                int groupFriendId = rs.getInt("GROUP_FRIEND_ID");
                int userId = rs.getInt("FRIEND_ID");
                String nike = rs.getString("FRIEND_NIKE");
                String password = rs.getString("FRIEND_PASSWORD");
                String sex = rs.getString("FRIEND_SEX");
                User user = new User(userId, nike, password, sex);
                GroupFriend groupFriend = new GroupFriend(groupFriendId, group, user);
                list.add(groupFriend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(ps, rs);
        }
        return list;
    }

    /**
     * 根据用户对象查询分组好友信息
     *
     * @param user 用户对象
     * @return 分组好友信息对象
     */
    @Override
    public List<GroupFriend> queryGroupFriend(User user) {
        String sql = "SELECT * FROM V_GROUP_FRIEND WHERE USER_ID = ?";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<GroupFriend> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserID());
            rs = ps.executeQuery();
            while (rs.next()) {
                int groupFriendId = rs.getInt("GROUP_FRIEND_ID");
                int userId = rs.getInt("FRIEND_ID");
                String nike = rs.getString("FRIEND_NIKE");
                String password = rs.getString("FRIEND_PASSWORD");
                String sex = rs.getString("FRIEND_SEX");
                int groupId = rs.getInt("GROUP_ID");
                String groupName = rs.getString("GROUP_NAME");
                Group group = new Group(groupId, user, groupName);
                User friendUser = new User(userId, nike, password, sex);
                GroupFriend groupFriend = new GroupFriend(groupFriendId, group, friendUser);
                list.add(groupFriend);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(ps, rs);
        }
        return list;
    }
}
