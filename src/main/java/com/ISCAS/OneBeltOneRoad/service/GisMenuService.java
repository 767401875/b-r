package com.ISCAS.OneBeltOneRoad.service;

import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotationMenuItem;
import com.ISCAS.OneBeltOneRoad.entity.br.BrMaps;
import com.ISCAS.OneBeltOneRoad.entity.br.BrHomeMenu;

import java.util.List;

public interface GisMenuService {
    List<BrHomeMenu> getHomeMenuAll();
    BrHomeMenu getHomeMenu();
    List<BrMaps> getBrMaps();
    List<BrAnnotationMenuItem> getBrAnnotationMenuItems();
    Integer getSubNumBrAnnotations(Integer parentId);
    Integer getSubNumBrFirstLevelAnnotationsMenu();
    Integer getMenuItemCount();
    Integer addBrAnnotationMenuItem(BrAnnotationMenuItem brAnnotationMenuItem);
    Integer modifyBrAnnotationMenuItem(BrAnnotationMenuItem brAnnotationMenuItem);
    Integer removeBrAnnotationMenuItem(Integer id);
    Integer removeBatchBrAnnotationMenuItem(Integer[] ids);
    BrAnnotationMenuItem getBrAnnotationMenuItemById(Integer id);
}
