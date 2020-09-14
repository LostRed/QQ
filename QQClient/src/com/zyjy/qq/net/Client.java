package com.zyjy.qq.net;

import com.zyjy.qq.dto.LogoffDto;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.ConfigUtil;
import com.zyjy.qq.util.JsonUtil;
import com.zyjy.qq.view.FriendView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端
 */
public class Client {
    /**
     * 单例客户端对象
     */
    private static Client CLIENT;
    /**
     * 与服务端建立的套接字
     */
    private Socket socket;
    /**
     * 格式化输出流
     */
    private PrintWriter pw;
    /**
     * 是否连接上服务端
     */
    private boolean connected;

    /**
     * 构造客户端
     */
    private Client() {
    }

    /**
     * 获取单例客户端
     *
     * @return 单例客户端
     */
    public static Client getClient() {
        if (CLIENT == null) {
            CLIENT = new Client();
        }
        return CLIENT;
    }

    /**
     * 请求连接客户端
     *
     * @param host ip地址
     * @param port 端口
     * @throws IOException IO异常
     */
    public void connectToServer(String host, String port) throws IOException {
        socket = new Socket(host, Integer.parseInt(port));
        pw = new PrintWriter(socket.getOutputStream());
        connected = true;
        new RecvThread(socket).start();
    }

    /**
     * 向服务端发送消息
     *
     * @param sendMessage 需要发送给服务端的消息
     */
    public void sendMessage(String sendMessage) {
        pw.println(sendMessage);
        pw.flush();
    }

    /**
     * 退出客户端
     */
    public void exit() {
        ConfigUtil.writeConfig();
        Client client = Client.getClient();
        //客户端已连接服务端时
        if (client.isConnected()) {
            User user = FriendView.getFriendView().getUser();
            LogoffDto logoffDto = new LogoffDto(JsonUtil.NORMAL, user);
            client.sendMessage(JsonUtil.toJsonString(logoffDto));
        }
        //客户端未连接服务端时
        else {
            System.exit(0);
        }
    }

    //get和set方法
    public Socket getSocket() {
        return socket;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
