package com.demo.pojo;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String sex;
    private String birthday;
    private String QQ;
    private String myself;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getMyself() {
        return myself;
    }

    public void setMyself(String myself) {
        this.myself = myself;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", QQ='" + QQ + '\'' +
                ", myself='" + myself + '\'' +
                '}';
    }
}
