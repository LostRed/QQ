package com.zyjy.qq.controller;

import com.zyjy.qq.net.Client;
import com.zyjy.qq.view.FriendView;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;

/**
 * 好友窗口监听
 */
public class FriendViewWindow extends WindowAdapter {
    /**
     * 窗口关闭时的动作
     *
     * @param e 窗口事件
     */
    @Override
    public void windowClosing(WindowEvent e) {
        FriendView fv = FriendView.getFriendView();
        int choice = JOptionPane.showConfirmDialog(fv, "确定退出吗？", "提示", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.OK_OPTION) {
            Set<Integer> set = fv.getCvMap().keySet();
            for (int userId : set) {
                fv.getCvMap().get(userId).dispose();
            }
            Client.getClient().exit();
        }
    }
}
