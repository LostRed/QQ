package com.zyjy.qq.service;

import com.zyjy.qq.dao.DaoFactory;
import com.zyjy.qq.dao.InGroupFriendDao;
import com.zyjy.qq.dto.QueryGroupFriendRequest;
import com.zyjy.qq.dto.QueryGroupFriendResponse;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.pojo.GroupFriend;
import net.sf.json.JSONObject;

import java.io.PrintWriter;
import java.util.List;

/**
 * 查询分组好友业务
 */
public class GroupFriendService implements InService {
    /**
     * 执行查询分组好友的业务
     *
     * @param handle 服务端接收线程对象
     */
    @Override
    public void doService(RecvThread handle) {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        QueryGroupFriendRequest queryHistoryRequest = (QueryGroupFriendRequest) JSONObject.toBean(jsonObject, QueryGroupFriendRequest.class);
        //查询数据库
        InGroupFriendDao groupFriendDao = (InGroupFriendDao) DaoFactory.getDao("InGroupFriendDao");
        List<GroupFriend> list = groupFriendDao.queryGroupFriend(queryHistoryRequest.getUser());
        //回应客户端
        QueryGroupFriendResponse groupFriendDto = new QueryGroupFriendResponse(list);
        PrintWriter pw = handle.getPw();
        pw.println(JSONObject.fromObject(groupFriendDto).toString());
        pw.flush();
    }
}
