package com.ISCAS.OneBeltOneRoad.dao;

import com.ISCAS.OneBeltOneRoad.BaseTest;
import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotations;
import com.ISCAS.OneBeltOneRoad.entity.br.BrMaps;
import com.ISCAS.OneBeltOneRoad.entity.br.BrHomeMenu;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GisMenuDaoTest extends BaseTest {
    @Autowired
    GisMenuDao gisMenuDao;
    @Test
    public void queryMenuItemCount(){
        System.out.println(gisMenuDao.queryMenuItemCount());
    }

    @Test
    @Ignore
    public void queryBrMapsTest(){
        List<BrMaps> brMapsList = gisMenuDao.queryBrMapsAll();
        for(BrMaps brMaps : brMapsList){
            System.out.println(brMaps.getAnnotation());
        }
    }
    @Test
    @Ignore
    public void queryBrAnnotationsAllTest(){
        List<BrAnnotations> brAnnotationsList = gisMenuDao.queryBrAnnotationsAll();
        int index = 1;
        for(BrAnnotations brAnnotations : brAnnotationsList){
//            if(brAnnotations.getName() == null)
            if(brAnnotations.getAnnotationRefId() == null&&brAnnotations.getParentId() == null)
             System.out.println(index++ + ": " + brAnnotations.getCaption());
        }
    }
    @Test
    @Ignore
    public void querySubNumBrAnnotationsTest(){
        Integer count = gisMenuDao.querySubNumBrAnnotations(47);
        System.out.println(count);
    }
    @Test
    public void querySubNumBrFirstLevelAnnotationsMenuTest(){
        Integer count = gisMenuDao.querySubNumBrFirstLevelAnnotationsMenu();
        System.out.println(count);
    }

}
