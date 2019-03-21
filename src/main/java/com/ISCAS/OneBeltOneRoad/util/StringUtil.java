package com.ISCAS.OneBeltOneRoad.util;

import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    public static List<Integer> splitToListInteger(String str){
        List<Integer> strList = new ArrayList<>();
        String[] strings = str.split(",");
        for(String string : strings){
            strList.add(Integer.parseInt(string.trim()));
        }
        return strList;
    }
}
