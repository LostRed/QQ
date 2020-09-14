package com.zyjy.qq.view.panel;

import javax.swing.*;
import java.awt.*;

/**
 * 历史聊天记录面板
 */
public class HistoryPanel extends JPanel {
    private JTextArea taHistoryArea = new JTextArea();

    public HistoryPanel() {
        //初始化
        JScrollPane taPanel = new JScrollPane();
        //设置控件
        taHistoryArea.setEditable(false);
        taHistoryArea.setLineWrap(true);
        taPanel.setViewportView(taHistoryArea);
        taPanel.setPreferredSize(new Dimension(250, 350));
        setLayout(new BorderLayout());
        //添加控件
        add(taPanel);
    }

    public JTextArea getTaHistoryArea() {
        return taHistoryArea;
    }

    public void setTaHistoryArea(JTextArea taHistoryArea) {
        this.taHistoryArea = taHistoryArea;
    }
}
