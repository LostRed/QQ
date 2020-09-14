package com.zyjy.qq.service;

import com.zyjy.qq.net.RecvThread;

import java.io.IOException;

/**
 * 业务接口
 */
public interface InService {
    /**
     * 执行业务
     *
     * @param handle 服务端接收线程对象
     * @throws IOException IO异常
     */
    void doService(RecvThread handle) throws IOException;
}
