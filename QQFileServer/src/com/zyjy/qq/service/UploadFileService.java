package com.zyjy.qq.service;

import com.zyjy.qq.dto.UploadFileDto;
import com.zyjy.qq.net.RecvFileThread;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.pojo.FileInfo;
import com.zyjy.qq.util.FileServiceUtil;
import com.zyjy.qq.util.JsonUtil;
import net.sf.json.JSONObject;

import java.io.PrintWriter;
import java.net.ServerSocket;

public class UploadFileService implements InService {

    @Override
    public void doService(RecvThread handle) {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        UploadFileDto uploadFileDto = (UploadFileDto) JSONObject.toBean(jsonObject, UploadFileDto.class);
        FileInfo fileInfo = uploadFileDto.getSendFile().getFileInfo();
        //分配ip和端口
        ServerSocket serverSocket = FileServiceUtil.getFileServerUtil().getServerSocket();
        String host = FileServiceUtil.getFileServerUtil().getHost();
        int port = FileServiceUtil.getFileServerUtil().getPort();
        //开启接收文件的线程
        new RecvFileThread(serverSocket, fileInfo).start();
        //更改发送消息内容
        uploadFileDto.getSendFile().getFileInfo().setHost(host);
        uploadFileDto.getSendFile().getFileInfo().setPort(port);
        PrintWriter pw = handle.getPw();
        pw.println(JsonUtil.toJsonString(uploadFileDto));
        pw.flush();
    }
}
