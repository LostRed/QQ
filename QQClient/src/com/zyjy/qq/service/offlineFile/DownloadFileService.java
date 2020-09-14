package com.zyjy.qq.service.offlineFile;

import com.zyjy.qq.net.RecvThread;
import com.zyjy.qq.net.offlineFile.DownloadFileThread;
import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.pojo.User;
import com.zyjy.qq.service.InService;
import com.zyjy.qq.view.ChatView;
import com.zyjy.qq.view.FriendView;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DownloadFileService implements InService {

    @Override
    public void doService(RecvThread handle) {
        //解析JSON
        JSONObject jsonObject = handle.getJsonObject();
        JSONArray jsonArray = jsonObject.getJSONArray("fileInfoList");
        Collection<SendFile> collection = JSONArray.toCollection(jsonArray, SendFile.class);
        List<SendFile> list = new ArrayList<>(collection);
        //遍历离线文件信息集合
        for (SendFile sendFile : list) {
            User sendUser = sendFile.getSendUser();
            int choice = JOptionPane.showConfirmDialog(FriendView.getFriendView(),
                    "是否接收" + sendUser.getNike() + "发送的" + sendFile.getFileInfo().getFileName() +
                            "(" + sendFile.getFileInfo().getFileSize() / 1024 + " KB)文件？",
                    "提示", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                //开启下载文件的线程
                ChatView cv = FriendView.getFriendView().ejectChatView(sendUser);
                new DownloadFileThread(cv, sendFile).start();
            }
        }
    }
}
