package com.ISCAS.OneBeltOneRoad.util;

import com.ISCAS.OneBeltOneRoad.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PictureUtil {
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    public static String generationNormalThumbnail(ImageHolder thumbnail, String targetAddr){
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail.getImageName());
//        PictureFileUtil.mkPictureDir(targetAddr);
        String addr = targetAddr + realFileName + extension;
        File destFile = new File(addr);
        try {
            Thumbnails.of(thumbnail.getImage()).size(400, 400).outputQuality(0.8f).toFile(destFile);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return addr;
    }
    public static String getRandomFileName(){
        int randum = new Random().nextInt(89999) + 100000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + randum;
    }
    public static String getFileExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
