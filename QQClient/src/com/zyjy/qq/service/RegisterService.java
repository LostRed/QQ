package com.zyjy.qq.service;

import com.zyjy.qq.dto.RegisterDto;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.view.RegisterView;
import net.sf.json.JSONObject;

/**
 * 注册业务
 */
public class RegisterService implements InService {
    /**
     * 执行注册的业务<br>
     * 根据返回的用户跳转界面
     *
     * @param handle 客户端接收线程对象
     */
    @Override
    public void doService(RecvThread handle) {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        RegisterDto registerDto = (RegisterDto) JSONObject.toBean(jsonObject, RegisterDto.class);
        User user = registerDto.getUser();
        //界面响应
        RegisterView.getRegisterView().showResult(user);
    }
}
