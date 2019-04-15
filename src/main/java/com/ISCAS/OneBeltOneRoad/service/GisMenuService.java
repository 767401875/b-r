package com.ISCAS.OneBeltOneRoad.service;

import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotationData;
import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotations;
import com.ISCAS.OneBeltOneRoad.entity.br.BrMaps;
import com.ISCAS.OneBeltOneRoad.entity.br.BrHomeMenu;

import java.util.List;

public interface GisMenuService {
    List<BrHomeMenu>getHomeMenuAll();
    BrHomeMenu getHomeMenu();
    List<BrMaps> getBrMaps();
    List<BrAnnotations> getBrAnnotations();
    Integer getSubNumBrAnnotations(Integer parentId);
    Integer getSubNumBrFirstLevelAnnotationsMenu();
    Integer getMenuItemCount();

}
