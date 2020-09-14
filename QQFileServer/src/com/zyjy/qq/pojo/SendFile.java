package com.zyjy.qq.pojo;

public class SendFile {
    private int fileInfoId;
    private User sendUser;
    private User recvUser;
    private FileInfo fileInfo;
    private String time;

    public SendFile() {
    }

    public SendFile(int fileInfoId, User sendUser, User recvUser, FileInfo fileInfo, String time) {
        this.fileInfoId = fileInfoId;
        this.sendUser = sendUser;
        this.recvUser = recvUser;
        this.fileInfo = fileInfo;
        this.time = time;
    }

    public int getFileInfoId() {
        return fileInfoId;
    }

    public void setFileInfoId(int fileInfoId) {
        this.fileInfoId = fileInfoId;
    }

    public User getSendUser() {
        return sendUser;
    }

    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }

    public User getRecvUser() {
        return recvUser;
    }

    public void setRecvUser(User recvUser) {
        this.recvUser = recvUser;
    }

    public FileInfo getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
