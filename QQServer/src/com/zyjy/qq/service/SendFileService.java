package com.zyjy.qq.service;

import com.zyjy.qq.dao.DaoFactory;
import com.zyjy.qq.dao.InFileInfoDao;
import com.zyjy.qq.dao.InSendFileDao;
import com.zyjy.qq.dto.SendFileDto;
import com.zyjy.qq.dto.offlineFile.UploadFileDto;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.ConfigUtil;
import com.zyjy.qq.util.JsonUtil;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 发送文件业务
 */
public class SendFileService implements InService {
    /**
     * 执行发送文件的用户
     * 将发送用户的消息转发给接收用户<br>
     * 如果接收用户为离线状态，则发回消息给发送用户
     *
     * @param handle 服务端接收线程对象
     * @throws IOException IO异常
     */
    @Override
    public void doService(RecvThread handle) throws IOException {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        SendFileDto sendFileDto = (SendFileDto) JSONObject.toBean(jsonObject, SendFileDto.class);
        String result = sendFileDto.getResult();
        SendFile sendFile = sendFileDto.getFileInfo();
        User sendUser = sendFile.getSendUser();
        User recvUser = sendFile.getRecvUser();
        //回应客户端
        Socket socket;
        if (JsonUtil.WAITING.equals(result)) {
            socket = RecvThread.getClientSocketMap().get(recvUser.getUserID());
        } else {
            socket = RecvThread.getClientSocketMap().get(sendUser.getUserID());
        }
        //接收用户在线，发送在线文件
        if (socket != null) {
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(JsonUtil.toJsonString(sendFileDto));
            pw.flush();
        }
        //接收用户离线，通知发送用户发送离线文件
        else {
            //查询数据库，服务器上是否已存在该文件
            String MD5 = sendFileDto.getFileInfo().getFileInfo().getMD5();
            InFileInfoDao fileInfoDao = (InFileInfoDao) DaoFactory.getDao("InFileInfoDao");
            int fileId = fileInfoDao.queryFileInfo(MD5);
            //没有文件
            if (fileId == 0) {
                //通知发送端更改为离线文件业务
                sendFile.getFileInfo().setHost(ConfigUtil.getFileServerHost());
                sendFile.getFileInfo().setPort(Integer.parseInt(ConfigUtil.getFileServerPort()));
                UploadFileDto uploadFileDto = new UploadFileDto(sendFile);
                PrintWriter pw = handle.getPw();
                pw.println(JsonUtil.toJsonString(uploadFileDto));
                pw.flush();
            }
            //有文件
            else {
                //查询数据库，服务器上是否有发送记录
                InSendFileDao inOfflineFileDao = (InSendFileDao) DaoFactory.getDao("InSendFileDao");
                int fileInfoID = inOfflineFileDao.querySendFile(fileId);
                //没有发送记录
                if (fileInfoID == 0) {
                    inOfflineFileDao.addSendFile(sendFile, fileId);
                }
                //更改发送消息内容
                sendFileDto.setResult(JsonUtil.SUCCESS);
                PrintWriter pw = handle.getPw();
                pw.println(JsonUtil.toJsonString(sendFileDto));
                pw.flush();
            }
        }
    }
}
