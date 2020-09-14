package com.zyjy.qq.net;

import com.zyjy.qq.pojo.FileInfo;
import com.zyjy.qq.util.IOStreamUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 发送文件线程
 */
public class SendFileThread extends Thread {
    /**
     * 服务端套接字
     */
    private final ServerSocket serverSocket;
    /**
     * 发送的文件信息
     */
    private final FileInfo fileInfo;

    /**
     * 构造接收文件线程
     *
     * @param serverSocket 服务端套接字
     * @param fileInfo     发送的文件信息
     */
    public SendFileThread(ServerSocket serverSocket, FileInfo fileInfo) {
        this.serverSocket = serverSocket;
        this.fileInfo = fileInfo;
    }

    @Override
    public void run() {
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            socket = serverSocket.accept();
            is = new FileInputStream("file/" + fileInfo.getMD5());
            os = socket.getOutputStream();
            IOStreamUtil.Transmit(is, os);
            System.out.println("IP" + socket.getInetAddress() + ":" + socket.getPort() + "-" + "文件传输完成！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOStreamUtil.release(is, os, socket);
        }
    }
}
