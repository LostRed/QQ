package com.zyjy.qq.view;

import com.zyjy.qq.controller.RegisterViewAction;
import com.zyjy.qq.pojo.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * 注册窗口
 */
public class RegisterView extends JFrame {
    private static final RegisterView REGISTER_VIEW = new RegisterView();
    private JTextField tfNike = new JTextField();
    private JPasswordField pfPwd = new JPasswordField();
    private JPasswordField pfPwdConfirm = new JPasswordField();
    private JComboBox<String> cbSex = new JComboBox<>(new String[]{"男", "女"});

    /**
     * 构造注册窗口
     */
    private RegisterView() {
        //初始化控件
        JPanel rootPanel = new JPanel();
        JPanel nikePanel = new JPanel();
        JPanel pwdPanel = new JPanel();
        JPanel PwdConfirmPanel = new JPanel();
        JPanel sexPanel = new JPanel();
        JPanel btnPanel = new JPanel();
        JLabel lbNike = new JLabel("请输入您的昵称：");
        JLabel lbPwd = new JLabel("请输入您的密码：");
        JLabel lbPwdConfirm = new JLabel("请重复输入密码：");
        JLabel lbSex = new JLabel("请选择您的性别：");
        JButton btnBack = new JButton("返回");
        JButton btnRegister = new JButton("注册");
        JButton btnClear = new JButton("清除");
        //设置控件
        nikePanel.setBorder(new EmptyBorder(5, 50, 5, 50));
        pwdPanel.setBorder(new EmptyBorder(5, 50, 5, 50));
        PwdConfirmPanel.setBorder(new EmptyBorder(5, 50, 5, 50));
        sexPanel.setBorder(new EmptyBorder(5, 50, 5, 50));
        btnPanel.setBorder(new EmptyBorder(5, 50, 5, 50));
        rootPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        tfNike.setPreferredSize(new Dimension(150, 30));
        pfPwd.setPreferredSize(new Dimension(150, 30));
        pfPwdConfirm.setPreferredSize(new Dimension(150, 30));
        nikePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        pwdPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        PwdConfirmPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        sexPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        rootPanel.setLayout(new GridLayout(5, 1));
        getRootPane().setDefaultButton(btnRegister);
        //添加控件
        nikePanel.add(lbNike);
        nikePanel.add(tfNike);
        pwdPanel.add(lbPwd);
        pwdPanel.add(pfPwd);
        PwdConfirmPanel.add(lbPwdConfirm);
        PwdConfirmPanel.add(pfPwdConfirm);
        sexPanel.add(lbSex);
        sexPanel.add(cbSex);
        btnPanel.add(btnBack);
        btnPanel.add(btnRegister);
        btnPanel.add(btnClear);
        rootPanel.add(nikePanel);
        rootPanel.add(pwdPanel);
        rootPanel.add(PwdConfirmPanel);
        rootPanel.add(sexPanel);
        rootPanel.add(btnPanel);
        add(rootPanel);
        //设置监听
        RegisterViewAction registerViewAction = new RegisterViewAction();
        btnBack.addActionListener(registerViewAction);
        btnRegister.addActionListener(registerViewAction);
        btnClear.addActionListener(registerViewAction);
        btnBack.setActionCommand("back");
        btnRegister.setActionCommand("register");
        btnClear.setActionCommand("clear");
        //窗口设置
        setTitle("注册");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * 显示注册结果
     *
     * @param user 注册的用户对象
     */
    public void showResult(User user) {
        if (user.getUserID() == 0) {
            JOptionPane.showMessageDialog(this, "注册失败！");
        } else {
            int userId = user.getUserID();
            JOptionPane.showMessageDialog(this, "注册成功！您的QQ号为：" + userId);
            this.setVisible(false);
            LoginView.getLoginView().setVisible(true);
        }
    }

    /**
     * 获取单例注册窗口
     *
     * @return 单例注册窗口
     */
    public static RegisterView getRegisterView() {
        return REGISTER_VIEW;
    }

    //get和set方法
    public JTextField getTfNike() {
        return tfNike;
    }

    public void setTfNike(JTextField tfNike) {
        this.tfNike = tfNike;
    }

    public JPasswordField getPfPwd() {
        return pfPwd;
    }

    public void setPfPwd(JPasswordField pfPwd) {
        this.pfPwd = pfPwd;
    }

    public JPasswordField getPfPwdConfirm() {
        return pfPwdConfirm;
    }

    public void setPfPwdConfirm(JPasswordField pfPwdConfirm) {
        this.pfPwdConfirm = pfPwdConfirm;
    }

    public JComboBox<String> getCbSex() {
        return cbSex;
    }

    public void setCbSex(JComboBox<String> cbSex) {
        this.cbSex = cbSex;
    }
}
