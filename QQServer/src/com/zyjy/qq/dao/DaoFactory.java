package com.zyjy.qq.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * DAO工厂
 */
public class DaoFactory {
    /**
     * DAO接口集合
     */
    private static final Map<String, Object> DAO_MAP = new HashMap<>();

    static {
        try {
            Object obj = Class.forName("com.zyjy.qq.dao.UserDaoImpl").newInstance();
            DAO_MAP.put("InUserDao", obj);
            obj = Class.forName("com.zyjy.qq.dao.GroupDaoImpl").newInstance();
            DAO_MAP.put("InGroupDao", obj);
            obj = Class.forName("com.zyjy.qq.dao.GroupFriendDaoImpl").newInstance();
            DAO_MAP.put("InGroupFriendDao", obj);
            obj = Class.forName("com.zyjy.qq.dao.ChatDaoImpl").newInstance();
            DAO_MAP.put("InChatDao", obj);
            obj = Class.forName("com.zyjy.qq.dao.FileInfoDaoImpl").newInstance();
            DAO_MAP.put("InFileInfoDao", obj);
            obj = Class.forName("com.zyjy.qq.dao.SendFileDaoImpl").newInstance();
            DAO_MAP.put("InSendFileDao", obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取DAO接口
     *
     * @param daoName 接口名
     * @return DAO接口对象
     */
    public static Object getDao(String daoName) {
        return DAO_MAP.get(daoName);
    }
}
