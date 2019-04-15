package com.ISCAS.OneBeltOneRoad.dao.Gis;

import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotationData;

import java.util.List;

public interface GisDataDao {
    BrAnnotationData selectBrAnnotationData(String name);;
    Integer insertBrAnnotationData(BrAnnotationData brAnnotationData);
    Integer updateBrAnnotationData(BrAnnotationData brAnnotationData);
    Integer deleteBrAnnotationData(Integer id);
    Integer deleteBrAnnotationDataList(List<Integer> ids);
    BrAnnotationData selectBrAnnotationDataById(Integer id);
    List<BrAnnotationData> selectBrAnnotationDataAll();
}
