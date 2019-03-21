package com.ISCAS.OneBeltOneRoad.dto;

import com.ISCAS.OneBeltOneRoad.entity.SystemUser;
import com.ISCAS.OneBeltOneRoad.enums.UserStateEnum;

import java.util.List;

public class UserExecution {
    //状态
    private int state;
    //状态标记
    private String stateInfo;
    //用户数量
    private int count;
    //操作的用户
    SystemUser user;
    //用户列表
    private List<SystemUser> userList;
    public UserExecution(){

    }
    //用户操作失败时的构造函数
    public UserExecution(UserStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }
    //用户增删改成功时使用的构造函数
    public UserExecution(UserStateEnum stateEnum, SystemUser user){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.user = user;
    }
    //用户列表查询成功时使用的构造函数
    public UserExecution(UserStateEnum stateEnum, List<SystemUser> userList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.userList = userList;
    }
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
    }

    public List<SystemUser> getUserList() {
        return userList;
    }

    public void setUserList(List<SystemUser> userList) {
        this.userList = userList;
    }
}
