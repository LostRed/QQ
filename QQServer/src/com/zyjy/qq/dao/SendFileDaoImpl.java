package com.zyjy.qq.dao;

import com.zyjy.qq.pojo.FileInfo;
import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SendFileDaoImpl implements InSendFileDao {
    @Override
    public void addSendFile(SendFile sendFile, int fileId) {
        String sql = "INSERT INTO T_SENDFILE (SEND_ID,RECV_ID,FILE_ID) VALUES (?,?,?)";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, sendFile.getSendUser().getUserID());
            ps.setInt(2, sendFile.getRecvUser().getUserID());
            ps.setInt(3, fileId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(ps, null);
        }
    }

    @Override
    public void deleteSendFile(int fileInfoId) {
        String sql = "DELETE FROM T_SENDFILE WHERE ID = ?";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, fileInfoId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(ps, null);
        }
    }

    @Override
    public int querySendFile(int fileId) {
        String sql = "SELECT * FROM T_SENDFILE WHERE FILE_ID = ?";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        int fileInfoId = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, fileId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                fileInfoId = rs.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(ps, null);
        }
        return fileInfoId;
    }

    @Override
    public List<SendFile> querySendFile(User recvUser) {
        String sql = "SELECT * FROM V_SEND_FILE WHERE RECV_ID = ?";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        List<SendFile> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, recvUser.getUserID());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int fileInfoId = rs.getInt("ID");
                int sendId = rs.getInt("SEND_ID");
                String sendNike = rs.getString("SEND_NIKE");
                int fileId = rs.getInt("FILE_ID");
                String fileName = rs.getString("FILE_NAME");
                long fileSize = rs.getLong("FILE_SIZE");
                String MD5 = rs.getString("MD5");
                String host = rs.getString("host");
                int port = rs.getInt("port");
                User sendUser = new User(sendId, sendNike, null, null);
                FileInfo fileInfo = new FileInfo(fileId, fileName, null, fileSize, MD5, host, port);
                SendFile sendFile = new SendFile(fileInfoId, sendUser, recvUser, fileInfo, null);
                list.add(sendFile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(ps, null);
        }
        return list;
    }
}
