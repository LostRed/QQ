package com.zyjy.qq.pojo;

public class User {
    private int userID;
    private String nike;
    private String password;
    private String sex;

    public User() {
    }

    public User(int userID, String nike, String password, String sex) {
        this.userID = userID;
        this.nike = nike;
        this.password = password;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return nike;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getNike() {
        return nike;
    }

    public void setNike(String nike) {
        this.nike = nike;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
