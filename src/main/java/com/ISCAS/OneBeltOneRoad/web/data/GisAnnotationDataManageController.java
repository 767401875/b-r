package com.ISCAS.OneBeltOneRoad.web.data;

import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotationData;
import com.ISCAS.OneBeltOneRoad.service.GisDataService;
import com.ISCAS.OneBeltOneRoad.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/annotationdatamanage")
public class GisAnnotationDataManageController {
    @Autowired
    GisDataService gisDataService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> annotationDataRegister(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        String brAnnotationDataStr = HttpServletRequestUtil.getString(request, "brAnnotationDataStr");
        ObjectMapper mapper = new ObjectMapper();
        try{
           BrAnnotationData brAnnotationData = mapper.readValue(brAnnotationDataStr, BrAnnotationData.class);
           if(brAnnotationData != null){
               Integer count = gisDataService.addBrAnnotationData(brAnnotationData);
               if(count > 0){
                   modelMap.put("success", true);
               }
               else {
                   modelMap.put("errMsg", "插入数据失败");
               }
           }else {
                modelMap.put("errMsg","数据为空!");
           }
        }catch (Exception e){
            e.printStackTrace();
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }
}