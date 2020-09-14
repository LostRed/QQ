package com.zyjy.qq.dao;

import com.zyjy.qq.pojo.Chat;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 聊天记录信息数据库访问
 */
public class ChatDaoImpl implements InChatDao {
    /**
     * 根据用户及对方用户查询聊天记录
     *
     * @param user      本用户
     * @param otherUser 对方用户
     * @return 聊天记录信息集合
     */
    @Override
    public List<Chat> queryChat(User user, User otherUser) {
        String sql = "SELECT * FROM V_CHAT WHERE (SEND_ID = ? AND RECV_ID = ?) OR (SEND_ID = ? AND RECV_ID = ?)";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Chat> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserID());
            ps.setInt(2, otherUser.getUserID());
            ps.setInt(3, otherUser.getUserID());
            ps.setInt(4, user.getUserID());
            rs = ps.executeQuery();
            while (rs.next()) {
                int chatId = rs.getInt("ID");
                int sendUserId = rs.getInt("SEND_ID");
                String sendNike = rs.getString("SEND_NIKE");
                int recvUserId = rs.getInt("RECV_ID");
                String recvNike = rs.getString("RECV_NIKE");
                String message = rs.getString("MSG");
                String time = rs.getString("TIME");
                User sendUser = new User(sendUserId, sendNike, null, null);
                User recvUser = new User(recvUserId, recvNike, null, null);
                Chat chat = new Chat(chatId, sendUser, recvUser, message, time);
                list.add(chat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(ps, rs);
        }
        return list;
    }

    /**
     * 根据聊天信息对象新增聊天信息
     *
     * @param chat 聊天信息对象
     * @return 受影响的记录数
     */
    @Override
    public int addChat(Chat chat) {
        String sql = "INSERT INTO T_CHAT (SEND_ID,RECV_ID,MSG) VALUES (?,?,?)";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        int num = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, chat.getSendUser().getUserID());
            ps.setInt(2, chat.getRecvUser().getUserID());
            ps.setString(3, chat.getMessage());
            num += ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(ps, null);
        }
        return num;
    }
}
