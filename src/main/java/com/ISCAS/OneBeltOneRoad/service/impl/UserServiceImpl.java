package com.ISCAS.OneBeltOneRoad.service.impl;

import com.ISCAS.OneBeltOneRoad.dao.UserDao;
import com.ISCAS.OneBeltOneRoad.dto.ImageHolder;
import com.ISCAS.OneBeltOneRoad.dto.UserExecution;
import com.ISCAS.OneBeltOneRoad.entity.SystemUser;
import com.ISCAS.OneBeltOneRoad.enums.UserStateEnum;
import com.ISCAS.OneBeltOneRoad.exceptions.UserOperationException;
import com.ISCAS.OneBeltOneRoad.service.UserService;
import com.ISCAS.OneBeltOneRoad.util.PageCalculatorUtil;
import com.ISCAS.OneBeltOneRoad.util.PathUtil;
import com.ISCAS.OneBeltOneRoad.util.PictureFileUtil;
import com.ISCAS.OneBeltOneRoad.util.PictureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public UserExecution addUser(SystemUser user, ImageHolder thumbnail) {
        if(user == null){
            return new UserExecution(UserStateEnum.NULL_USER);
        }
        try {
            user.setCreateTime(new Date());
            user.setLastEditTime(new Date());
            user.setEnableStatus(0);
            int effectNum = userDao.insertUser(user);
            if(effectNum < 1){
                throw new UserOperationException("添加用户失败");
            }
            else {
                if(thumbnail.getImage() != null){
                    try {
                        addUserPic(user, thumbnail);
                    }
                    catch (Exception e){
                        throw new UserOperationException("添加用户图片失败");
                    }
                    effectNum = userDao.updateUser(user);
                    if(effectNum < 1){
                        throw new UserOperationException("在用户表中更新图片字典失败");
                    }
                }
            }
        }catch (Exception e){
            throw new UserOperationException("添加用户失败:" + e.getMessage());
        }
        return new UserExecution(UserStateEnum.Check, user);
    }
    void addUserPic(SystemUser user, ImageHolder thumbnail){
        String targetAddr = PathUtil.getUserPicPath(user.getId());
        String addr = PictureUtil.generationNormalThumbnail(thumbnail, targetAddr);
        user.setPicture(addr);
    }

    @Override
    public SystemUser getById(long id) {
        return userDao.queryUserById(id);
    }

    @Override
    public UserExecution modefiyUser(SystemUser user, ImageHolder thumbnail) {
        if(user == null){
            return new UserExecution(UserStateEnum.NULL_USER);
        }
        try {
            boolean temp = "".equals(thumbnail.getImageName());
            if(thumbnail.getImage() != null&&thumbnail.getImageName() != null&&!"".equals(thumbnail.getImageName())){
                SystemUser tempUser = userDao.queryUserById(user.getId());
                if(tempUser.getPicture() != null){
                    if(PictureFileUtil.deleteFileOrPath(tempUser.getPicture())){
                        addUserPic(user, thumbnail);
                    }
                }
            }
            user.setLastEditTime(new Date());
            int num = userDao.updateUser(user);
            if(num < 1){
                return new UserExecution(UserStateEnum.INNER_ERROR);
            }else {
                user = userDao.queryUserById(user.getId());
                return new UserExecution(UserStateEnum.SUCCESS, user);
            }
        }catch (Exception e){
            throw new UserOperationException("modefiyUser更新失败");
        }
    }

    @Override
    public UserExecution getUserList(SystemUser userCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculatorUtil.rowIndexCalculate(pageIndex, pageSize);
        List<SystemUser> userList = new ArrayList<>();
        int count = 0;
        UserExecution ue = new UserExecution();
        try{
            userList = userDao.queryUserList(userCondition, rowIndex, pageSize);
            count = userDao.queryUserCount(userCondition);
        }
        catch (Exception e){
            throw new UserOperationException("getUserList error!");
        }
        if(userList != null){
            ue = new UserExecution(UserStateEnum.SUCCESS, userList);
            ue.setCount(count);
        }else {
            ue.setState(UserStateEnum.INNER_ERROR.getState());
        }

        return ue;
    }

    @Override
    public SystemUser getUserByLoginName(String loginName) {
        return userDao.queryUserByLoginName(loginName);
    }

    @Override
    public SystemUser getUserByEmail(String email) {
        return userDao.queryUserByEmail(email);
    }

    @Override
    public SystemUser getUserByPhone(String phone) {
        return userDao.queryUserByPhone(phone);
    }

    @Override
    public SystemUser getUserByLogin(String loginName, String password) {
        return userDao.queryUserByLogin(loginName, password);
    }
}
