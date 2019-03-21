package com.ISCAS.OneBeltOneRoad.util;

public class PathUtil {
    private static String seperator = System.getProperty("file.seperator");
    public static String getPicBasePath(){
        String basePath = "";
        //运行时的根目录 E:\apache-tomcat-7.0.82-windows-x64\apache-tomcat-7.0.82\webapps\ROOT\
        basePath = System.getProperty("BeltRoad.webapp");
//        basePath.replace("\\", seperator);
        return basePath;
    }
    public static String getUserPicRelativePath(Long id){
        //图片存在的相对路径
        String relativePath = "\\upload\\pic\\User" + id + "\\";
        return relativePath;
    }
    public static String getUserPicPath(Long id){
        //图片存在的相对路径
        String path = getPicBasePath() + "upload\\pic\\User\\" + id + "\\";
        PictureFileUtil.mkPictureDir(path);
        return path;
    }
}
