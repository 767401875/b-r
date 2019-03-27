package com.ISCAS.OneBeltOneRoad.dao.Gis;

import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotationData;

public interface GisDataDao {
    BrAnnotationData selectBrAnnotationData(String name);
    Integer insertBrAnnotationData(BrAnnotationData brAnnotationData);
    Integer updateBrAnnotationData(BrAnnotationData brAnnotationData);
    Integer deleteBrAnnotationData(Integer id);
}
