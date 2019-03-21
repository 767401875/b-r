package com.ISCAS.OneBeltOneRoad.dao;

import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotationData;
import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotations;
import com.ISCAS.OneBeltOneRoad.entity.br.BrMaps;
import com.ISCAS.OneBeltOneRoad.entity.br.BrHomeMenu;

import java.util.List;

public interface GisMenuDao {
    List<BrHomeMenu> queryHomeMenuAll();
    BrHomeMenu queryHomeMenuById();
    List<BrMaps> queryBrMapsAll();
    List<BrAnnotations> queryBrAnnotationsAll();
    Integer querySubNumBrAnnotations(Integer parentId);
    Integer querySubNumBrFirstLevelAnnotationsMenu();
    Integer queryMenuItemCount();
    BrAnnotationData queryBrAnnotationData(String name);
}
