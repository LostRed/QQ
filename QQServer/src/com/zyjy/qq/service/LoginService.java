package com.zyjy.qq.service;

import com.zyjy.qq.dao.DaoFactory;
import com.zyjy.qq.dao.InUserDao;
import com.zyjy.qq.dto.LoginDto;
import com.zyjy.qq.dto.LogoffDto;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.JsonUtil;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 登录业务
 */
public class LoginService implements InService {
    /**
     * 执行登录的业务<br>
     * 如果该用户已经连接客户端并使用新的端口连接客户端，则发回消息给旧的端口用户，通知其下线
     *
     * @param handle 服务端接收线程对象
     * @throws IOException IO异常
     */
    @Override
    public void doService(RecvThread handle) throws IOException {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        LoginDto loginDto = (LoginDto) JSONObject.toBean(jsonObject, LoginDto.class);
        //查询数据库
        InUserDao userDao = (InUserDao) DaoFactory.getDao("InUserDao");
        User user = userDao.selectUser(loginDto.getUser().getUserID(), loginDto.getUser().getPassword());
        //如果用户存在
        if (user != null) {
            //将用户和对应端口保存到Map集合
            Socket oldSocket = RecvThread.getClientSocketMap().put(user.getUserID(), handle.getSocket());
            //如果存在旧的用户端口
            if (oldSocket != null) {
                PrintWriter pw = new PrintWriter(oldSocket.getOutputStream());
                LogoffDto logoffDto = new LogoffDto(JsonUtil.ABNORMAL, user);
                pw.println(JsonUtil.toJsonString(logoffDto));
                pw.flush();
                oldSocket.close();
            }
        }
        //回应客户端
        loginDto = new LoginDto(user);
        PrintWriter pw = handle.getPw();
        pw.println(JsonUtil.toJsonString(loginDto));
        pw.flush();
    }
}
