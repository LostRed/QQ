package com.zyjy.qq.controller;

import com.zyjy.qq.dto.LoginDto;
import com.zyjy.qq.net.Client;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.JsonUtil;
import com.zyjy.qq.view.LoginView;
import com.zyjy.qq.view.RegisterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 登录窗口动作监听
 */
public class LoginViewAction implements ActionListener {
    /**
     * 根据动作命令执行任务
     *
     * @param e 动作事件
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        LoginView lv = LoginView.getLoginView();
        RegisterView rv = RegisterView.getRegisterView();
        switch (command) {
            case "toRegister":
                lv.setVisible(false);
                rv.setVisible(true);
                break;
            case "login":
                String userId = lv.getTfUserId().getText();
                String password = new String(lv.getPfPwd().getPassword());
                if (userId.equals("")) {
                    JOptionPane.showMessageDialog(lv, "请输入用户名！");
                    return;
                } else if (password.equals("")) {
                    JOptionPane.showMessageDialog(lv, "请输入密码！");
                    return;
                }
                Client client = Client.getClient();
                if (!client.isConnected()) {
                    Properties prop = new Properties();
                    try {
                        prop.load(new BufferedReader(new FileReader("config/config.properties")));
                        String host = prop.getProperty("host");
                        String port = prop.getProperty("port");
                        client.connectToServer(host, port);
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(lv, "无法连接服务器！");
                        return;
                    }
                }
                User user = new User(Integer.parseInt(userId), null, password, null);
                LoginDto loginDto = new LoginDto(user);
                client.sendMessage(JsonUtil.toJsonString(loginDto));
                break;
            case "clear":
                lv.getTfUserId().setText("");
                lv.getPfPwd().setText("");
                break;
            case "autoLogin":
                if (lv.getCbAutoLogin().isSelected() && !lv.getCbRemember().isSelected()) {
                    lv.getCbRemember().setSelected(true);
                }
                break;
        }
    }
}
