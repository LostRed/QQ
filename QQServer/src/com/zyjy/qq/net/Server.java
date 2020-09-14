package com.zyjy.qq.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务端
 */
public class Server {
    /**
     * 服务器连接后对应的套接字集合
     */
    private static final Map<String, Socket> SERVER_SOCKET_MAP = new HashMap<>();

    /**
     * 获取服务器接字集合
     *
     * @return 服务器套接字集合
     */
    public static Map<String, Socket> getServerSocketMap() {
        return SERVER_SOCKET_MAP;
    }

    /**
     * 启动服务器
     */
    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(10080);
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
