package com.ISCAS.OneBeltOneRoad.dao;

import com.ISCAS.OneBeltOneRoad.BaseTest;
import com.ISCAS.OneBeltOneRoad.dao.Gis.GisMenuDao;
import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotationData;
import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotations;
import com.ISCAS.OneBeltOneRoad.entity.br.BrMaps;
import com.ISCAS.OneBeltOneRoad.entity.data.AnnotationData;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GisMenuDaoTest extends BaseTest {
    @Autowired
    GisMenuDao gisMenuDao;
    @Test
    @Ignore
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
    public void querySubNumBrAnnotationsTest(){
        Integer count = gisMenuDao.querySubNumBrAnnotations(47);
        System.out.println(count);
    }
    @Test
    @Ignore
    public void querySubNumBrFirstLevelAnnotationsMenuTest(){
        Integer count = gisMenuDao.querySubNumBrFirstLevelAnnotationsMenu();
        System.out.println(count);
    }
}
