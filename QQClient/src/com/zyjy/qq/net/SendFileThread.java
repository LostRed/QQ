package com.zyjy.qq.net;

import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.util.IOStreamUtil;
import com.zyjy.qq.view.ChatView;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 发送文件线程
 */
public class SendFileThread extends Thread {
    /**
     * 聊天窗口
     */
    private final ChatView chatView;
    /**
     * 文件信息
     */
    private final SendFile sendFile;

    /**
     * 构造发送文件的线程
     *
     * @param chatView 聊天窗口
     * @param sendFile 文件信息
     */
    public SendFileThread(ChatView chatView, SendFile sendFile) {
        this.chatView = chatView;
        this.sendFile = sendFile;
    }

    @Override
    public void run() {
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            String host = sendFile.getFileInfo().getHost();
            int port = sendFile.getFileInfo().getPort();
            String filePath = sendFile.getFileInfo().getFilePath();
            long fileSize = sendFile.getFileInfo().getFileSize();
            socket = new Socket(host, port);
            is = new FileInputStream(filePath);
            os = socket.getOutputStream();
            IOStreamUtil.Transmit(is, os, chatView, fileSize);
            socket.shutdownOutput();
            JOptionPane.showMessageDialog(chatView, "文件发送成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            chatView.removeProgressBar();
            IOStreamUtil.release(is, os, socket);
        }
    }
}
