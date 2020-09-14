package com.zyjy.qq.service;

import com.zyjy.qq.dao.DaoFactory;
import com.zyjy.qq.dao.InUserDao;
import com.zyjy.qq.dto.RegisterDto;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.pojo.User;
import net.sf.json.JSONObject;

import java.io.PrintWriter;

/**
 * 注册业务
 */
public class RegisterService implements InService {
    /**
     * 执行注册的业务
     *
     * @param handle 服务端接收线程对象
     */
    @Override
    public void doService(RecvThread handle) {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        RegisterDto registerDto = (RegisterDto) JSONObject.toBean(jsonObject, RegisterDto.class);
        //修改数据库
        InUserDao userDao = (InUserDao) DaoFactory.getDao("InUserDao");
        User user = userDao.addUser(registerDto.getUser());
        //回应客户端
        registerDto = new RegisterDto(user);
        PrintWriter pw = handle.getPw();
        pw.println(JSONObject.fromObject(registerDto).toString());
        pw.flush();
    }
}
