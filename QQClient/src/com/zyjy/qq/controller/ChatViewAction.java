package com.zyjy.qq.controller;

import com.zyjy.qq.dto.ChatDto;
import com.zyjy.qq.dto.QueryHistoryRequest;
import com.zyjy.qq.dto.SendFileDto;
import com.zyjy.qq.net.Client;
import com.zyjy.qq.pojo.FileInfo;
import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.JsonUtil;
import com.zyjy.qq.util.MD5Util;
import com.zyjy.qq.view.ChatView;
import com.zyjy.qq.view.FriendView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * 聊天窗口动作监听
 */
public class ChatViewAction implements ActionListener {
    /**
     * 绑定的聊天窗口
     */
    private final ChatView chatView;

    /**
     * 构造聊天窗口动作监听
     *
     * @param chatView 聊天窗口对象
     */
    public ChatViewAction(ChatView chatView) {
        this.chatView = chatView;
    }

    /**
     * 根据动作命令执行任务
     *
     * @param e 动作事件
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        FriendView fv = FriendView.getFriendView();
        Client client = Client.getClient();
        switch (command) {
            case "sendFile":
                JFileChooser chooser = new JFileChooser();
                if (chooser.showOpenDialog(chatView) == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    String fileName = file.getName();
                    String filePath = file.getAbsolutePath();
                    long fileSize = file.length();
                    String MD5 = MD5Util.getMD5(file);
                    FileInfo fileInfo = new FileInfo(0, fileName, filePath, fileSize, MD5, null, 0);
                    SendFile sendFile = new SendFile(0, fv.getUser(), chatView.getRecvUser(), fileInfo, null);
                    SendFileDto sendFileDto = new SendFileDto(JsonUtil.WAITING, sendFile);
                    client.sendMessage(JsonUtil.toJsonString(sendFileDto));
                    chatView.addProgressBar((int) fileSize);
                }
                break;
            case "history":
                chatView.changeHistoryPanel();
                if (chatView.isShowHistoryPanel()) {
                    QueryHistoryRequest queryHistoryRequest = new QueryHistoryRequest(fv.getUser(), chatView.getRecvUser());
                    client.sendMessage(JsonUtil.toJsonString(queryHistoryRequest));
                }
                break;
            case "close":
                chatView.dispose();
                break;
            case "send":
                if (!chatView.getInputPanel().getTaInputArea().getText().equals("")) {
                    User sendUser = fv.getUser();
                    User recvUser = chatView.getRecvUser();
                    String message = chatView.getInputPanel().getTaInputArea().getText();
                    chatView.importMessage(sendUser, message);
                    chatView.getInputPanel().getTaInputArea().setText("");
                    ChatDto chatDto = new ChatDto(null, sendUser, recvUser, message);
                    client.sendMessage(JsonUtil.toJsonString(chatDto));
                }
                break;
        }
    }
}
