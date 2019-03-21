package com.ISCAS.OneBeltOneRoad.common;

import com.ISCAS.OneBeltOneRoad.entity.SystemUser;

import javax.servlet.http.HttpServletRequest;

public class RequestHolder {
    private static final ThreadLocal<SystemUser> userHolder = new ThreadLocal<>();
    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();
    public static void add(SystemUser systemUser){
        userHolder.set(systemUser);
    }
    public static void add(HttpServletRequest request){
        requestHolder.set(request);
    }
    public static SystemUser getCurrentUser(){
        return userHolder.get();
    }
    public static HttpServletRequest getCurrentRequest(){
        return requestHolder.get();
    }
    public static void remove(){
        userHolder.remove();
        requestHolder.remove();
    }
}
