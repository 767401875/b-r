package com.ISCAS.OneBeltOneRoad.service.impl;

import com.ISCAS.OneBeltOneRoad.dao.Gis.GisMenuDao;
import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotationMenuItem;
import com.ISCAS.OneBeltOneRoad.entity.br.BrMaps;
import com.ISCAS.OneBeltOneRoad.entity.br.BrHomeMenu;
import com.ISCAS.OneBeltOneRoad.service.GisMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GisMenuServiceImpl implements GisMenuService {
    @Autowired
    GisMenuDao gisMenuDao;
    @Override
    public List<BrHomeMenu> getHomeMenuAll() {
        return gisMenuDao.queryHomeMenuAll();
    }

    @Override
    public BrHomeMenu getHomeMenu() {
        return null;
    }

    @Override
    public List<BrMaps> getBrMaps() {
        return gisMenuDao.queryBrMapsAll();
    }

    @Override
    public List<BrAnnotationMenuItem> getBrAnnotationMenuItems() {
        return gisMenuDao.queryBrAnnotationsAll();
    }

    @Override
    public Integer getSubNumBrAnnotations(Integer parentId) {
        return gisMenuDao.querySubNumBrAnnotations(parentId);
    }

    @Override
    public Integer getSubNumBrFirstLevelAnnotationsMenu() {
        return gisMenuDao.querySubNumBrFirstLevelAnnotationsMenu();
    }

    @Override
    public Integer getMenuItemCount() {
        return gisMenuDao.queryMenuItemCount();
    }

    @Override
    public Integer addBrAnnotationMenuItem(BrAnnotationMenuItem brAnnotationMenuItem) {
        return gisMenuDao.insertBrAnnotationMenuItem(brAnnotationMenuItem);
    }

    @Override
    public Integer modifyBrAnnotationMenuItem(BrAnnotationMenuItem brAnnotationMenuItem) {
        return gisMenuDao.updateBrAnnotationMenuItem(brAnnotationMenuItem);
    }

    @Override
    public Integer removeBrAnnotationMenuItem(Integer id) {
        return gisMenuDao.deleteBrAnnotationMenuItem(id);
    }

    @Override
    public BrAnnotationMenuItem getBrAnnotationMenuItemById(Integer id) {
        return gisMenuDao.selectBrAnnotationMenuItemById(id);
    }

    @Override
    public Integer removeBatchBrAnnotationMenuItem(Integer[] ids) {
        return gisMenuDao.deleteBatchBrAnnotationMenuItem(ids);
    }
}
