package com.zyjy.qq.service;

import com.zyjy.qq.dto.QueryHistoryResponse;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.pojo.Chat;
import com.zyjy.qq.view.FriendView;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 显示聊天记录业务
 */
public class HistoryService implements InService {
    /**
     * 执行显示聊天记录的业务<br>
     * 根据消息数据，将聊天记录显示在聊天窗口上
     *
     * @param handle 客户端接收线程对象
     */
    @Override
    public void doService(RecvThread handle) {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        QueryHistoryResponse queryHistoryResponse = (QueryHistoryResponse) JSONObject.toBean(jsonObject, QueryHistoryResponse.class);
        JSONArray jsonArray = jsonObject.getJSONArray("chatList");
        Collection<Chat> collection = JSONArray.toCollection(jsonArray, Chat.class);
        List<Chat> list = new ArrayList<>(collection);
        //界面响应
        int userId = queryHistoryResponse.getRecvUser().getUserID();
        FriendView.getFriendView().getCvMap().get(userId).importChat(list);
    }
}
