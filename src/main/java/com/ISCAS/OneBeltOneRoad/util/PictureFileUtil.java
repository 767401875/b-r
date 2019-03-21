package com.ISCAS.OneBeltOneRoad.util;

import java.io.File;

public class PictureFileUtil {
    public static void mkPictureDir(String path){
        File file  = new File(path);
        if(!file.exists()&&!file.isDirectory())
            file.mkdirs();
    }
    public static boolean deleteFileOrPath(String path){
        File fileOrPath = new File(path);
        if(fileOrPath.exists()){
            if(fileOrPath.isDirectory()){
                File[] file = fileOrPath.listFiles();
                for(int i = 0; i < file.length; i++) {
                    file[i].delete();
                }
            }
            fileOrPath.delete();
            return true;
        }
        return false;
    }
}
