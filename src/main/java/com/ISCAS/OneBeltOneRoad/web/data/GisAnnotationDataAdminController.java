package com.ISCAS.OneBeltOneRoad.web.data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/annotationdataadmin")
public class GisAnnotationDataAdminController {
    @RequestMapping("/annotationdataoperation")
    public String annotationDataOperation(){
        return "/gis/annotationDataOperation";
    }
}
