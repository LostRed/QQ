package com.zyjy.qq.service;

import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.pojo.GroupFriend;
import com.zyjy.qq.view.FriendView;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 显示分组好友业务
 */
public class GroupFriendService implements InService {
    /**
     * 执行显示分组好友的业务<br>
     * 根据消息数据，将分组好友显示在好友窗口上
     *
     * @param handle 客户端接收线程对象
     */
    @Override
    public void doService(RecvThread handle) {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        JSONArray jsonArray = jsonObject.getJSONArray("groupFriendList");
        Collection<GroupFriend> collection = JSONArray.toCollection(jsonArray, GroupFriend.class);
        List<GroupFriend> list = new ArrayList<>(collection);
        //界面响应
        FriendView.getFriendView().importFriendList(list);
    }
}
