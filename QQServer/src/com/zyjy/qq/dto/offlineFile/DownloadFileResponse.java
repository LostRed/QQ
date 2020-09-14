package com.zyjy.qq.dto.offlineFile;

import com.zyjy.qq.pojo.SendFile;
import com.zyjy.qq.util.JsonUtil;

import java.util.List;

public class DownloadFileResponse {
    private String type = JsonUtil.DOWNLOAD_FILE;
    private List<SendFile> sendFileList;

    public DownloadFileResponse() {
    }

    public DownloadFileResponse(List<SendFile> sendFileList) {
        this.sendFileList = sendFileList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SendFile> getFileInfoList() {
        return sendFileList;
    }

    public void setFileInfoList(List<SendFile> sendFileList) {
        this.sendFileList = sendFileList;
    }
}
