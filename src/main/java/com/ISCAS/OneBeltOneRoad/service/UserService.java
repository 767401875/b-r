package com.ISCAS.OneBeltOneRoad.service;

import com.ISCAS.OneBeltOneRoad.dto.ImageHolder;
import com.ISCAS.OneBeltOneRoad.dto.UserExecution;
import com.ISCAS.OneBeltOneRoad.entity.SystemUser;


//Service层
public interface UserService {
    //添加用户
    UserExecution addUser(SystemUser user, ImageHolder thumbnail);
    //通过ID得到用户类
    SystemUser getById(long id);
    //修改用户
    UserExecution modefiyUser(SystemUser user, ImageHolder thumbnail);
    //获得用户列表
    UserExecution getUserList(SystemUser userCondition, int pageIndex, int pageSize);
    //通过名字来获得用户
    SystemUser getUserByLoginName(String loginName);
    SystemUser getUserByLogin(String loginName, String password);
    SystemUser getUserByEmail(String email);
    SystemUser getUserByPhone(String phone);
}
