package com.zyjy.qq.util;

import com.zyjy.qq.view.LoginView;

import java.io.*;
import java.util.Properties;

/**
 * 配置
 */
public class ConfigUtil {
    /**
     * 写入配置文件
     */
    public static void writeConfig() {
        LoginView lv = LoginView.getLoginView();
        Properties prop = new Properties();
        try {
            if (lv.getCbRemember().isSelected()) {
                String userId = lv.getTfUserId().getText();
                String password = new String(lv.getPfPwd().getPassword());
                prop.put("remember", "yes");
                prop.put("userId", userId);
                prop.put("password", password);
            } else {
                prop.put("remember", "no");
            }
            if (lv.getCbAutoLogin().isSelected()) {
                prop.put("autoLogin", "yes");
            } else {
                prop.put("autoLogin", "no");
            }
            prop.put("host", "127.0.0.1");
            prop.put("port", "10080");
            OutputStream out = new FileOutputStream("data/config.properties");
            prop.store(out, "Copyright(c) by LostRed");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * 读取配置文件
     *
     * @return 是否自动登录
     */
    public static boolean readConfig() {
        LoginView lv = LoginView.getLoginView();
        Properties prop = new Properties();
        try {
            prop.load(new BufferedReader(new FileReader("data/config.properties")));
            String remember = prop.getProperty("remember");
            String autoLogin = prop.getProperty("autoLogin");
            String userId = prop.getProperty("userId");
            String password = prop.getProperty("password");
            if (remember.equals("yes")) {
                lv.getCbRemember().setSelected(true);
                lv.getTfUserId().setText(userId);
                lv.getPfPwd().setText(password);
            }
            if (autoLogin.equals("yes")) {
                lv.getCbAutoLogin().setSelected(true);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取服务器ip
     *
     * @return 服务器ip
     */
    public static String getHost() {
        String host = null;
        Properties prop = new Properties();
        try {
            prop.load(new BufferedReader(new FileReader("data/config.properties")));
            host = prop.getProperty("host");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return host;
    }

    /**
     * 获取服务器端口
     *
     * @return 服务器端口
     */
    public static String getPort() {
        String port = null;
        Properties prop = new Properties();
        try {
            prop.load(new BufferedReader(new FileReader("data/config.properties")));
            port = prop.getProperty("port");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return port;
    }
}
