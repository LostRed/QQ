package com.zyjy.qq.service;

import com.zyjy.qq.dto.offlineFileToServer.DownloadFileRequest;
import com.zyjy.qq.dto.QueryGroupFriendRequest;
import com.zyjy.qq.dto.LoginDto;
import com.zyjy.qq.net.Client;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.JsonUtil;
import com.zyjy.qq.view.LoginView;
import net.sf.json.JSONObject;

/**
 * 登录业务
 */
public class LoginService implements InService {
    /**
     * 执行登录的业务
     * 根据返回的用户跳转界面
     *
     * @param handle 客户端接收线程对象
     */
    @Override
    public void doService(RecvThread handle) {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        LoginDto loginDto = (LoginDto) JSONObject.toBean(jsonObject, LoginDto.class);
        User user = loginDto.getUser();
        //界面响应
        LoginView.getLoginView().toFriendView(user);
        if (user != null) {
            //请求服务端查询分组好友信息
            QueryGroupFriendRequest queryGroupFriendRequest = new QueryGroupFriendRequest(user);
            Client.getClient().sendMessage(JsonUtil.toJsonString(queryGroupFriendRequest));
            //请求服务端查询离线文件信息
            DownloadFileRequest downloadFileRequest = new DownloadFileRequest(user);
            Client.getClient().sendMessage(JsonUtil.toJsonString(downloadFileRequest));
        }
    }
}
