package com.zyjy.qq.view;

import com.zyjy.qq.controller.ChatViewAction;
import com.zyjy.qq.dto.ChatDto;
import com.zyjy.qq.pojo.Chat;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.view.panel.ChatPanel;
import com.zyjy.qq.view.panel.HistoryPanel;
import com.zyjy.qq.view.panel.InputPanel;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 聊天窗口
 */
public class ChatView extends JFrame {
    private ChatPanel chatPanel = new ChatPanel();
    private InputPanel inputPanel = new InputPanel();
    private HistoryPanel historyPanel = new HistoryPanel();
    private boolean showHistoryPanel = false;
    private User recvUser;

    /**
     * 构造聊天窗口
     */
    public ChatView(User recvUser) {
        this.recvUser = recvUser;
        //初始化控件
        JPanel mainPanel = new JPanel();
        //设置控件
        mainPanel.setLayout(new BorderLayout());
        getRootPane().setDefaultButton(inputPanel.getBtnSend());
        //添加控件
        mainPanel.add(chatPanel);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        add(mainPanel);
        //添加监听
        ChatViewAction chatViewAction = new ChatViewAction(this);
        chatPanel.getBtnSendFile().addActionListener(chatViewAction);
        chatPanel.getBtnHistory().addActionListener(chatViewAction);
        inputPanel.getBtnClose().addActionListener(chatViewAction);
        inputPanel.getBtnSend().addActionListener(chatViewAction);
        chatPanel.getBtnSendFile().setActionCommand("sendFile");
        chatPanel.getBtnHistory().setActionCommand("history");
        inputPanel.getBtnClose().setActionCommand("close");
        inputPanel.getBtnSend().setActionCommand("send");
        //窗口设置
        setTitle(recvUser.getNike());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * 切换历史记录面板
     */
    public void changeHistoryPanel() {
        if (showHistoryPanel) {
            remove(historyPanel);
            historyPanel.getTaHistoryArea().setText("");
            showHistoryPanel = false;
        } else {
            add(historyPanel, BorderLayout.EAST);
            showHistoryPanel = true;
        }
        pack();
    }

    /**
     * 从输入框导入消息
     *
     * @param sendUser 用户对象
     * @param message  聊天内容
     */
    public void importMessage(User sendUser, String message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        String info = sendUser.getNike() + "(" + sendUser.getUserID() + ") " + time + "\r\n";
        message = message + "\r\n";
        chatPanel.getTaChatArea().append(info);
        chatPanel.getTaChatArea().append(message);
        chatPanel.getTaChatArea().append("\r\n");
    }

    /**
     * 从Socket接收缓冲区导入消息
     *
     * @param chatDto 消息数据
     */
    public void importMessage(ChatDto chatDto) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        User sendUser = chatDto.getSendUser();
        String info = sendUser.getNike() + "(" + sendUser.getUserID() + ") " + time + "\r\n";
        String message = chatDto.getMessage();
        chatPanel.getTaChatArea().append(info);
        chatPanel.getTaChatArea().append(message + "\r\n");
        chatPanel.getTaChatArea().append("\r\n");
        chatPanel.getTaChatArea().setCaretPosition(chatPanel.getTaChatArea().getText().length());
    }

    /**
     * 显示消息发送失败
     */
    public void showSendFail() {
        String message = "对方处于离线状态，可能无法接收消息！" + "\r\n";
        chatPanel.getTaChatArea().append(message);
        chatPanel.getTaChatArea().append("\r\n");
        chatPanel.getTaChatArea().setCaretPosition(chatPanel.getTaChatArea().getText().length());
    }

    /**
     * 导入聊天记录
     *
     * @param list 聊天记录信息集合
     */
    public void importChat(List<Chat> list) {
        for (Chat chat : list) {
            String info = chat.getSendUser().getNike() + "(" + chat.getSendUser().getUserID() + ") " + chat.getTime() + "\r\n";
            String message = chat.getMessage() + "\r\n";
            historyPanel.getTaHistoryArea().append(info);
            historyPanel.getTaHistoryArea().append(message);
            historyPanel.getTaHistoryArea().append("\r\n");
            historyPanel.getTaHistoryArea().setCaretPosition(historyPanel.getTaHistoryArea().getText().length());
        }
    }

    /**
     * 添加文件传输进度条
     *
     * @param total 进度条最大值
     */
    public void addProgressBar(int total) {
        JProgressBar progressBar = chatPanel.getProgressBar();
        progressBar.setMaximum(total);
        chatPanel.getTopPanel().add(progressBar);
        chatPanel.updateUI();
    }

    /**
     * 更新当前进度
     *
     * @param current 进度条当前值
     */
    public void updateProgressBar(int current) {
        JProgressBar progressBar = chatPanel.getProgressBar();
        progressBar.setValue(current);
    }

    /**
     * 移除文件传输进度条
     */
    public void removeProgressBar() {
        JProgressBar progressBar = chatPanel.getProgressBar();
        chatPanel.getTopPanel().remove(progressBar);
        chatPanel.updateUI();
    }

    //get和set方法
    public ChatPanel getChatPanel() {
        return chatPanel;
    }

    public void setChatPanel(ChatPanel chatPanel) {
        this.chatPanel = chatPanel;
    }

    public InputPanel getInputPanel() {
        return inputPanel;
    }

    public void setInputPanel(InputPanel inputPanel) {
        this.inputPanel = inputPanel;
    }

    public HistoryPanel getHistoryPanel() {
        return historyPanel;
    }

    public void setHistoryPanel(HistoryPanel historyPanel) {
        this.historyPanel = historyPanel;
    }

    public boolean isShowHistoryPanel() {
        return showHistoryPanel;
    }

    public void setShowHistoryPanel(boolean showHistoryPanel) {
        this.showHistoryPanel = showHistoryPanel;
    }

    public User getRecvUser() {
        return recvUser;
    }

    public void setRecvUser(User recvUser) {
        this.recvUser = recvUser;
    }
}
