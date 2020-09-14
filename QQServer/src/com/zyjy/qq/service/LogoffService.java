package com.zyjy.qq.service;

import com.zyjy.qq.dto.LogoffDto;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.util.JsonUtil;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 注销业务
 */
public class LogoffService implements InService {
    /**
     * 执行注销的业务
     *
     * @param handle 服务端接收线程对象
     * @throws IOException IO异常
     */
    @Override
    public void doService(RecvThread handle) throws IOException {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        LogoffDto logoffDto = (LogoffDto) JSONObject.toBean(jsonObject, LogoffDto.class);
        //回应客户端
        PrintWriter pw = handle.getPw();
        pw.println(JsonUtil.toJsonString(logoffDto));
        pw.flush();
        //关闭Socket
        RecvThread.getClientSocketMap().remove(logoffDto.getUser().getUserID());
        handle.getSocket().close();
        throw new IOException(JsonUtil.NORMAL);
    }
}
