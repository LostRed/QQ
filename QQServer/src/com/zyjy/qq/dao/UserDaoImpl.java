package com.zyjy.qq.dao;

import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户信息数据库访问
 */
public class UserDaoImpl implements InUserDao {
    /**
     * 根据输入的QQ号和密码查询用户
     *
     * @param inputUserId 输入的QQ号
     * @param inputPwd    输入的密码
     * @return 用户对象
     */
    @Override
    public User selectUser(int inputUserId, String inputPwd) {
        String sql = "SELECT * FROM T_USER WHERE ID = ? AND PASSWORD = ?";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        User user = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, inputUserId);
            ps.setString(2, inputPwd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("ID");
                String nike = rs.getString("NIKE");
                String password = rs.getString("PASSWORD");
                String sex = rs.getString("SEX");
                user = new User(id, nike, password, sex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(ps, null);
        }
        return user;
    }

    /**
     * 根据输入的用户对象新增用户
     *
     * @param inputUser 输入的用户对象
     * @return 用户对象
     */
    @Override
    public User addUser(User inputUser) {
        String sql = "INSERT INTO T_USER VALUES(NULL,?,?,?)";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, inputUser.getNike());
            ps.setString(2, inputUser.getPassword());
            ps.setString(3, inputUser.getSex());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int userId = rs.getInt(1);
                inputUser.setUserID(userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(ps, rs);
        }
        return inputUser;
    }
}
