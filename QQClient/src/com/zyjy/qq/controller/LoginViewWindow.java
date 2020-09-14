package com.zyjy.qq.controller;

import com.zyjy.qq.net.Client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 登录窗口监听
 */
public class LoginViewWindow extends WindowAdapter {
    /**
     * 窗口关闭时的动作
     *
     * @param e 窗口事件
     */
    @Override
    public void windowClosing(WindowEvent e) {
        Client.getClient().exit();
    }
}
