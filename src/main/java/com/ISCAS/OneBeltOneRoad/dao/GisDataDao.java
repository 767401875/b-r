package com.ISCAS.OneBeltOneRoad.dao;

import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotationData;

public interface GisDataDao {
    BrAnnotationData queryBrAnnotationData(String name);
}
