package com.ISCAS.OneBeltOneRoad.service.impl;

import com.ISCAS.OneBeltOneRoad.dao.GisMenuDao;
import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotations;
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
    public List<BrAnnotations> getBrAnnotations() {
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
}
