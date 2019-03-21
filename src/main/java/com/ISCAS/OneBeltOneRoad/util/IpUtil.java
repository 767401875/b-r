package com.ISCAS.OneBeltOneRoad.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpUtil {
    public final static String ERROR_IP = "127.0.0.1";
    public final static Pattern pattern = Pattern.
            compile("(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})");

    /**
     * 取外网IP
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request){
        String ip = request.getHeader("x-real-ip");
        if(ip == null){
            ip = request.getRemoteAddr();
        }
        //过滤反向代理的ip
        String[] stamp = ip.split(",");
        if(stamp != null&&stamp.length >= 1){
            //得到第一个IP，即客户端真实IP
            ip = stamp[0];
        }
        ip = ip.trim();
        if(ip.length() > 23){
            ip = ip.substring(0, 23);
        }
        return ip;
    }
    /**
     * 获取用户的真实IP
     * @param request
     * @return
     */
    public static String getUserIp(HttpServletRequest request){
        //获取X-Real-IP
        String ip = request.getHeader("X-Real-IP");
        if(ip == null||ip.length() == 0||"unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("x-forwarded-for");
        }
        if(ip == null||ip.length() == 0||"unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
            if("0:0:0:0:0:0:0:1".equals(ip)){
                ip = ERROR_IP;
            }
        }
        if("unknown".equalsIgnoreCase(ip)){
            ip = ERROR_IP;
            return ip;
        }
        int pos = ip.indexOf(",");
        if(pos >= 0){
            ip = ip.substring(0, pos);
        }
        return ip;
    }
    public static String getLastIpSegment(HttpServletRequest request){
        String ip = getUserIp(request);
        if(ip != null){
            ip = ip.substring(ip.lastIndexOf(".") + 1);
        }
        else {
            ip = "0";
        }
        return ip;
    }
    public static boolean isValidIP(HttpServletRequest request){
        String ip = getUserIp(request);
        return isValidIP(ip);
    }
    public static boolean isValidIP(String ip){
        if(StringUtils.isEmpty(ip)){
            System.out.println("空IP.结果是错误的!");
            return false;
        }
        Matcher matcher = pattern.matcher(ip);
        boolean isValid = matcher.matches();
        System.out.println("有效IP：" + ip + " 结果是：" + isValid);
        return isValid;
    }
    public static String getLastServerIpSegment(){
        String ip = getServerIP();
        if(ip != null){
            ip = ip.substring(ip.lastIndexOf(".") + 1);
        }
        else {
            ip = "0";
        }
        return ip;
    }
    public static String getServerIP(){
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getLocalHost();
            String hostAddress = inetAddress.getHostAddress();
            return hostAddress;
        }catch (UnknownHostException e){
            e.printStackTrace();
        }
        return "127.0.0.1";
    }
    public static void main(String[] args){
        String ip = "127.0.0.113";
        int lastIndex = ip.lastIndexOf(".");
        System.out.println(lastIndex);
        ip = ip.substring(lastIndex + 1);
        System.out.println(ip);
    }
}

