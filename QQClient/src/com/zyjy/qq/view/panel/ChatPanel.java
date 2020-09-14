package com.zyjy.qq.view.panel;

import javax.swing.*;
import java.awt.*;

/**
 * 聊天面板
 */
public class ChatPanel extends JPanel {
    private JPanel topPanel = new JPanel();
    private JTextArea taChatArea = new JTextArea();
    private JButton btnSendFile = new JButton("发送文件");
    private JButton btnHistory = new JButton("历史记录");
    private JProgressBar progressBar = new JProgressBar();

    public ChatPanel() {
        //初始化
        JScrollPane taPanel = new JScrollPane();
        JPanel bottomPanel = new JPanel();
        //设置控件
        taChatArea.setEditable(false);
        taChatArea.setLineWrap(true);
        taPanel.setViewportView(taChatArea);
        taPanel.setPreferredSize(new Dimension(450, 350));
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        setLayout(new BorderLayout());
        //添加控件
        topPanel.add(btnSendFile);
        bottomPanel.add(btnHistory);
        add(topPanel, BorderLayout.NORTH);
        add(taPanel);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public JTextArea getTaChatArea() {
        return taChatArea;
    }

    public void setTaChatArea(JTextArea taChatArea) {
        this.taChatArea = taChatArea;
    }

    public JButton getBtnSendFile() {
        return btnSendFile;
    }

    public void setBtnSendFile(JButton btnSendFile) {
        this.btnSendFile = btnSendFile;
    }

    public JButton getBtnHistory() {
        return btnHistory;
    }

    public void setBtnHistory(JButton btnHistory) {
        this.btnHistory = btnHistory;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }
}
