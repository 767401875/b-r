package com.ISCAS.OneBeltOneRoad.dao;

import com.ISCAS.OneBeltOneRoad.entity.br.BrImportProject;

import java.util.List;
public interface BrDao {
    List<BrImportProject> selectBrImportProjectAll();
}
