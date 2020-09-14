package com.zyjy.qq.service.offlineFile;

import com.zyjy.qq.dao.DaoFactory;
import com.zyjy.qq.dao.InSendFileDao;
import com.zyjy.qq.dto.offlineFile.DeleteSendFileDto;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.service.InService;
import net.sf.json.JSONObject;

public class DeleteSendFileService implements InService {

    @Override
    public void doService(RecvThread handle) {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        DeleteSendFileDto deleteSendFileDto = (DeleteSendFileDto) JSONObject.toBean(jsonObject, DeleteSendFileDto.class);
        SendFile sendFile = deleteSendFileDto.getSendFile();
        //删除离线文件信息
        InSendFileDao sendFileDao = (InSendFileDao) DaoFactory.getDao("InSendFileDao");
        sendFileDao.deleteSendFile(sendFile.getFileInfoId());
    }
}
