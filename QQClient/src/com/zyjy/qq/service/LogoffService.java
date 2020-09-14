package com.zyjy.qq.service;

import com.zyjy.qq.dto.LogoffDto;
import com.zyjy.qq.net.Client;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.util.JsonUtil;
import com.zyjy.qq.view.FriendView;
import com.zyjy.qq.view.LoginView;
import net.sf.json.JSONObject;

import javax.swing.*;
import java.io.IOException;

/**
 * 注销业务
 */
public class LogoffService implements InService {
    /**
     * 执行注销的业务
     *
     * @param handle 客户端接收线程对象
     * @throws IOException IO异常
     */
    @Override
    public void doService(RecvThread handle) throws IOException {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        LogoffDto logoffDto = (LogoffDto) JSONObject.toBean(jsonObject, LogoffDto.class);
        String result = logoffDto.getResult();
        Client.getClient().setConnected(false);
        if (JsonUtil.NORMAL.equals(result)) {
            FriendView.getFriendView().setVisible(false);
            LoginView.getLoginView().setVisible(true);
            throw new IOException(JsonUtil.NORMAL);
        } else {
            JOptionPane.showMessageDialog(FriendView.getFriendView(), "您的账号已在其它地方登录！");
            FriendView.getFriendView().setVisible(false);
            LoginView.getLoginView().setVisible(true);
            throw new IOException(JsonUtil.ABNORMAL);
        }
    }
}
