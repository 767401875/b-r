package com.ISCAS.OneBeltOneRoad.dao;

import com.ISCAS.OneBeltOneRoad.BaseTest;
import com.ISCAS.OneBeltOneRoad.entity.SystemUser;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class UserDaoTest extends BaseTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void insertUser(){
        SystemUser user = new SystemUser();
        user.setName("王五");
        user.setSex("男");
        user.setEmail("qweasdf13@qq.com");
        user.setPhone("789456231");
        user.setPassword("789456231");
        user.setBirthday(new Date());
        user.setLoginName("wangwu");
        user.setPicture("C:\\Users\\qsq\\Desktop\\gis\\1515048493934.jpg");
        userDao.insertUser(user);
    }
    @Test
    @Ignore
    public void updateUser(){
        SystemUser user = new SystemUser();
        user.setId(5);
        user.setCreateTime(new Date());
        user.setLastEditTime(new Date());
        user.setEnableStatus(1);
        user.setPriority(20);
        userDao.updateUser(user);
    }
    @Test
    @Ignore
    public void queryUserById(){
        long id = 5;
        SystemUser user = userDao.queryUserById(id);
        System.out.println(user.getName());
        System.out.println(user.getSex());
        System.out.println(user.getEmail());
        System.out.println(user.getPhone());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        System.out.println(user.getLoginName());
        System.out.println(user.getPicture());
    }
    @Test
    @Ignore
    public void queryUserList(){
        List<SystemUser> userList = userDao.queryUserList(null, 0, 10);
        for(SystemUser user :userList){
            System.out.println(user.getName());
        }
    }
    @Test
    @Ignore
    public void queryUserCount(){
        int count = userDao.queryUserCount(null);
        System.out.println(count);
    }
    @Test
    @Ignore
    public void queryUserByName(){
        SystemUser user = userDao.queryUserByLoginName("我的多啦不能没有A梦");
        System.out.println(user.getPassword());
    }
    @Test
    @Ignore
    public void queryUserByEmail(){
        SystemUser user = userDao.queryUserByEmail("767401882@qq.com");
        System.out.println(user.getName());
    }
    @Test
    @Ignore
    public void queryUserByPhone(){
        SystemUser user = userDao.queryUserByPhone("1234567895");
        System.out.println(user.getBirthday());
    }
    @Test
    @Ignore
    public void queryUserByLogin(){
        String login_name = "qsq";
        String password = "1234";
        SystemUser user = userDao.queryUserByLogin(login_name, password);
        System.out.println(user.getName());
    }

}
