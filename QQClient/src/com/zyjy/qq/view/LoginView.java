package com.zyjy.qq.view;

import com.zyjy.qq.controller.LoginViewAction;
import com.zyjy.qq.controller.LoginViewWindow;
import com.zyjy.qq.pojo.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * 登录窗口
 */
public class LoginView extends JFrame {
    private static final LoginView LOGIN_VIEW = new LoginView();
    private JTextField tfUserId = new JTextField();
    private JPasswordField pfPwd = new JPasswordField();
    private JCheckBox cbAutoLogin = new JCheckBox("自动登录");
    private JCheckBox cbRemember = new JCheckBox("记住密码");

    /**
     * 构造登录窗口
     */
    private LoginView() {
        //初始化控件
        JPanel topPanel = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel accPanel = new JPanel();
        JPanel pwdPanel = new JPanel();
        JPanel checkBoxPanel = new JPanel();
        JPanel btnPanel = new JPanel();
        JLabel lbUserId = new JLabel("QQ号：", JLabel.RIGHT);
        JLabel lbPwd = new JLabel("密码：", JLabel.RIGHT);
        JButton btnToRegister = new JButton("注册");
        JButton btnLogin = new JButton("登录");
        JButton btnClear = new JButton("清除");
        //设置控件
        mainPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
        btnPanel.setBorder(new EmptyBorder(0, 0, 15, 0));
        topPanel.setPreferredSize(new Dimension(400, 140));
        lbUserId.setPreferredSize(new Dimension(50, 30));
        lbPwd.setPreferredSize(new Dimension(50, 30));
        tfUserId.setPreferredSize(new Dimension(150, 30));
        pfPwd.setPreferredSize(new Dimension(150, 30));
        topPanel.setBackground(new Color(50, 100, 50));
        mainPanel.setLayout(new BorderLayout());
        getRootPane().setDefaultButton(btnLogin);
        //添加控件
        accPanel.add(lbUserId);
        accPanel.add(tfUserId);
        pwdPanel.add(lbPwd);
        pwdPanel.add(pfPwd);
        checkBoxPanel.add(cbAutoLogin);
        checkBoxPanel.add(cbRemember);
        mainPanel.add(accPanel, BorderLayout.NORTH);
        mainPanel.add(pwdPanel);
        mainPanel.add(checkBoxPanel, BorderLayout.SOUTH);
        btnPanel.add(btnToRegister);
        btnPanel.add(btnLogin);
        btnPanel.add(btnClear);
        add(topPanel, BorderLayout.NORTH);
        add(mainPanel);
        add(btnPanel, BorderLayout.SOUTH);
        //添加监听
        LoginViewAction loginViewAction = new LoginViewAction();
        LoginViewWindow loginViewWindow = new LoginViewWindow();
        btnToRegister.addActionListener(loginViewAction);
        btnLogin.addActionListener(loginViewAction);
        btnClear.addActionListener(loginViewAction);
        cbAutoLogin.addActionListener(loginViewAction);
        btnToRegister.setActionCommand("toRegister");
        btnLogin.setActionCommand("login");
        btnClear.setActionCommand("clear");
        cbAutoLogin.setActionCommand("autoLogin");
        addWindowListener(loginViewWindow);
        //窗口设置
        setTitle("登录");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * 跳转至好友窗口
     *
     * @param user 登录的用户对象
     */
    public void toFriendView(User user) {
        if (user == null) {
            JOptionPane.showMessageDialog(this, "用户名或密码错误！");
        } else {
            JOptionPane.showMessageDialog(this, "登录成功！");
            this.dispose();
            FriendView.getFriendView().setUser(user);
            FriendView.getFriendView().setTitle(user.getNike());
            FriendView.getFriendView().setVisible(true);
        }
    }

    /**
     * 获取单例登录窗口
     *
     * @return 单例登录窗口
     */
    public static LoginView getLoginView() {
        return LOGIN_VIEW;
    }

    //get和set方法
    public JTextField getTfUserId() {
        return tfUserId;
    }

    public void setTfUserId(JTextField tfUserId) {
        this.tfUserId = tfUserId;
    }

    public JPasswordField getPfPwd() {
        return pfPwd;
    }

    public void setPfPwd(JPasswordField pfPwd) {
        this.pfPwd = pfPwd;
    }

    public JCheckBox getCbRemember() {
        return cbRemember;
    }

    public void setCbRemember(JCheckBox cbRemember) {
        this.cbRemember = cbRemember;
    }

    public JCheckBox getCbAutoLogin() {
        return cbAutoLogin;
    }

    public void setCbAutoLogin(JCheckBox cbAutoLogin) {
        this.cbAutoLogin = cbAutoLogin;
    }
}
