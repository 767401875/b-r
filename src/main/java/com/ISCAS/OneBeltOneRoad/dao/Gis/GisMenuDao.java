package com.ISCAS.OneBeltOneRoad.dao.Gis;

import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotationMenuItem;
import com.ISCAS.OneBeltOneRoad.entity.br.BrMaps;
import com.ISCAS.OneBeltOneRoad.entity.br.BrHomeMenu;

import java.util.List;

public interface GisMenuDao {
    List<BrHomeMenu> queryHomeMenuAll();
    List<BrMaps> queryBrMapsAll();
    List<BrAnnotationMenuItem> queryBrAnnotationsAll();
    Integer querySubNumBrAnnotations(Integer parentId);
    Integer querySubNumBrFirstLevelAnnotationsMenu();
    Integer queryMenuItemCount();
    Integer insertBrAnnotationMenuItem(BrAnnotationMenuItem brAnnotationMenuItem);
    Integer updateBrAnnotationMenuItem(BrAnnotationMenuItem brAnnotationMenuItem);
    Integer deleteBrAnnotationMenuItem(Integer id);
    Integer deleteBatchBrAnnotationMenuItem(Integer[] ids);
    BrAnnotationMenuItem selectBrAnnotationMenuItemById(Integer id);
}
