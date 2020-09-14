package com.zyjy.qq.service.offlineFile;

import com.zyjy.qq.dao.DaoFactory;
import com.zyjy.qq.dao.InFileInfoDao;
import com.zyjy.qq.dao.InSendFileDao;
import com.zyjy.qq.dto.offlineFile.AddSendFileDto;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.service.InService;
import net.sf.json.JSONObject;

public class AddSendFileService implements InService {
    @Override
    public void doService(RecvThread handle) {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        AddSendFileDto addSendFileDto = (AddSendFileDto) JSONObject.toBean(jsonObject, AddSendFileDto.class);
        SendFile sendFile = addSendFileDto.getSendFile();
        //添加离线文件信息
        InFileInfoDao fileInfoDao = (InFileInfoDao) DaoFactory.getDao("InFileInfoDao");
        InSendFileDao inSendFileDao = (InSendFileDao) DaoFactory.getDao("InSendFileDao");
        int fileId = fileInfoDao.addFileInfo(sendFile);
        inSendFileDao.addSendFile(sendFile, fileId);
    }
}
