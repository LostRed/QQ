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
     * @param handle 客户端接收线程对象
     */
    void doService(RecvThread handle) throws IOException;
}
