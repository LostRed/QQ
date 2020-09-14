package com.zyjy.qq.dao;

import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.pojo.User;

import java.util.List;

public interface InSendFileDao {
    /**
     * 根据文件信息添加离线文件信息
     *
     * @param sendFile 文件信息
     * @param fileId   文件id
     */
    void addSendFile(SendFile sendFile, int fileId);

    /**
     * 根据发送文件信息编号删除离线文件信息
     *
     * @param fileInfoId 离线文件信息编号
     */
    void deleteSendFile(int fileInfoId);

    /**
     * 根据接收文件的用户查询离线发送文件信息编号
     *
     * @param fileId 文件id
     * @return 文件信息id
     */
    int querySendFile(int fileId);

    /**
     * 根据接收文件的用户查询离线发送文件信息编号
     *
     * @param recvUser 接收文件的用户
     * @return 文件信息集合
     */
    List<SendFile> querySendFile(User recvUser);
}
