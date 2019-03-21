package com.ISCAS.OneBeltOneRoad.dao;

import com.ISCAS.OneBeltOneRoad.BaseTest;
import com.ISCAS.OneBeltOneRoad.entity.SysDept;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;


public class SysDeptDaoTest extends BaseTest {
    @Autowired
    SysDeptDao sysDeptDao;
    @Test
    @Ignore
    public void selectByPrimaryKeyTest(){
        Integer id = 1;
        SysDept sysDept = sysDeptDao.selectByPrimaryKey(id);
        System.out.println(sysDept.getName());
    }
    @Test
    public void getChildrenDeptListByLevelTest(){
        String curLevel = "0.4";
        List<SysDept> sysDeptList = sysDeptDao.getChildrenDeptListByLevel(curLevel + "%");
        String newLevel = "0.5";
        for(SysDept sysDept : sysDeptList){
            String level = sysDept.getLevel();
            if(level.equals(curLevel)||level.indexOf(curLevel + ".") == 0){
                level = newLevel + level.substring(curLevel.length());
                sysDept.setLevel(level);
            }
        }
        sysDeptDao.batchUpdateLevel(sysDeptList);
    }
    @Test
    @Ignore
    public  void insertSelectiveTest(){
        SysDept sysDept = new SysDept();
        sysDept.setName("大妖怪");
        sysDept.setSeq(1);
        sysDept.setRemark("第一次创建");
        sysDept.setOperator("qsq1");
        sysDept.setOperateIp("192.168.2.41");
        sysDept.setLevel("0.2");
        sysDept.setParentId(4);
        sysDept.setParentId(4);
        sysDept.setOperateTime(new Date());
        sysDeptDao.insertSelective(sysDept);
    }
    @Test
    @Ignore
    public void updateByPrimaryKeyTest(){
        SysDept sysDept = new SysDept();
        sysDept.setId(4);
        sysDept.setLevel("0");
        int count = sysDeptDao.updateByPrimaryKey(sysDept);
        System.out.println("===============================");
        System.out.println("count:" + count);
        System.out.println("===============================");
    }
    @Test
    @Ignore
    public void countByNameAndParentId(){
        SysDept sysDept = new SysDept();
        sysDept.setName("我的多啦不能没有A梦1");
        int count = sysDeptDao.countByNameAndParentId(null, "我的多啦不能没有A梦", null);
        System.out.println("count:" + count);
    }
    @Test
    @Ignore
    public void test(){
//        DeptParam param = new DeptParam();
//        param.setName("qsq");
//        param.setParentId(1);
//        param.setRemark("123123");
//        BeanValidator.check(param);
        String level = "1.0.1.1";
        String curLevel = "0.1";
        System.out.println(level.substring(2));
    }
}
