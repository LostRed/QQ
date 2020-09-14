package com.zyjy.qq.dao;

import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileInfoDaoImpl implements InFileInfoDao {
    @Override
    public int addFileInfo(SendFile sendFile) {
        String sql = "INSERT INTO T_FILEINFO VALUES (NULL,?,?,?,?,?)";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int fileId = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, sendFile.getFileInfo().getFileName());
            ps.setLong(2, sendFile.getFileInfo().getFileSize());
            ps.setString(3, sendFile.getFileInfo().getMD5());
            ps.setString(4, sendFile.getFileInfo().getHost());
            ps.setInt(5, sendFile.getFileInfo().getPort());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                fileId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(ps, rs);
        }
        return fileId;
    }

    @Override
    public int queryFileInfo(String MD5) {
        String sql = "SELECT * FROM T_FILEINFO WHERE MD5 = ?";
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int fileId = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, MD5);
            rs = ps.executeQuery();
            if (rs.next()) {
                fileId = rs.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.release(ps, rs);
        }
        return fileId;
    }
}
