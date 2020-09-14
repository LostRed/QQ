package com.zyjy.qq.net;

import com.zyjy.qq.service.ServiceController;
import com.zyjy.qq.util.JsonUtil;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 通讯主接收线程
 */
public class RecvThread extends Thread {
    /**
     * 用户连接后对应的套接字集合
     */
    private static final Map<Integer, Socket> CLIENT_SOCKET_MAP = new HashMap<>();
    /**
     * 与客户端建立的套接字对象
     */
    private final Socket socket;
    /**
     * 字符输出流
     */
    private PrintWriter pw;
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
     * @param socket 与客户端建立的套接字对象
     */
    public RecvThread(Socket socket) {
        this.socket = socket;
        try {
            this.pw = new PrintWriter(socket.getOutputStream());
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取用户套接字集合
     *
     * @return 用户套接字集合
     */
    public static Map<Integer, Socket> getClientSocketMap() {
        return CLIENT_SOCKET_MAP;
    }

    /**
     * 循环解析客户端发来的消息并执行业务
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
                }
                System.out.println("IP" + socket.getInetAddress() + ":" + socket.getPort() + "-客户端断开连接！");
                break;
            }
        }
    }

    //get方法
    public Socket getSocket() {
        return socket;
    }

    public PrintWriter getPw() {
        return pw;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}