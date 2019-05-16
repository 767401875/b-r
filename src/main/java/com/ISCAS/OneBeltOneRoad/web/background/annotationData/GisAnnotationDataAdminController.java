package com.ISCAS.OneBeltOneRoad.web.background.annotationData;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/annotationdataadmin")
public class GisAnnotationDataAdminController {
    @RequestMapping("/change")
    public String annotationDataChange(){
        return "gis/annotationDatas/annotationDataChange";
    }
    @RequestMapping("/list")
    public String annotationDataList(){
        return "gis/annotationDatas/annotationDataList";
    }
    @RequestMapping("/add")
    public String annotationDataAdd(){
        return "gis/annotationDatas/annotationDataAdd";
    }
    @RequestMapping("/delete")
    public String annotationDataDelete(){
        return "gis/annotationDatas/annotationDataDelete";
    }
}
