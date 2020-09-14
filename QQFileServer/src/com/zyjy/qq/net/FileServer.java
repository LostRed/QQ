package com.zyjy.qq.net;

import com.zyjy.qq.util.ConfigUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    /**
     * 启动服务器
     */
    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(ConfigUtil.getFileServerPort()));
            while (true) {
                System.out.println("等待客户端连接...");
                Socket socket = serverSocket.accept();
                System.out.println("IP" + socket.getInetAddress() + ":" + socket.getPort() + "-客户端连接成功！");
                new RecvThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
