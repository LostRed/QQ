package com.zyjy.qq.view.panel;

import javax.swing.*;
import java.awt.*;

/**
 * 输入框面板
 */
public class InputPanel extends JPanel {
    private JTextArea taInputArea = new JTextArea();
    private JButton btnClose = new JButton("关闭");
    private JButton btnSend = new JButton("发送");

    public InputPanel() {
        //初始化控件
        JScrollPane taPanel = new JScrollPane();
        JPanel bottomPanel = new JPanel();
        //设置控件
        taInputArea.setLineWrap(true);
        taPanel.setViewportView(taInputArea);
        taPanel.setPreferredSize(new Dimension(450, 100));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        setLayout(new BorderLayout());
        //添加控件
        bottomPanel.add(btnClose);
        bottomPanel.add(btnSend);
        add(taPanel);
        add(bottomPanel, BorderLayout.SOUTH);

    }

    public JTextArea getTaInputArea() {
        return taInputArea;
    }

    public void setTaInputArea(JTextArea taInputArea) {
        this.taInputArea = taInputArea;
    }

    public JButton getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(JButton btnClose) {
        this.btnClose = btnClose;
    }

    public JButton getBtnSend() {
        return btnSend;
    }

    public void setBtnSend(JButton btnSend) {
        this.btnSend = btnSend;
    }
}
