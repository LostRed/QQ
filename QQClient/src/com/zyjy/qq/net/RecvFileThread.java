package com.zyjy.qq.net;

import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.util.IOStreamUtil;
import com.zyjy.qq.view.ChatView;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 接收文件线程
 */
public class RecvFileThread extends Thread {
    /**
     * 服务端套接字
     */
    private final ServerSocket serverSocket;
    /**
     * 聊天窗口
     */
    private final ChatView chatView;
    /**
     * 文件信息
     */
    private final SendFile sendFile;

    /**
     * 构造接收文件的线程
     *
     * @param serverSocket 服务端套接字
     * @param chatView     聊天窗口
     * @param sendFile     文件信息
     */
    public RecvFileThread(ServerSocket serverSocket, ChatView chatView, SendFile sendFile) {
        this.serverSocket = serverSocket;
        this.chatView = chatView;
        this.sendFile = sendFile;
    }

    @Override
    public void run() {
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            String filePath = sendFile.getFileInfo().getFilePath();
            long fileSize = sendFile.getFileInfo().getFileSize();
            chatView.addProgressBar((int) fileSize);
            socket = serverSocket.accept();
            is = socket.getInputStream();
            os = new FileOutputStream(filePath);
            IOStreamUtil.Transmit(is, os, chatView, fileSize);
            JOptionPane.showMessageDialog(chatView, "文件接收成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            chatView.removeProgressBar();
            IOStreamUtil.release(is, os, socket);
        }
    }
}
