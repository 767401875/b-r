package com.ISCAS.OneBeltOneRoad.service.impl;

import com.ISCAS.OneBeltOneRoad.dao.Gis.GisDataDao;
import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotationData;
import com.ISCAS.OneBeltOneRoad.service.GisDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GisDataServiceImpl implements GisDataService {
    @Autowired
    GisDataDao gisDataDao;
    @Override
    public BrAnnotationData getBrAnnotationData(String name) {
        return gisDataDao.selectBrAnnotationData(name);
    }

    @Override
    public Integer addBrAnnotationData(BrAnnotationData brAnnotationData) {
        return gisDataDao.insertBrAnnotationData(brAnnotationData);
    }

    @Override
    public Integer modifyBrAnnotationData(BrAnnotationData brAnnotationData) {
        return gisDataDao.updateBrAnnotationData(brAnnotationData);
    }

    @Override
    public Integer removeBrAnnotationData(Integer id) {
        return gisDataDao.deleteBrAnnotationData(id);
    }

    @Override
    public List<BrAnnotationData> getBrAnnotationDataAll() {
        return gisDataDao.selectBrAnnotationDataAll();
    }

    @Override
    public BrAnnotationData getBrAnnotationDataById(Integer id) {
        return gisDataDao.selectBrAnnotationDataById(id);
    }

    @Override
    public Integer removeBatchBrAnnotationData(Integer[] ids) {
        return gisDataDao.deleteBatchBrAnnotationData(ids);
    }
}
