package pers.summer.entity;

import java.util.Date;

public class User {
    private Integer uid;
    private String userName;
    private String email;
    private String password;
    private Integer state;
    private Date dateJoined;
    private Date dateLastModified;
    private int canRead;

    public User() {
    }

    public User(Integer uid, String userName, String email, String password, Integer state,
                Date joinDate, Date modifyDate, Integer canRead) {
        this.uid = uid;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.state = state;
        this.dateJoined = joinDate;
        this.dateLastModified = modifyDate;
        this.canRead = canRead;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public int getCanRead() {
        return canRead;
    }

    public void setCanRead(int canRead) {
        this.canRead = canRead;
    }

}
