package com.ISCAS.OneBeltOneRoad.web.background.annotationData;

import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotationData;
import com.ISCAS.OneBeltOneRoad.service.GisDataService;
import com.ISCAS.OneBeltOneRoad.util.HttpServletRequestUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/annotationdatamanage")
public class GisAnnotationDataManageController {
    @Autowired
    GisDataService gisDataService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> annotationDataAdd(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        String brAnnotationDataStr = HttpServletRequestUtil.getString(request, "brAnnotationDataStr");
        ObjectMapper mapper = new ObjectMapper();
        try{
           BrAnnotationData brAnnotationData = mapper.readValue(brAnnotationDataStr, BrAnnotationData.class);
           if(brAnnotationData != null){
               Integer count = gisDataService.addBrAnnotationData(brAnnotationData);
               if(count > 0){
                   modelMap.put("success", true);
                   modelMap.put("id", brAnnotationData.getId());
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

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> annotationDataDelete(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        String brAnnotationDataStr = HttpServletRequestUtil.getString(request, "brAnnotationDataStr");
        ObjectMapper mapper = new ObjectMapper();
        try{
            BrAnnotationData brAnnotationData = mapper.readValue(brAnnotationDataStr, BrAnnotationData.class);
//            BrAnnotationData brAnnotationData = gisDataService.getBrAnnotationData(brAnnotationDataRequest.getName());
            if(brAnnotationData != null){
                Integer count = gisDataService.removeBrAnnotationData(brAnnotationData.getId());
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
    //结果：将name不为空的菜单项进行返回
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    @ResponseBody
    Map<String, Object> annotationDataList(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        try{
            List<BrAnnotationData> brAnnotationsList = gisDataService.getBrAnnotationDataAll(); //这里修改
            if(brAnnotationsList != null){
                modelMap.put("result", brAnnotationsList);
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
    //结果：根据ID返回菜单的详细信息
    @RequestMapping(value = "/annotationdetail/{id}", method = RequestMethod.GET)
    @ResponseBody
    Map<String, Object> annotationDetail(@PathVariable Integer id){
        Map<String, Object> modelMap = new HashMap<>();
        try{
            BrAnnotationData brAnnotationData = gisDataService.getBrAnnotationDataById(id);
            if(brAnnotationData != null){
                modelMap.put("result", brAnnotationData);
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
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> annotationDataEdit(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        String brAnnotationDataStr = HttpServletRequestUtil.getString(request, "brAnnotationDataStr");
        ObjectMapper mapper = new ObjectMapper();
        try{
            BrAnnotationData brAnnotationData = mapper.readValue(brAnnotationDataStr, BrAnnotationData.class);
            if(brAnnotationData != null){
                Integer count = gisDataService.modifyBrAnnotationData(brAnnotationData);
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
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("error", e.getMessage());
        }
        return modelMap;
    }
    @RequestMapping(value = "/deletebatch", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> annotationDataDeleteBatch(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        String brAnnotationDatasStr = HttpServletRequestUtil.getString(request, "brAnnotationDatasStr");
        ObjectMapper mapper = new ObjectMapper();
        try {
            BrAnnotationData[] brAnnotationDatas = mapper.readValue(brAnnotationDatasStr, new TypeReference<BrAnnotationData[]>(){});
            if(brAnnotationDatas != null){
                Integer[] ids = new Integer[brAnnotationDatas.length];
                for(int i = 0;i < brAnnotationDatas.length; i++){
                    ids[i] = brAnnotationDatas[i].getId();
                }
                Integer count = gisDataService.removeBatchBrAnnotationData(ids);
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
