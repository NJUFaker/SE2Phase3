package com.example.cinema.vo;

import com.example.cinema.po.ManagerPO;

public class ManagerVO {
    private String username;
    private String password;
    private Integer managerID;

    public ManagerVO(ManagerPO managerPO){
        this.managerID=managerPO.getManagerID();
        this.password=managerPO.getPassword();
        this.username=managerPO.getUsername();
    }
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

    public Integer getManagerID() {
        return managerID;
    }

    public void setManagerID(Integer managerID) {
        this.managerID = managerID;
    }
}
