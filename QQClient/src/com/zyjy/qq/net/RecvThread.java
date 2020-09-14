package com.zyjy.qq.net;

import com.zyjy.qq.service.ServiceController;
import com.zyjy.qq.util.JsonUtil;
import com.zyjy.qq.view.FriendView;
import net.sf.json.JSONObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 通讯主接收线程
 */
public class RecvThread extends Thread {
    /**
     * 与服务端建立的套接字对象
     */
    private final Socket socket;
    /**
     * 字符输入流
     */
    private BufferedReader br;
    /**
     * 解析的JSON对象
     */
    private JSONObject jsonObject;

    /**
     * 构造通讯主接收线程
     *
     * @param socket 套接字对象
     */
    public RecvThread(Socket socket) {
        this.socket = socket;
        try {
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 循环解析服务端发来的消息并执行业务
     */
    @Override
    public void run() {
        while (true) {
            try {
                //读取消息
                String jsonString = br.readLine();
                System.out.println("IP" + socket.getInetAddress() + ":" + socket.getPort() + "-" + jsonString);
                //解析消息
                jsonObject = JsonUtil.toJsonObject(jsonString);
                String type = jsonObject.getString("type");
                //执行业务
                ServiceController.getService(type).doService(this);
            } catch (IOException e) {
                if (!e.getMessage().equals(JsonUtil.NORMAL)) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(FriendView.getFriendView(), "与IP" + socket.getInetAddress() + ":" + socket.getPort() + "服务器断开连接！");
                    Client.getClient().setConnected(false);
                }
                break;
            }
        }
    }

    //get方法
    public Socket getSocket() {
        return socket;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
