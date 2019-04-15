package com.ISCAS.OneBeltOneRoad.web.data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/annotationdataadmin")
public class GisAnnotationDataAdminController {
    @RequestMapping("/change")
    public String annotationDataOperation(){
        return "/gis/annotationDataChange";
    }
    @RequestMapping("/list")
    public String annotationDataList(){
        return "/gis/annotationDataList";
    }
    @RequestMapping("/add")
    public String annotationDataAdd(){
        return "/gis/annotationDataAdd";
    }
    @RequestMapping("/delete")
    public String annotationDataDelete(){
        return "/gis/annotationDataDelete";
    }
}
