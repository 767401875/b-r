package com.ISCAS.OneBeltOneRoad.dao;

import com.ISCAS.OneBeltOneRoad.BaseTest;
import com.ISCAS.OneBeltOneRoad.dao.Gis.GisMenuDao;
import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotationMenuItem;
import com.ISCAS.OneBeltOneRoad.entity.br.BrMaps;
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
    @Test
    @Ignore
    public void insertBrAnnotationMenuItemTest(){
        BrAnnotationMenuItem brAnnotationMenuItem = new BrAnnotationMenuItem();
        brAnnotationMenuItem.setCaption("秦慎强");
        brAnnotationMenuItem.setDescription("qsq");
        brAnnotationMenuItem.setAnnotationRefId(1);
        brAnnotationMenuItem.setParentId(1);
        brAnnotationMenuItem.setName("qsq");
        int count = gisMenuDao.insertBrAnnotationMenuItem(brAnnotationMenuItem);
        System.out.println(count);
    }
    @Test
    @Ignore
    public void updateBrAnnotationMenuItemTest(){
        BrAnnotationMenuItem brAnnotationMenuItem = new BrAnnotationMenuItem();
        brAnnotationMenuItem.setId(53);
        brAnnotationMenuItem.setCaption("李婷婷");
        brAnnotationMenuItem.setDescription("夫人");
        brAnnotationMenuItem.setTimeRelated(true);
        brAnnotationMenuItem.setShowUp(true);
        brAnnotationMenuItem.setAnnotationRefId(2);
        brAnnotationMenuItem.setParentId(2);
        brAnnotationMenuItem.setName("ltt");
        int count = gisMenuDao.updateBrAnnotationMenuItem(brAnnotationMenuItem);
        System.out.println(count);

    }
    @Test
    @Ignore
    public void deleteBrAnnotationMenuItemTest(){
        Integer id = 53;
        int count = gisMenuDao.deleteBrAnnotationMenuItem(id);
        System.out.println(count);

    }
    @Test
    @Ignore
    public void selectBrAnnotationMenuItemByIdTest(){
        Integer id = 50;
        BrAnnotationMenuItem brAnnotationMenuItem = gisMenuDao.selectBrAnnotationMenuItemById(50);
        System.out.println(brAnnotationMenuItem.getCaption());
    }
    @Test
    public void deleteBatchBrAnnotationMenuItemTest(){
        Integer[] ids = {73, 74};
        Integer count = gisMenuDao.deleteBatchBrAnnotationMenuItem(ids);
        System.out.println(count);
    }
}
