package com.ISCAS.OneBeltOneRoad.web.menu;

import com.ISCAS.OneBeltOneRoad.dao.BrAuthorityDao;
import com.ISCAS.OneBeltOneRoad.entity.SystemUser;
import com.ISCAS.OneBeltOneRoad.entity.annotations.AnnotationRefQueryParam;
import com.ISCAS.OneBeltOneRoad.entity.br.BrAuthority;
import com.ISCAS.OneBeltOneRoad.entity.maps.*;
import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotations;
import com.ISCAS.OneBeltOneRoad.entity.br.BrMaps;
import com.ISCAS.OneBeltOneRoad.entity.br.BrHomeMenu;
import com.ISCAS.OneBeltOneRoad.entity.annotations.AnnotationMenuData;
import com.ISCAS.OneBeltOneRoad.entity.homeMenu.HomeMenuItem;
import com.ISCAS.OneBeltOneRoad.entity.homeMenu.LogoItemParam;
import com.ISCAS.OneBeltOneRoad.entity.annotations.AnnotationRef;
import com.ISCAS.OneBeltOneRoad.entity.maps.AnnotationLayersSource;
import com.ISCAS.OneBeltOneRoad.service.GisMenuService;
import com.ISCAS.OneBeltOneRoad.util.HttpServletRequestUtil;
import com.ISCAS.OneBeltOneRoad.util.JWTUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/data")
public class GisMenuController {
    @Autowired
    GisMenuService gisMenuService;
    @Autowired
    BrAuthorityDao brAuthorityDao;
    //主页面菜单
    @RequestMapping(value = "/home-menu", method = RequestMethod.GET)
    @ResponseBody
    Map<String, Object> homeMenuController(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        try{
            List<BrHomeMenu> brHomeMenuList = gisMenuService.getHomeMenuAll();
            List<HomeMenuItem> homeMenuItemList = new ArrayList<>();
            for(BrHomeMenu brHomeMenu : brHomeMenuList){
                JSONObject jsonObject = new JSONObject(brHomeMenu.getHomeMenuItem());
                HomeMenuItem homeMenuItem = new HomeMenuItem();
                LogoItemParam logoItemParam = new LogoItemParam();
                homeMenuItem.setName(jsonObject.getString("name"));
                homeMenuItem.setLink(jsonObject.getString("link"));
                homeMenuItem.setAvailable(jsonObject.getBoolean("available"));
                logoItemParam.setIconType(jsonObject.getJSONObject("logo").getString("iconType"));
                logoItemParam.setIconName(jsonObject.getJSONObject("logo").getString("iconName"));
                homeMenuItem.setLogo(logoItemParam);
                homeMenuItem.setDescription(jsonObject.getString("description"));
                homeMenuItemList.add(homeMenuItem);
            }
            if(brHomeMenuList != null)
                modelMap.put("data", homeMenuItemList);
            else modelMap.put("data", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelMap;
    }

    //获取地图
    @RequestMapping(value = "/maps", method = RequestMethod.GET)
    @ResponseBody
    Map<String, Object> mapsMenuController(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        List<BrMaps> brMapsList = gisMenuService.getBrMaps();
        MapsParam mapsParam  = new MapsParam();
        MapsItem[] mapsItems = new MapsItem[brMapsList.size()];
        int index = 0;
        for(BrMaps brMaps : brMapsList){
            JSONObject jsonObject = new JSONObject(brMaps.getAnnotation());
            JSONArray layersArray = jsonObject.getJSONArray("layers");
            Annotation annotation = new Annotation();
            AnnotationLayerObject[][] annotationLayerObjects  = new AnnotationLayerObject[layersArray.length()][];
            for(int i = 0; i < layersArray.length(); i++){
                JSONArray layerFrame = layersArray.getJSONArray(i);
                annotationLayerObjects[i] = new AnnotationLayerObject[layerFrame.length()];
                for(int j = 0; j < layerFrame.length(); j++){
                    JSONObject layerObject = layerFrame.getJSONObject(j);
                    AnnotationSourceParam annotationSourceParam = new AnnotationSourceParam();
                    AnnotationLayerObject annotationLayerObject = new AnnotationLayerObject();
                    annotationLayerObject.setType(layerObject.getString("type"));
                    annotationLayerObjects[i][j] = annotationLayerObject;
                    if(layerObject.has("source") == false||layerObject.getJSONObject("source").has("params") == false||layerObject.getJSONObject("source").getJSONObject("params").has("layers") == false)
                        continue;
                    annotationSourceParam.setLayers(layerObject.getJSONObject("source").getJSONObject("params").getString("layers"));
                    AnnotationLayersSource source = new AnnotationLayersSource();
                    if(layerObject.getJSONObject("source").has("url") == false)
                        continue;
                    source.setUrl(layerObject.getJSONObject("source").getString("url"));
                    if(layerObject.getJSONObject("source").has("servertype") == false)
                        continue;
                    source.setServerType(layerObject.getJSONObject("source").getString("servertype"));
                    source.setParams(annotationSourceParam);
                    annotationLayerObject.setSource(source);
                }
            }
            annotation.setLayers(annotationLayerObjects);
            MapsItem mapsItem = new MapsItem();
            mapsItem.setCaption(brMaps.getCaption());
            mapsItem.setAnnotation(annotation);
            mapsItems[index++] = mapsItem;
        }
        mapsParam.setItems(mapsItems);
        mapsParam.setInit(brMapsList.get(0).getInit());
        mapsParam.setProjection(brMapsList.get(0).getProjection());
        Double[] center = {116.342, 39.985};
        mapsParam.setCenter(center);
        Integer[] leftBottom = {-190, -90};
        mapsParam.setLeftBottom(leftBottom);
        Integer[] rightTop = {190, 90};
        mapsParam.setRightTop(rightTop);
        modelMap.put("name", "maps");
        modelMap.put("data", mapsParam);
        return modelMap;
    }

    //v1.0 获取菜单
//    @RequestMapping(value = "/annotations", method = RequestMethod.GET)
//    @ResponseBody
//    Map<String, Object> annotationsController(HttpServletRequest request){
//        Map<String, Object> modelMap = new HashMap<>();
//        List<BrAnnotations> brAnnotationsList = gisMenuService.getBrAnnotations();
//        Integer size = gisMenuService.getSubNumBrFirstLevelAnnotationsMenu();
//        AnnotationMenuData[] dataItems = new  AnnotationMenuData[size];
//        int dataItemsIndex = 0;
//        for(BrAnnotations brAnnotations : brAnnotationsList){
////          处理一级目录
//            if(brAnnotations.getParentId() == null){
//                AnnotationMenuData dataItem  = new AnnotationMenuData();
//                dataItem.setCaption(brAnnotations.getCaption());
//                dataItem.setDescription(brAnnotations.getDescription());
//                AnnotationMenuData[] annotationMenuDataSub = subAnnotationMenuData(brAnnotations, brAnnotationsList);
//                dataItem.setItems(annotationMenuDataSub);
//                dataItems[dataItemsIndex++] = dataItem;
//            }
//        }
//        modelMap.put("name", "annotations");
//        modelMap.put("data", dataItems);
//        return modelMap;
//    }

    /*
        v2.0 获取菜单，菜单内容需要和用户进行挂钩!
        用户权限表中包括：菜单中整个路径上的所有节点。
        这里配合后台管理系统分配权限是来保障整个路径上所有节点多能正确地插入。
     */
    @RequestMapping(value = "/annotations", method = RequestMethod.GET)
    @ResponseBody
    Map<String, Object> annotationsByUserController(HttpServletRequest request, @RequestHeader HttpHeaders  headers, HttpServletResponse response){
        Map<String, Object> modelMap = new HashMap<>();
        //根据access确认用户信息
        String access = headers.getFirst("Authorization");
//        String access = HttpServletRequestUtil.getString(request, "Authorization");
        //前端请求为Bearer TokenString
        String[] accessSplits = access.split(" ");
        access = accessSplits[accessSplits.length - 1];
        if(access == null){
            modelMap.put("errMsg", "不包含refresh字段");
            response.setHeader("code", "403");
            response.setStatus(403);
            return null;
        }
        SystemUser user = JWTUtil.unsign(access, SystemUser.class);
        BrAuthority brAuthority = brAuthorityDao.selectAuthority((int)user.getId());
//        BrAuthority brAuthority = brAuthorityDao.selectAuthority(2);
        String menuItems = brAuthority.getMenuItemId();
        String[] menuItem = menuItems.split(",");
        Integer menuItemAllSize = gisMenuService.getMenuItemCount();
        boolean[] menuItemFlag = new boolean[menuItem.length];
//      数据库中返回的结果
        List<BrAnnotations> brAnnotationsList = gisMenuService.getBrAnnotations();
//        Integer size = gisMenuService.getSubNumBrFirstLevelAnnotationsMenu();
//      确定权限列表中第一级出现的个数
        Integer size = firstLevelByUserCount(brAnnotationsList, menuItem);

//      JSON包含的数组
        AnnotationMenuData[] dataItems = new AnnotationMenuData[size];
        int dataItemsIndex = 0;
        for(BrAnnotations brAnnotations : brAnnotationsList){
//          处理一级目录
            if(brAnnotations.getParentId() == null){
                Integer firstLevelId = brAnnotations.getId();
                Integer menuItemIndex = 0;
                for(;menuItemIndex < menuItem.length; menuItemIndex++){
                    if(firstLevelId.equals(Integer.parseInt(menuItem[menuItemIndex]))){
                        AnnotationMenuData dataItem  = new AnnotationMenuData();
                        dataItem.setCaption(brAnnotations.getCaption());
                        dataItem.setDescription(brAnnotations.getDescription());
                        AnnotationMenuData[] annotationMenuDataSub = subAnnotationMenuDataByUser(brAnnotations, brAnnotationsList, menuItem);
                        dataItem.setItems(annotationMenuDataSub);
                        dataItems[dataItemsIndex++] = dataItem;
                        break;
                    }
                }
            }
        }
        modelMap.put("name", "annotations");
        modelMap.put("data", dataItems);
        return modelMap;
    }
    public Integer firstLevelByUserCount(List<BrAnnotations> brAnnotationsList, String[] menuItem){
        Integer size = 0;
        for(BrAnnotations brAnnotations : brAnnotationsList){
            if(brAnnotations.getParentId() == null){
                Integer menuItemIndex = 0;
                Integer brAnnotationsId = brAnnotations.getId();
                for(;menuItemIndex < menuItem.length; menuItemIndex++){
                    if(brAnnotationsId.equals(Integer.parseInt(menuItem[menuItemIndex]))){
                        size++;
                    }
                }
            }
        }
        return size;
    }
    public  AnnotationMenuData[] subAnnotationMenuData(BrAnnotations parentAnnotations, List<BrAnnotations> brAnnotationsList){
        int count  = gisMenuService.getSubNumBrAnnotations(parentAnnotations.getId());
        if(count == 0){
            return null;
        }
        AnnotationMenuData[] items = new AnnotationMenuData[count];
        int itemsIndex = 0;
        for(BrAnnotations brAnnotations : brAnnotationsList){
//          查找子节点
            if(parentAnnotations.getId() == brAnnotations.getParentId()){
                AnnotationMenuData subAnnotationMenuData = new AnnotationMenuData();
                subAnnotationMenuData.setCaption(brAnnotations.getCaption());

                AnnotationMenuData[] sub = subAnnotationMenuData(brAnnotations, brAnnotationsList);
//              为叶子节点
                if(sub == null){
                   AnnotationRef annotationRef = new AnnotationRef();
                   AnnotationRefQueryParam annotationRefQueryParam = new AnnotationRefQueryParam();
                   annotationRef.setQueryParam(annotationRefQueryParam);
                   annotationRef.setDataName(brAnnotations.getName());
                   subAnnotationMenuData.setAnnotationRef(annotationRef);
                }
                else {
                    subAnnotationMenuData.setItems(sub);
                }
                items[itemsIndex++] = subAnnotationMenuData;
            }
        }
        return items;
    }
    public Integer subCountByUser(BrAnnotations parentAnnotations, List<BrAnnotations> brAnnotationsList, String[] menuItem){
        Integer count = 0;
        for(BrAnnotations brAnnotations :brAnnotationsList){
            if(parentAnnotations.getId() == brAnnotations.getParentId()) {
                Integer brAnnotationsId = brAnnotations.getId();
                for (int menuItemIndex = 0; menuItemIndex < menuItem.length; menuItemIndex++) {
                    if (brAnnotationsId.equals(Integer.parseInt(menuItem[menuItemIndex]))) {
                        count++;
                    }

                }
            }
        }
        return count;
    }
    public  AnnotationMenuData[] subAnnotationMenuDataByUser(BrAnnotations parentAnnotations, List<BrAnnotations> brAnnotationsList, String[] menuItem){
        //处理叶子结点
        int count  = gisMenuService.getSubNumBrAnnotations(parentAnnotations.getId());
        if(count == 0){
            return null;
        }
        //判断有多少个子节点在权限表中出现过
        count = subCountByUser(parentAnnotations, brAnnotationsList, menuItem);
        AnnotationMenuData[] items = new AnnotationMenuData[count];
        int itemsIndex = 0;
        for(BrAnnotations brAnnotations : brAnnotationsList){
//          查找子节点
            if(parentAnnotations.getId() == brAnnotations.getParentId()){
//              子节点的ID是否在权限里
                Integer brAnnotationsId = brAnnotations.getId();
                for(int menuItemIndex = 0; menuItemIndex < menuItem.length; menuItemIndex++){
                    if(brAnnotationsId.equals(Integer.parseInt(menuItem[menuItemIndex]))){
                        AnnotationMenuData subAnnotationMenuData = new AnnotationMenuData();
                        subAnnotationMenuData.setCaption(brAnnotations.getCaption());

                        AnnotationMenuData[] sub = subAnnotationMenuDataByUser(brAnnotations, brAnnotationsList, menuItem);
//                      叶子节点
                        if(sub == null){
                            AnnotationRef annotationRef = new AnnotationRef();
                            AnnotationRefQueryParam annotationRefQueryParam = new AnnotationRefQueryParam();
                            annotationRef.setQueryParam(annotationRefQueryParam);
                            annotationRef.setDataName(brAnnotations.getName());
                            subAnnotationMenuData.setAnnotationRef(annotationRef);
                        }
                        else {
                            subAnnotationMenuData.setItems(sub);
                        }
                        items[itemsIndex++] = subAnnotationMenuData;
                        break;
                    }
                }
            }
        }
        return items;
    }

}
