package com.zyjy.qq.view;

import com.zyjy.qq.controller.FriendViewMouse;
import com.zyjy.qq.controller.FriendViewWindow;
import com.zyjy.qq.pojo.Group;
import com.zyjy.qq.pojo.GroupFriend;
import com.zyjy.qq.pojo.User;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 好友窗口
 */
public class FriendView extends JFrame {
    private static FriendView FRIEND_VIEW;
    private final Map<Integer, ChatView> cvMap = new HashMap<>();
    private JTree tree = new JTree();
    private User user;

    /**
     * 构造好友窗口
     */
    public FriendView() {
        //初始化控件
        JScrollPane friendPanel = new JScrollPane();
        //设置控件
        friendPanel.setPreferredSize(new Dimension(300, 400));
        //添加控件
        friendPanel.setViewportView(tree);
        add(friendPanel);
        //添加监听
        FriendViewMouse friendViewMouse = new FriendViewMouse(tree);
        FriendViewWindow friendViewWindow = new FriendViewWindow();
        tree.addMouseListener(friendViewMouse);
        addWindowListener(friendViewWindow);
        //窗口设置
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * 获取单例好友窗口
     *
     * @return 单例好友窗口
     */
    public static FriendView getFriendView() {
        if (FRIEND_VIEW == null) {
            FRIEND_VIEW = new FriendView();
        }
        return FRIEND_VIEW;
    }

    /**
     * 将用户的好友导入界面
     */
    public void importFriendList(List<GroupFriend> list) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("我的好友");
        DefaultTreeModel model = new DefaultTreeModel(rootNode);
        for (GroupFriend groupFriend : list) {
            boolean isExistGroup = false;
            Group group = groupFriend.getGroup();
            DefaultMutableTreeNode groupNode;
            Enumeration<?> e = rootNode.children();
            while (e.hasMoreElements()) {
                groupNode = (DefaultMutableTreeNode) e.nextElement();
                Group existGroup = (Group) groupNode.getUserObject();
                if (group.getGroupName().equals(existGroup.getGroupName())) {
                    User user = groupFriend.getUser();
                    DefaultMutableTreeNode userLeaf = new DefaultMutableTreeNode(user);
                    groupNode.add(userLeaf);
                    isExistGroup = true;
                    break;
                }
            }
            if (!isExistGroup) {
                groupNode = new DefaultMutableTreeNode(group);
                rootNode.add(groupNode);
                User user = groupFriend.getUser();
                DefaultMutableTreeNode userLeaf = new DefaultMutableTreeNode(user);
                groupNode.add(userLeaf);
            }
        }
        tree.setModel(model);
    }

    /**
     * 弹出聊天窗口
     *
     * @param user 对方用户对象
     * @return 聊天窗口
     */
    public ChatView ejectChatView(User user) {
        //聊天窗口集合中存在选中好友的窗口时
        ChatView cv;
        if (cvMap.get(user.getUserID()) != null) {
            cv = cvMap.get(user.getUserID());
        }
        //聊天窗口集合中未存在选中好友的窗口时
        else {
            cv = new ChatView(user);
            cvMap.put(user.getUserID(), cv);
        }
        cv.setVisible(true);
        cv.setState(JFrame.NORMAL);
        return cv;
    }

    //get和set方法
    public Map<Integer, ChatView> getCvMap() {
        return cvMap;
    }

    public JTree getTree() {
        return tree;
    }

    public void setTree(JTree tree) {
        this.tree = tree;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
