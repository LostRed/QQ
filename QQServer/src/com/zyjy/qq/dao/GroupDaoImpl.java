package com.zyjy.qq.dao;

import com.zyjy.qq.pojo.Group;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 分组信息数据库访问
 */
public class GroupDaoImpl implements InGroupDao {
    /**
     * 根据用户对象该用户的分组信息对象
     *
     * @param user 用户对象
     * @return 分组信息对象
     */
    @Override
    public List<Group> queryGroup(User user) {
        String sql = "SELECT * FROM T_GROUP WHERE USER_ID = ?";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Group> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserID());
            rs = ps.executeQuery();
            while (rs.next()) {
                int groupId = rs.getInt("ID");
                String groupName = rs.getString("NAME");
                Group group = new Group(groupId, user, groupName);
                list.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(ps, rs);
        }
        return list;
    }
}
