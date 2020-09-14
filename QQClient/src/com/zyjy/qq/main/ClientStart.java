package com.zyjy.qq.main;

import com.zyjy.qq.dto.LoginDto;
import com.zyjy.qq.net.Client;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.ConfigUtil;
import com.zyjy.qq.view.LoginView;
import net.sf.json.JSONObject;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.IOException;
import java.util.Enumeration;

public class ClientStart {
    public static void main(String[] args) {
        initGlobalFontSetting(new Font("微软雅黑", Font.PLAIN, 13));
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        LoginView lv = LoginView.getLoginView();
        lv.setVisible(true);
        if (ConfigUtil.readConfig()) {
            String host = ConfigUtil.getHost();
            String port = ConfigUtil.getPort();
            Client client = Client.getClient();
            try {
                client.connectToServer(host, port);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(lv, "无法连接服务器！");
                return;
            }
            String userId = lv.getTfUserId().getText();
            String password = new String(lv.getPfPwd().getPassword());
            User user = new User(Integer.parseInt(userId), null, password, null);
            LoginDto loginDto = new LoginDto(user);
            JSONObject jsonObject = JSONObject.fromObject(loginDto);
            client.sendMessage(jsonObject.toString());
        }
    }

    public static void initGlobalFontSetting(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }
}
