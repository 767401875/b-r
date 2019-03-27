package com.ISCAS.OneBeltOneRoad.service.impl;

import com.ISCAS.OneBeltOneRoad.dao.Br.BrAuthorityDao;
import com.ISCAS.OneBeltOneRoad.entity.br.BrAuthority;
import com.ISCAS.OneBeltOneRoad.service.GisAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GisAuthorityServiceImpl implements GisAuthorityService {
    @Autowired
    BrAuthorityDao brAuthorityDao;
    @Override
    public BrAuthority getAuthority(Integer userId) {
        return brAuthorityDao.selectAuthority(userId);
    }
}
