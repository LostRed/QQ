package com.zyjy.qq.controller;

import com.zyjy.qq.pojo.User;
import com.zyjy.qq.view.FriendView;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 好友窗口列表鼠标监听
 */
public class FriendViewMouse extends MouseAdapter {
    /**
     * 好友列表树
     */
    private final JTree tree;

    /**
     * 构造好友窗口列表鼠标监听
     *
     * @param tree 好友列表树
     */
    public FriendViewMouse(JTree tree) {
        this.tree = tree;
    }

    /**
     * 鼠标点击到叶节点且双击时
     *
     * @param e 鼠标事件
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedRow = tree.getRowForLocation(e.getX(), e.getY());
        if (selectedRow != -1) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (node.isLeaf() && e.getClickCount() == 2) {
                if (node.getUserObject() instanceof User) {
                    User recvUser = (User) node.getUserObject();
                    FriendView.getFriendView().ejectChatView(recvUser);
                }
            }
        }
    }
}
