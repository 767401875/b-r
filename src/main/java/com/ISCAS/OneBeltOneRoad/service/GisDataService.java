package com.ISCAS.OneBeltOneRoad.service;

import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotationData;

public interface GisDataService {
    BrAnnotationData getBrAnnotationData(String name);
    Integer addBrAnnotationData(BrAnnotationData brAnnotationData);
    Integer modifyBrAnnotationData(BrAnnotationData brAnnotationData);
    Integer removeBrAnnotationData(Integer id);
}
