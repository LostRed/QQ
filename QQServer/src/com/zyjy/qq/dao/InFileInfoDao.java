package com.zyjy.qq.dao;

import com.zyjy.qq.pojo.SendFile;

public interface InFileInfoDao {
    /**
     * 根据文件信息添加离线文件信息
     *
     * @param sendFile 文件信息
     * @return 受影响的记录数
     */
    int addFileInfo(SendFile sendFile);

    /**
     * 根据MD5查询文件
     *
     * @param MD5 文件的MD5
     * @return 文件id
     */
    int queryFileInfo(String MD5);
}
