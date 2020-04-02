package cn.brownqi.model;

import java.sql.Date;

public class User {

    private Integer userId;
    private String uName;
    private String uPwd;
    private Date uBirth;

    public User() {
    }

    public User(Integer userId, String uName, String uPwd, Date uBirth) {
        this.userId = userId;
        this.uName = uName;
        this.uPwd = uPwd;
        this.uBirth = uBirth;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPwd() {
        return uPwd;
    }

    public void setuPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    public Date getuBirth() {
        return uBirth;
    }

    public void setuBirth(Date uBirth) {
        this.uBirth = uBirth;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", uName='" + uName + '\'' +
                ", uPwd='" + uPwd + '\'' +
                ", uBirth=" + uBirth +
                '}';
    }
}
