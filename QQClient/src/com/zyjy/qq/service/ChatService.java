package com.zyjy.qq.service;

import com.zyjy.qq.dto.ChatDto;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.JsonUtil;
import com.zyjy.qq.view.ChatView;
import com.zyjy.qq.view.FriendView;
import net.sf.json.JSONObject;

/**
 * 聊天业务
 */
public class ChatService implements InService {
    /**
     * 执行聊天的业务<br>
     * 如果接收到服务端发来的转发失败消息，则将消息发送失败显示在聊天窗口上
     *
     * @param handle 客户端接收线程对象
     */
    @Override
    public void doService(RecvThread handle) {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        ChatDto chatDto = (ChatDto) JSONObject.toBean(jsonObject, ChatDto.class);
        User sendUser = chatDto.getSendUser();
        User recvUser = chatDto.getRecvUser();
        //界面响应
        if (JsonUtil.SUCCESS.equals(chatDto.getResult())) {
            ChatView cv = FriendView.getFriendView().ejectChatView(sendUser);
            cv.importMessage(chatDto);
        } else if (JsonUtil.FAIL.equals(chatDto.getResult())) {
            ChatView cv = FriendView.getFriendView().getCvMap().get(recvUser.getUserID());
            cv.showSendFail();
        }
    }
}
