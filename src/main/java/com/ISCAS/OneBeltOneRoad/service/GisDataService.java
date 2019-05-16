package com.ISCAS.OneBeltOneRoad.service;

import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotationData;

import java.util.List;

public interface GisDataService {
    BrAnnotationData getBrAnnotationData(String name);
    Integer addBrAnnotationData(BrAnnotationData brAnnotationData);
    Integer modifyBrAnnotationData(BrAnnotationData brAnnotationData);
    Integer removeBrAnnotationData(Integer id);
    Integer removeBatchBrAnnotationData(Integer[] ids);
    List<BrAnnotationData> getBrAnnotationDataAll();
    BrAnnotationData getBrAnnotationDataById(Integer id);
}
