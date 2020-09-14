package com.zyjy.qq.service;

import com.zyjy.qq.dto.SendFileDto;
import com.zyjy.qq.net.Client;
import com.zyjy.qq.net.RecvFileThread;
import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.net.SendFileThread;
import com.zyjy.qq.pojo.FileInfo;
import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.util.FileServiceUtil;
import com.zyjy.qq.util.JsonUtil;
import com.zyjy.qq.view.ChatView;
import com.zyjy.qq.view.FriendView;
import net.sf.json.JSONObject;

import javax.swing.*;
import java.io.File;
import java.net.ServerSocket;

/**
 * 发送文件业务
 */
public class SendFileService implements InService {
    /**
     * 执行发送文件的业务<br>
     * 将消息转发个接收文件的用户，若接收用户离线
     *
     * @param handle 客户端接收线程对象
     */
    @Override
    public void doService(RecvThread handle) {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        SendFileDto sendFileDto = (SendFileDto) JSONObject.toBean(jsonObject, SendFileDto.class);
        String result = sendFileDto.getResult();
        SendFile sendFile = sendFileDto.getFileInfo();
        User sendUser = sendFile.getSendUser();
        User recvUser = sendFile.getRecvUser();
        FileInfo fileInfo = sendFile.getFileInfo();
        if (JsonUtil.WAITING.equals(result)) {
            //界面响应
            ChatView cv = FriendView.getFriendView().ejectChatView(sendUser);
            int choice = JOptionPane.showConfirmDialog(cv,
                    "是否接收" + sendUser.getNike() + "发送的" + sendFile.getFileInfo().getFileName() +
                            "(" + sendFile.getFileInfo().getFileSize() / 1024 + " KB)文件？",
                    "提示", JOptionPane.YES_NO_OPTION);
            sendFileDto.setResult(JsonUtil.REFUSE);
            if (choice == JOptionPane.YES_OPTION) {
                JFileChooser chooser = new JFileChooser();
                chooser.setSelectedFile(new File(sendFile.getFileInfo().getFileName()));
                if (chooser.showSaveDialog(cv) == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    //分配ip和端口
                    ServerSocket serverSocket = FileServiceUtil.getFileServerUtil().getServerSocket();
                    String host = FileServiceUtil.getFileServerUtil().getHost();
                    int port = FileServiceUtil.getFileServerUtil().getPort();
                    FileInfo recvFileInfo = new FileInfo(0, fileInfo.getFileName(), file.getAbsolutePath(),
                            fileInfo.getFileSize(), fileInfo.getMD5(), host, port);
                    SendFile recvSendFile = new SendFile(0, sendUser, recvUser, recvFileInfo, null);
                    //开启接收文件的线程
                    new RecvFileThread(serverSocket, cv, recvSendFile).start();
                    //更改发送消息内容
                    sendFileDto.getFileInfo().getFileInfo().setHost(host);
                    sendFileDto.getFileInfo().getFileInfo().setPort(port);
                    sendFileDto.setResult(JsonUtil.ACCEPT);
                }
            }
            Client.getClient().sendMessage(JsonUtil.toJsonString(sendFileDto));
        } else if (JsonUtil.ACCEPT.equals(result)) {
            //开启发送文件的线程
            ChatView cv = FriendView.getFriendView().getCvMap().get(recvUser.getUserID());
            new SendFileThread(cv, sendFile).start();
        } else if (JsonUtil.REFUSE.equals(result)) {
            //界面响应
            ChatView cv = FriendView.getFriendView().ejectChatView(recvUser);
            JOptionPane.showMessageDialog(cv, "对方已拒绝接收文件！");
        } else if (JsonUtil.SUCCESS.equals(result)) {
            //界面响应
            ChatView cv = FriendView.getFriendView().getCvMap().get(recvUser.getUserID());
            cv.updateProgressBar((int) fileInfo.getFileSize());
            JOptionPane.showMessageDialog(cv, "文件发送成功！");
            cv.removeProgressBar();
        }
    }
}
