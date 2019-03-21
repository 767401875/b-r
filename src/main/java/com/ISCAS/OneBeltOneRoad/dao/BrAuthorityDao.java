package com.ISCAS.OneBeltOneRoad.dao;

import com.ISCAS.OneBeltOneRoad.entity.SystemUser;
import com.ISCAS.OneBeltOneRoad.entity.br.BrAuthority;

public interface BrAuthorityDao {
    BrAuthority selectAuthority(Integer userId);
}
