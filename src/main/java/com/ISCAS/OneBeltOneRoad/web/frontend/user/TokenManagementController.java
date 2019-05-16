package com.ISCAS.OneBeltOneRoad.web.frontend.user;

import com.ISCAS.OneBeltOneRoad.entity.SystemUser;
import com.ISCAS.OneBeltOneRoad.service.UserService;
import com.ISCAS.OneBeltOneRoad.util.HttpServletRequestUtil;
import com.ISCAS.OneBeltOneRoad.util.JWTUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
/*
 * 时间：2018/12/28
 * 作者：秦慎强
 */
@Controller
@RequestMapping(value = "/api/token", method = RequestMethod.POST)
public class TokenManagementController {
    @Autowired
    UserService userService;
//  将username和frensh、access进行映射
    private static Map<String, Map<String, String>> userTokenMap = new HashMap<>();
    /*
     * username：用户名 对应数据库中的loginName
     * password：密码
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getToken(@Param("username") String username, @Param("password") String password, HttpServletResponse response){
        Map<String, Object> modelMap = new HashMap<>();
        Map<String, String> tokenMap = new HashMap<>();
        if(username == null||password == null){
            modelMap.put("errMsg", "用户信息错误");
            response.setHeader("code", "401");
            response.setStatus(401);
            return null;
        }
        SystemUser systemUser = userService.getUserByLogin(username, password);
        if(systemUser != null){
            String access = JWTUtil.createAccessJwtToken(systemUser);
            String refresh = JWTUtil.createRefreshToken(systemUser);
            tokenMap.put(refresh, access);
            userTokenMap.put(username, tokenMap);
            //短期Token
            modelMap.put("access", access);
            //长期Token
            modelMap.put("refresh", refresh);
            modelMap.put("user", systemUser);
            return modelMap;
        }
        else {
            modelMap.put("errMsg", "异常用户");
            response.setHeader("code", "401");
            response.setStatus(401);
            return null;
        }
    }
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> refreshToken(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> modelMap = new HashMap<>();
        Map<String, String> tokenMap = new HashMap<>();
        String refresh = HttpServletRequestUtil.getString(request, "refresh");
        String name;
        if(refresh == null){
            modelMap.put("errMsg", "不包含refresh字段");
            response.setHeader("code", "403");
            response.setStatus(403);
            return null;
        }
//      查看登录用户中是否包含refreshToken
        boolean containRefresh = JWTUtil.containToken(refresh, userTokenMap, "refresh");
        name = JWTUtil.findName(refresh, userTokenMap);
        if(!containRefresh){
            response.setHeader("code", "403");
            response.setStatus(403);
            modelMap.put("errMsg", "refreshToken失效");
            return modelMap;
        }
        SystemUser user = JWTUtil.unsign(refresh, SystemUser.class);
        if(user == null){
            response.setHeader("code", "403");
            response.setStatus(403);
            modelMap.put("errMsg", "错误的refresh TOKEN");
            return null;
        }
        SystemUser systemUser = userService.getUserByLogin(user.getLoginName(), user.getPassword());
        if(systemUser != null){
            String access = JWTUtil.createAccessJwtToken(systemUser);
            modelMap.put("access", access);
            tokenMap.clear();
            tokenMap.put(refresh, access);
            userTokenMap.put(name, tokenMap);
            response.setHeader("code", "200");
            response.setStatus(200);
            response.setHeader("Authorization", "Bearer" + access);
        }
        else {
            response.setHeader("code", "403");
            response.setStatus(403);
            modelMap.put("errMsg", "异常用户");
            return null;
        }
        return modelMap;
    }
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> verifyToken(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<>();
        String refreshToken = HttpServletRequestUtil.getString(request, "refresh");
        String accessToken = HttpServletRequestUtil.getString(request, "access");
        String token = null;
        boolean containRefreshToken = false;
        if(refreshToken != null){
            containRefreshToken = JWTUtil.containToken(refreshToken, userTokenMap, "refresh");
            if(containRefreshToken){
                token = refreshToken;
            }
            else {
                modelMap.put("errMsg", "refreshToken失效");
                response.setHeader("code", "403");
                response.setStatus(403);
                return modelMap;
            }
        }
        if(accessToken != null){
            containRefreshToken = JWTUtil.containToken(accessToken, userTokenMap, "access");
            if(containRefreshToken){
                token = accessToken;
            }
            else {
                modelMap.put("errMsg", "accessToken失效");
                response.setHeader("code", "403");
                response.setStatus(403);
                return modelMap;
            }
        }
        if(token == null){
            modelMap.put("errMsg", "缺少refresh或access");
            response.setHeader("code", "400");
            response.setStatus(400);
            return modelMap;
        }
        Long expireTime = JWTUtil.getExpireTime(token);
        Long currentTime = System.currentTimeMillis();
        if(expireTime == null){
            modelMap.put("errMsg", "token中不包含过期时间");
            response.setHeader("code", "403");
            response.setStatus(403);
            return modelMap;
        }
        if(currentTime <= expireTime){
            response.setHeader("code", "200");
            return null;
        }
        else {
            modelMap.put("errMsg", "token时间过期");
            response.setHeader("code", "403");
            response.setStatus(403);
            return null;
        }
    }


}
