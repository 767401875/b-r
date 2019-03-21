package com.ISCAS.OneBeltOneRoad.web.user;

import com.ISCAS.OneBeltOneRoad.dto.ImageHolder;
import com.ISCAS.OneBeltOneRoad.dto.ResponseData;
import com.ISCAS.OneBeltOneRoad.dto.UserExecution;
import com.ISCAS.OneBeltOneRoad.entity.SystemUser;
import com.ISCAS.OneBeltOneRoad.enums.UserStateEnum;
import com.ISCAS.OneBeltOneRoad.service.UserService;
import com.ISCAS.OneBeltOneRoad.util.HttpServletRequestUtil;
import com.ISCAS.OneBeltOneRoad.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/usermanage", method = RequestMethod.GET)
public class UserManagementController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/registeruser", method = RequestMethod.POST)
    private Map<String, Object> registerUser(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        //1.验证码
        //2.接受店铺信息
        String userStr = HttpServletRequestUtil.getString(request, "userStr");
        ObjectMapper mapper = new ObjectMapper();
        SystemUser user = null;
        try {
            user = mapper.readValue(userStr, SystemUser.class);
            //登录名是未用的
            SystemUser temp = userService.getUserByLoginName(user.getLoginName());
            if(temp != null){
                modelMap.put("success", false);
                modelMap.put("errMsg", "登录名电话已注册");
                return modelMap;
            }
            //电话是未注册的
            temp = userService.getUserByPhone(user.getPhone());
            if(temp != null){
                modelMap.put("success", false);
                modelMap.put("errMsg", "电话已注册");
                return modelMap;
            }
            //电子邮箱是未注册的
            temp = userService.getUserByPhone(user.getPhone());
            if(temp != null){
                modelMap.put("success", false);
                modelMap.put("errMsg", "电子邮箱已注册");
                return modelMap;
            }
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        //3.接受用户图片
        CommonsMultipartFile userPicture = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if(commonsMultipartResolver.isMultipart(request)){
            try{
                MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
                userPicture = (CommonsMultipartFile)multipartHttpServletRequest.getFile("userPicture");
            }
            catch (Exception e){
                modelMap.put("success", false);
                modelMap.put("errMsg","注册图片不能为空");
                return modelMap;
            }
        }
        //4.注册用户信息
        if(user!=null&&userPicture!=null){
            try{
                ImageHolder thumbnail = new ImageHolder(userPicture.getInputStream(), userPicture.getOriginalFilename());
                UserExecution ue = userService.addUser(user, thumbnail);
                if(ue.getState() == UserStateEnum.Check.getState()){
                    modelMap.put("success", true);
                }
            }catch (IOException e){
                modelMap.put("success", false);
                modelMap.put("errMsg", "CommonsMultipartFile通过getInputStream转换成流文件失败");
            }

        }
        return modelMap;
    }
    @RequestMapping(value = "/getuserbyid", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getUserById(HttpServletRequest request, @RequestParam("userId")long id){
        Map<String, Object> modelMap = new HashMap<>();
        SystemUser user = userService.getById(id);
        if(user != null){
            modelMap.put("user", user);
            modelMap.put("success", true);
        }else {
            modelMap.put("success", false);
            modelMap.put("errMsg","用户为空");
        }
        return modelMap;
    }
    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> editUser(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        //1.处理user
        String userStr = HttpServletRequestUtil.getString(request, "userStr");
        ObjectMapper mapper = new ObjectMapper();
        SystemUser user = null;
        try {
            user = mapper.readValue(userStr, SystemUser.class);
        }catch (IOException e){
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        //2.处理userPicture
        CommonsMultipartFile userPicture = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if(commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
            userPicture = (CommonsMultipartFile)multipartHttpServletRequest.getFile("userPicture");
        }
        //3.更新用户
        if(user != null&&(Long)user.getId()!=null){
            try {
                UserExecution userExecution;
                if(userPicture == null){
                    userExecution = userService.modefiyUser(user, null);
                }
                else {
                    ImageHolder imageHolder = new ImageHolder(userPicture.getInputStream(), userPicture.getOriginalFilename());
                    userExecution = userService.modefiyUser(user, imageHolder);
                }
                if(userExecution.getState() == UserStateEnum.SUCCESS.getState()){
                    modelMap.put("success", true);
                }
                else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", userExecution.getStateInfo());
                }
            }catch (IOException e){
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }
        }
        else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "空User或userId");
        }
        return modelMap;
    }
    /*
     *前台user封装了账号和密码
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    private ResponseData loginUser(HttpServletRequest request, HttpServletResponse response){
        //1.接受用户信息
        String userStr = HttpServletRequestUtil.getString(request, "user");
        ObjectMapper mapper = new ObjectMapper();
        SystemUser user = null;
        ResponseData responseData = ResponseData.ok();
        try {
            user = mapper.readValue(userStr, SystemUser.class);
            //通过名字和密码进行登录
            SystemUser systemUser = userService.getUserByLogin(user.getLoginName(), user.getPassword());
            Long id = systemUser.getId();
            if(id != null){
                String access = JWTUtil.createAccessJwtToken(systemUser);
                String refresh = JWTUtil.createRefreshToken(systemUser);
                //短期Token
                responseData.putDataValue("access", access);
                //长期Token Todo
                responseData.putDataValue("refresh", refresh);
                responseData.putDataValue("user", systemUser);
                response.setHeader("code", "" + responseData.getCode());
                response.setHeader("Authorization", "Bearer" + access);
            }else {
                responseData = ResponseData.customerError();
            }

        }catch (Exception e){
            responseData = ResponseData.customerError();
        }
        return responseData;
    }



}
