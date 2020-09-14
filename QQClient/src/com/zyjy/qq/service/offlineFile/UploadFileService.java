package com.zyjy.qq.service.offlineFile;

import com.zyjy.qq.dto.offlineFileToFileServer.UploadFileDto;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.net.offlineFile.UploadFileThread;
import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.service.InService;
import com.zyjy.qq.view.ChatView;
import com.zyjy.qq.view.FriendView;
import net.sf.json.JSONObject;

public class UploadFileService implements InService {
    @Override
    public void doService(RecvThread handle) {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        UploadFileDto uploadFileDto = (UploadFileDto) JSONObject.toBean(jsonObject, UploadFileDto.class);
        SendFile sendFile = uploadFileDto.getSendFile();
        User recvUser = sendFile.getRecvUser();
        //开启上传文件的线程
        ChatView cv = FriendView.getFriendView().getCvMap().get(recvUser.getUserID());
        new UploadFileThread(cv, sendFile).start();
    }
}
