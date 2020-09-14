package com.zyjy.qq.controller;

import com.zyjy.qq.dto.RegisterDto;
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
 * 注册窗口动作监听
 */
public class RegisterViewAction implements ActionListener {
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
            case "back":
                rv.setVisible(false);
                rv.getTfNike().setText("");
                rv.getPfPwd().setText("");
                rv.getPfPwdConfirm().setText("");
                rv.getCbSex().setSelectedIndex(0);
                lv.setVisible(true);
                break;
            case "register":
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
                String nike = rv.getTfNike().getText();
                String pwd = new String(rv.getPfPwd().getPassword());
                String pwdConfirm = new String(rv.getPfPwdConfirm().getPassword());
                String sex = null;
                if (nike.equals("")) {
                    JOptionPane.showMessageDialog(lv, "请输入用户名！");
                    return;
                } else if (pwd.equals("")) {
                    JOptionPane.showMessageDialog(lv, "请输入密码！");
                    return;
                } else if (pwdConfirm.equals("")) {
                    JOptionPane.showMessageDialog(lv, "请输入重复密码！");
                    return;
                } else if (!pwd.equals(pwdConfirm)) {
                    JOptionPane.showMessageDialog(lv, "两次输入的密码不同！");
                    return;
                } else if (rv.getCbSex().getSelectedItem() != null) {
                    sex = rv.getCbSex().getSelectedItem().toString();
                }
                User user = new User(0, nike, pwd, sex);
                RegisterDto registerDto = new RegisterDto(user);
                client.sendMessage(JsonUtil.toJsonString(registerDto));
                break;
            case "clear":
                rv.getTfNike().setText("");
                rv.getPfPwd().setText("");
                rv.getPfPwdConfirm().setText("");
                rv.getCbSex().setSelectedIndex(0);
                break;
        }
    }
}
