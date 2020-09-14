package com.zyjy.qq.net.offlineFile;

import com.zyjy.qq.dto.offlineFileToFileServer.DownloadFileDto;
import com.zyjy.qq.dto.offlineFileToServer.DeleteSendFileDto;
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
 * 下载文件线程
 */
public class DownloadFileThread extends Thread {
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
     * 构造下载文件的线程
     *
     * @param chatView 聊天窗口
     * @param sendFile 文件信息
     */
    public DownloadFileThread(ChatView chatView, SendFile sendFile) {
        this.chatView = chatView;
        this.sendFile = sendFile;
    }

    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        try {
            String jsonString = getFileServerMessage();
            DownloadFileDto downloadFileDto = (DownloadFileDto) JSONObject.toBean(JsonUtil.toJsonObject(jsonString), DownloadFileDto.class);
            long fileSize = downloadFileDto.getSendFile().getFileInfo().getFileSize();
            String fileName = downloadFileDto.getSendFile().getFileInfo().getFileName();
            String host = downloadFileDto.getSendFile().getFileInfo().getHost();
            int port = downloadFileDto.getSendFile().getFileInfo().getPort();
            //选择保存文件的路径
            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(new File(fileName));
            if (chooser.showSaveDialog(chatView) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                chatView.addProgressBar((int) fileSize);
                //连接文件服务器发送文件线程
                socket = new Socket(host, port);
                //初始化流
                is = socket.getInputStream();
                os = new FileOutputStream(file);
                //开始传输
                IOStreamUtil.Transmit(is, os, chatView, fileSize);
                socket.shutdownOutput();
                //通知主服务器更改数据库
                DeleteSendFileDto deleteSendFileDto = new DeleteSendFileDto(sendFile);
                Client.getClient().sendMessage(JsonUtil.toJsonString(deleteSendFileDto));
                JOptionPane.showMessageDialog(chatView, "文件接收成功！");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(chatView, "离线文件接收失败！");
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
        DownloadFileDto downloadFileDto = new DownloadFileDto(sendFile);
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pw.println(JsonUtil.toJsonString(downloadFileDto));
        pw.flush();
        socket.shutdownOutput();
        return br.readLine();
    }
}
