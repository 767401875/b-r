package com.ISCAS.OneBeltOneRoad.web.background.annotationMenuItem;

import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotationMenuItem;
import com.ISCAS.OneBeltOneRoad.service.GisMenuService;
import com.ISCAS.OneBeltOneRoad.util.HttpServletRequestUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/annotationmenuitemmanage")
public class GisAnnotationMenuItemManageController {
    @Autowired
    GisMenuService gisMenuService;

    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    @ResponseBody
    Map<String, Object> annotationMenuItemList(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        try{
            List<BrAnnotationMenuItem> brAnnotationMenuItemList = gisMenuService.getBrAnnotationMenuItems();
            if(brAnnotationMenuItemList != null){
                modelMap.put("result", brAnnotationMenuItemList);
                modelMap.put("size", brAnnotationMenuItemList.size());
                modelMap.put("success", true);
            }
            else {
                modelMap.put("error", "结果为空");
                modelMap.put("success", false);
            }
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("error", e.getMessage());
        }
        return modelMap;
    }
    //结果:根据ID返回annotationMenuItem详细信息
//    @RequestMapping(value = "/menuitemdetail/{id}", method = RequestMethod.GET)
    @RequestMapping(value = "/menuitemdetail", method = RequestMethod.GET)
    @ResponseBody
//    Map<String, Object> annotationMenuItemDetail(@PathVariable Integer id){
    Map<String, Object> annotationMenuItemDetail(){
        Map<String, Object> modelMap = new HashMap<>();
        try {
//            BrAnnotationMenuItem brAnnotationMenuItem =  gisMenuService.getBrAnnotationMenuItemById(id);
            List<BrAnnotationMenuItem> brAnnotationMenuItemList = gisMenuService.getBrAnnotationMenuItems();
            if(brAnnotationMenuItemList != null){
                modelMap.put("result", brAnnotationMenuItemList);
                modelMap.put("success", true);
            }
            else {
                modelMap.put("success", false);
                modelMap.put("error", "结果为空");
            }
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("error", e.getMessage());
        }
        return modelMap;
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> annotationMenuItemAdd(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        String brAnnotationMenuItemStr = HttpServletRequestUtil.getString(request, "brAnnotationMenuItemStr");
        ObjectMapper mapper = new ObjectMapper();
        try{
            BrAnnotationMenuItem brAnnotationMenuItem = mapper.readValue(brAnnotationMenuItemStr, BrAnnotationMenuItem.class);
            if(brAnnotationMenuItem != null){
                Integer count = gisMenuService.addBrAnnotationMenuItem(brAnnotationMenuItem);
                if(count > 0){
                    modelMap.put("success", true);
                    modelMap.put("id", brAnnotationMenuItem.getId());
                }
                else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "插入数据失败");
                }
            }else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "数据为空");
            }
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> annotationMenuItemEdit(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        String brAnnotationMenuItemStr = HttpServletRequestUtil.getString(request, "brAnnotationMenuItemStr");
        ObjectMapper mapper = new ObjectMapper();
        try{
            BrAnnotationMenuItem brAnnotationMenuItem = mapper.readValue(brAnnotationMenuItemStr, BrAnnotationMenuItem.class);
            if(brAnnotationMenuItem != null){
                Integer count = gisMenuService.modifyBrAnnotationMenuItem(brAnnotationMenuItem);
                if(count > 0){
                    modelMap.put("success", true);
                }
                else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "修改数据失败");
                }
            }else {
                modelMap.put("success", false);
                modelMap.put("errMsg","数据为空!");
            }
        }
        catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("error", e.getMessage());
        }
        return modelMap;
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> annotationMenuItemDelete(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        String brAnnotationMenuItemStr = HttpServletRequestUtil.getString(request, "brAnnotationMenuItemStr");
        ObjectMapper mapper = new ObjectMapper();
        try {
            BrAnnotationMenuItem brAnnotationMenuItem = mapper.readValue(brAnnotationMenuItemStr, BrAnnotationMenuItem.class);
            if(brAnnotationMenuItem != null){
                Integer count = gisMenuService.removeBrAnnotationMenuItem(brAnnotationMenuItem.getId());
                if(count > 0){
                    modelMap.put("success", true);
                }
                else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "插入数据失败");
                }
            }else {
                modelMap.put("success", false);
                modelMap.put("errMsg","数据为空!");
            }
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }
    @RequestMapping(value = "/deletebatch", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> annotationMenuItemDeleteBatch(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        String brAnnotationMenuItemsStr = HttpServletRequestUtil.getString(request, "brAnnotationMenuItemsStr");
        ObjectMapper mapper = new ObjectMapper();
        try {
            BrAnnotationMenuItem[] brAnnotationMenuItems = mapper.readValue(brAnnotationMenuItemsStr, new TypeReference<BrAnnotationMenuItem[]>(){});
            if(brAnnotationMenuItems != null){

                Integer[] ids = new Integer[brAnnotationMenuItems.length];
                for(int i = 0;i < brAnnotationMenuItems.length; i++){
                    ids[i] = brAnnotationMenuItems[i].getId();
                }
                Integer count = gisMenuService.removeBatchBrAnnotationMenuItem(ids);
                if(count > 0){
                    modelMap.put("success", true);
                }
                else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "插入数据失败");
                }
            }else {
                modelMap.put("success", false);
                modelMap.put("errMsg","数据为空!");
            }
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

}
