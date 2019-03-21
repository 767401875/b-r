package com.ISCAS.OneBeltOneRoad.dao;

import com.ISCAS.OneBeltOneRoad.entity.SystemUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    //插入新用户
    int insertUser(SystemUser user);
    //更新新用户
    int updateUser(SystemUser user);
    //根据用户ID查询用户
    SystemUser queryUserById(long id);
    //返回用户列表
    List<SystemUser> queryUserList(@Param("userCondition")SystemUser user, @Param("rowIndex")int rowIndex, @Param("pageSize")int pageSize);
    //查询用户数量
    int queryUserCount(@Param("userCondition")SystemUser user);
    SystemUser queryUserByLoginName(String login_name);
    SystemUser queryUserByEmail(String email);
    SystemUser queryUserByPhone(String phone);
    SystemUser queryUserByLogin(@Param("login_name") String login_name, @Param("password") String password);
}
