package com.zyjy.qq.net.offlineFile;

import com.zyjy.qq.dto.offlineFileToFileServer.UploadFileDto;
import com.zyjy.qq.dto.offlineFileToServer.AddSendFileDto;
import com.zyjy.qq.net.Client;
import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.util.IOStreamUtil;
import com.zyjy.qq.util.JsonUtil;
import com.zyjy.qq.view.ChatView;
import net.sf.json.JSONObject;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

/**
 * 上传文件线程
 */
public class UploadFileThread extends Thread {
    /**
     * 聊天窗口
     */
    private final ChatView chatView;
    /**
     * 文件信息
     */
    private final SendFile sendFile;
    /**
     * 与文件服务器建立的套接字
     */
    private Socket socket;

    /**
     * 构造上传文件的线程
     *
     * @param chatView 聊天窗口
     * @param sendFile 文件信息
     */
    public UploadFileThread(ChatView chatView, SendFile sendFile) {
        this.chatView = chatView;
        this.sendFile = sendFile;
    }

    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        try {
            String jsonString = getFileServerMessage();
            UploadFileDto uploadFileDto = (UploadFileDto) JSONObject.toBean(JsonUtil.toJsonObject(jsonString), UploadFileDto.class);
            String filePath = uploadFileDto.getSendFile().getFileInfo().getFilePath();
            String host = uploadFileDto.getSendFile().getFileInfo().getHost();
            int port = uploadFileDto.getSendFile().getFileInfo().getPort();
            //连接文件服务器接收文件线程
            socket = new Socket(host, port);
            //初始化流
            is = new FileInputStream(filePath);
            os = socket.getOutputStream();
            //开始传输
            IOStreamUtil.Transmit(is, os, chatView);
            socket.shutdownOutput();
            //通知主服务器更改数据库
            AddSendFileDto addSendFileDto = new AddSendFileDto(sendFile);
            Client.getClient().sendMessage(JsonUtil.toJsonString(addSendFileDto));
            JOptionPane.showMessageDialog(chatView, "文件发送成功！");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(chatView, "离线文件发送失败！");
        } finally {
            chatView.removeProgressBar();
            IOStreamUtil.release(is, os, socket);
        }
    }

    /**
     * 等待文件服务器的消息
     *
     * @return 文件服务器的消息
     * @throws IOException IO异常
     */
    private String getFileServerMessage() throws IOException {
        String host = sendFile.getFileInfo().getHost();
        int port = sendFile.getFileInfo().getPort();
        socket = new Socket(host, port);
        UploadFileDto uploadFileDto = new UploadFileDto(sendFile);
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pw.println(JsonUtil.toJsonString(uploadFileDto));
        pw.flush();
        socket.shutdownOutput();
        return br.readLine();
    }
}
