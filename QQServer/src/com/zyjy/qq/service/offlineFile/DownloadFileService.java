package com.zyjy.qq.service.offlineFile;

import com.zyjy.qq.dao.DaoFactory;
import com.zyjy.qq.dao.InSendFileDao;
import com.zyjy.qq.dto.offlineFile.DownloadFileRequest;
import com.zyjy.qq.dto.offlineFile.DownloadFileResponse;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.service.InService;
import com.zyjy.qq.util.JsonUtil;
import net.sf.json.JSONObject;

import java.io.PrintWriter;
import java.util.List;

public class DownloadFileService implements InService {

    @Override
    public void doService(RecvThread handle) {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        DownloadFileRequest downloadFileRequest = (DownloadFileRequest) JSONObject.toBean(jsonObject, DownloadFileRequest.class);
        User recvUser = downloadFileRequest.getUser();
        //查询数据库，是否有离线文件
        InSendFileDao inOfflineFileDao = (InSendFileDao) DaoFactory.getDao("InSendFileDao");
        List<SendFile> list = inOfflineFileDao.querySendFile(recvUser);
        //回应客户端
        DownloadFileResponse downloadFileResponse = new DownloadFileResponse(list);
        PrintWriter pw = handle.getPw();
        pw.println(JsonUtil.toJsonString(downloadFileResponse));
        pw.flush();
    }
}
