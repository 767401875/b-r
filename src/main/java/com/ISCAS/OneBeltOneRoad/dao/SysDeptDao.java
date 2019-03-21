package com.ISCAS.OneBeltOneRoad.dao;

import com.ISCAS.OneBeltOneRoad.entity.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDeptDao {
    int deleteByPrimaryKey(Integer id);
    int insert(SysDept record);
    int insertSelective(SysDept record);
    SysDept selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(SysDept record);
    int updateByPrimaryKey(SysDept record);
    int batchUpdateLevel(@Param(value = "SysDeptList") List<SysDept> deptList);
    List<SysDept> getAllDept();
    List<SysDept> getChildrenDeptListByLevel(@Param("level")String level);
    int countByNameAndParentId(@Param("parentId") Integer parentId,@Param("deptName") String deptName,@Param("deptId") Integer deptId);

}
