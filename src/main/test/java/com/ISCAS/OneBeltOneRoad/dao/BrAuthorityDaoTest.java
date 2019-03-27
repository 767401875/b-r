package com.ISCAS.OneBeltOneRoad.dao;

import com.ISCAS.OneBeltOneRoad.BaseTest;
import com.ISCAS.OneBeltOneRoad.dao.Br.BrAuthorityDao;
import com.ISCAS.OneBeltOneRoad.entity.br.BrAuthority;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BrAuthorityDaoTest extends BaseTest {
    @Autowired
    BrAuthorityDao brAuthorityDao;
    @Test
    public void selectAuthority(){
        Integer a = 1;
        BrAuthority brAuthority = brAuthorityDao.selectAuthority(2);
        String menuItems = brAuthority.getMenuItemId();
        String[] menuItem = menuItems.split(",");
        boolean[] flag = new boolean[menuItem.length];
        for(int i = 0; i < flag.length; i++)
            System.out.println(flag[i]);
        System.out.println("id:" + brAuthority.getId());
        System.out.println("userId:" + brAuthority.getUserId());
        if(menuItems.contains("7"))
            System.out.println(true);
        menuItems = "-1,12,3,4,6";
        System.out.println("index:" + menuItems.indexOf(String.valueOf(a)));
        System.out.println("menuItemId:" + brAuthority.getMenuItemId());
        for(String item: menuItem){
            if(a.equals(Integer.parseInt(item)))
            System.out.println(Integer.parseInt(item));
        }
    }

}
