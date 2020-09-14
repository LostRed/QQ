package com.zyjy.qq.util;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 配置
 */
public class ConfigUtil {
    /**
     * 获取文件服务器ip
     *
     * @return 文件服务器ip
     */
    public static String getFileServerHost() {
        String host = null;
        Properties prop = new Properties();
        try {
            prop.load(new BufferedReader(new FileReader("data/fileServer.properties")));
            host = prop.getProperty("fileServerHost");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return host;
    }

    /**
     * 获取文件服务器端口
     *
     * @return 文件服务器端口
     */
    public static String getFileServerPort() {
        String port = null;
        Properties prop = new Properties();
        try {
            prop.load(new BufferedReader(new FileReader("data/fileServer.properties")));
            port = prop.getProperty("fileServerPort");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return port;
    }
}
