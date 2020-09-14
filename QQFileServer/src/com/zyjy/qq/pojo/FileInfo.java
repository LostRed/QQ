package com.zyjy.qq.pojo;

public class FileInfo {
    private int fileId;
    private String fileName;
    private String filePath;
    private long fileSize;
    private String MD5;
    private String host;
    private int port;

    public FileInfo() {
    }

    public FileInfo(int fileId, String fileName, String filePath, long fileSize, String MD5, String host, int port) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.MD5 = MD5;
        this.host = host;
        this.port = port;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMD5() {
        return MD5;
    }

    public void setMD5(String MD5) {
        this.MD5 = MD5;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
