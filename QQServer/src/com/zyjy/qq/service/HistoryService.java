package com.zyjy.qq.service;

import com.zyjy.qq.dao.DaoFactory;
import com.zyjy.qq.dao.InChatDao;
import com.zyjy.qq.dto.QueryHistoryRequest;
import com.zyjy.qq.dto.QueryHistoryResponse;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.pojo.Chat;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.JsonUtil;
import net.sf.json.JSONObject;

import java.io.PrintWriter;
import java.util.List;

/**
 * 查询聊天信息业务
 */
public class HistoryService implements InService {
    /**
     * 执行查询聊天信息的业务
     *
     * @param handle 服务端接收线程对象
     */
    @Override
    public void doService(RecvThread handle) {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        QueryHistoryRequest queryHistoryRequest = (QueryHistoryRequest) JSONObject.toBean(jsonObject, QueryHistoryRequest.class);
        //查询数据库
        InChatDao chatDao = (InChatDao) DaoFactory.getDao("InChatDao");
        User sendUser = queryHistoryRequest.getSendUser();
        User recvUser = queryHistoryRequest.getRecvUser();
        List<Chat> list = chatDao.queryChat(sendUser, recvUser);
        //回应客户端
        QueryHistoryResponse queryHistoryResponse = new QueryHistoryResponse(sendUser, recvUser, list);
        PrintWriter pw = handle.getPw();
        pw.println(JsonUtil.toJsonString(queryHistoryResponse));
        pw.flush();
    }
}
