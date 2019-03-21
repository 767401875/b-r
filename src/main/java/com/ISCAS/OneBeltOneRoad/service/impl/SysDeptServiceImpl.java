package com.ISCAS.OneBeltOneRoad.service.impl;

import com.ISCAS.OneBeltOneRoad.common.RequestHolder;
import com.ISCAS.OneBeltOneRoad.dao.SysDeptDao;
import com.ISCAS.OneBeltOneRoad.entity.SysDept;
import com.ISCAS.OneBeltOneRoad.entity.param.DeptParam;
import com.ISCAS.OneBeltOneRoad.exceptions.ParamException;
import com.ISCAS.OneBeltOneRoad.service.SysDeptService;
import com.ISCAS.OneBeltOneRoad.util.IpUtil;
import com.ISCAS.OneBeltOneRoad.util.LevelUtil;
import com.google.common.base.Preconditions;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptDao sysDeptDao;
    @Override
    public void addDept(DeptParam param) {
//      Todo:参数校验
//      BeanValidator.check(param);
        if(checkExist(param.getParentId(), param.getName(), param.getId())){
            throw new ParamException("同一层级存在相同名称的部门");
        }
        SysDept sysDept = new SysDept();
        sysDept.setName(param.getName());
        sysDept.setParentId(param.getParentId());
        sysDept.setSeq(param.getSeq());
        sysDept.setRemark(param.getRemark());
        sysDept.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        sysDept.setOperator(RequestHolder.getCurrentUser().getName());
        sysDept.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysDept.setOperateTime(new Date());
        sysDeptDao.insertSelective(sysDept);
    }
    public void update(DeptParam param){
//      Todo:参数校验
//      BeanValidator.check(param);
        if(checkExist(param.getParentId(), param.getName(), param.getId())){
            throw new ParamException("同一层级下存在相同名称的部门");
        }
        SysDept before = sysDeptDao.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull("before", "待更新的部门为空");
        SysDept after = new SysDept();
        after.setName(param.getName());
        after.setParentId(param.getParentId());
        after.setSeq(param.getSeq());
        after.setRemark(param.getRemark());
        after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        after.setOperator(RequestHolder.getCurrentUser().getName());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        after.setOperateTime(new Date());
        updateWithChildren(before, after);
    }
    @Transactional
    public void updateWithChildren(SysDept before, SysDept after){
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if(!newLevelPrefix.equals(oldLevelPrefix)){
            String curLevel = before.getLevel() + "." + before.getId();
            /*
             * 以修改前的层进行模糊匹配得到所有子节点
             * curLevel:0.1
             * level:0.1,0.11*,0.1.*
             */
            List<SysDept> deptList = sysDeptDao.getChildrenDeptListByLevel(curLevel + "%");
            if(CollectionUtils.isNotEmpty(deptList)){
                for(SysDept dept : deptList){
                    String level = dept.getLevel();
                    if(level.equals(curLevel)||level.indexOf(curLevel + ".") == 0){
                        level = level.substring(oldLevelPrefix.length()) + newLevelPrefix;
                        dept.setLevel(level);
                    }
                }
                sysDeptDao.batchUpdateLevel(deptList);
            }
            sysDeptDao.updateByPrimaryKey(after);
        }
    }
    private boolean checkExist(Integer parentId, String deptName, Integer deptId){
        return sysDeptDao.countByNameAndParentId(parentId, deptName, deptId) > 0;
    }
    private String getLevel(Integer deptId){
        SysDept dept = sysDeptDao.selectByPrimaryKey(deptId);
        if(dept == null)
            return null;
        return dept.getLevel();
    }

}
