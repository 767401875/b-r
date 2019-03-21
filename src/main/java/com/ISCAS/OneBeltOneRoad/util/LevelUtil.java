package com.ISCAS.OneBeltOneRoad.util;

import org.apache.commons.lang3.StringUtils;

public class LevelUtil{
    public final static String SEPARATOR = ".";
    public final static String ROOT = "0";
    //0
    //0.1
    //0.1.2
    //0.1.3
    //0.2
    public static String calculateLevel(String parentLevel, int parentId){
        if(StringUtils.isBlank(parentLevel)){
            return ROOT;
        }
        else {
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }
    public static void main(String[] args){
        String parentLevel = "0.1";
        int parentId = 3;
        String str = calculateLevel(parentLevel, parentId);
        System.out.println(str);
    }
}
