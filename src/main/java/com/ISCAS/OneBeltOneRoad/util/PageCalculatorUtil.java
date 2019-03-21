package com.ISCAS.OneBeltOneRoad.util;

public class PageCalculatorUtil {
    public static int rowIndexCalculate(int pageIndex, int pageSize){
        return pageIndex > 0 ? (pageIndex - 1) * pageSize : 0;
    }
}
