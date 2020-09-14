package com.zyjy.qq.service;

import com.zyjy.qq.dao.DaoFactory;
import com.zyjy.qq.dao.InChatDao;
import com.zyjy.qq.dto.ChatDto;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.pojo.Chat;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.JsonUtil;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 聊天业务
 */
public class ChatService implements InService {
    /**
     * 执行聊天的业务<br>
     * 将发送用户的消息转发给接收用户<br>
     * 如果接收用户为离线状态，则发回消息给发送用户
     *
     * @param handle 服务端接收线程对象
     * @throws IOException IO异常
     */
    @Override
    public void doService(RecvThread handle) throws IOException {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        ChatDto chatDto = (ChatDto) JSONObject.toBean(jsonObject, ChatDto.class);
        User sendUser = chatDto.getSendUser();
        User recvUser = chatDto.getRecvUser();
        String message = chatDto.getMessage();
        //回应客户端
        Socket socket = RecvThread.getClientSocketMap().get(recvUser.getUserID());
        //接收用户已连接客户端
        if (socket != null) {
            InChatDao chatDao = (InChatDao) DaoFactory.getDao("InChatDao");
            Chat chat = new Chat(0, sendUser, recvUser, message, null);
            chatDao.addChat(chat);
            chatDto = new ChatDto(JsonUtil.SUCCESS, sendUser, recvUser, message);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(JsonUtil.toJsonString(chatDto));
            pw.flush();
        }
        //接收用户未连接客户端
        else {
            chatDto = new ChatDto(JsonUtil.FAIL, sendUser, recvUser, message);
            PrintWriter pw = handle.getPw();
            pw.println(JsonUtil.toJsonString(chatDto));
            pw.flush();
        }
    }
}
