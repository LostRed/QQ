package com.zyjy.qq.pojo;

public class Chat {
    private int ChatId;
    private User sendUser;
    private User recvUser;
    private String message;
    private String time;

    public Chat() {
    }

    public Chat(int ChatId, User sendUser, User recvUser, String message, String time) {
        this.ChatId = ChatId;
        this.sendUser = sendUser;
        this.recvUser = recvUser;
        this.message = message;
        this.time = time;
    }

    public int getChatId() {
        return ChatId;
    }

    public void setChatId(int chatId) {
        this.ChatId = chatId;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
