package com.zyjy.qq.service;

import com.zyjy.qq.dto.DownloadFileDto;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.net.SendFileThread;
import com.zyjy.qq.pojo.FileInfo;
import com.zyjy.qq.util.FileServiceUtil;
import com.zyjy.qq.util.JsonUtil;
import net.sf.json.JSONObject;

import java.io.PrintWriter;
import java.net.ServerSocket;

public class DownFileService implements InService {

    @Override
    public void doService(RecvThread handle) {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        DownloadFileDto downloadFileDto = (DownloadFileDto) JSONObject.toBean(jsonObject, DownloadFileDto.class);
        FileInfo fileInfo = downloadFileDto.getSendFile().getFileInfo();
        //分配ip和端口
        ServerSocket serverSocket = FileServiceUtil.getFileServerUtil().getServerSocket();
        String host = FileServiceUtil.getFileServerUtil().getHost();
        int port = FileServiceUtil.getFileServerUtil().getPort();
        //开启发送文件的线程
        new SendFileThread(serverSocket, fileInfo).start();
        //更改发送消息内容
        downloadFileDto.getSendFile().getFileInfo().setHost(host);
        downloadFileDto.getSendFile().getFileInfo().setPort(port);
        PrintWriter pw = handle.getPw();
        pw.println(JsonUtil.toJsonString(downloadFileDto));
        pw.flush();
    }
}
