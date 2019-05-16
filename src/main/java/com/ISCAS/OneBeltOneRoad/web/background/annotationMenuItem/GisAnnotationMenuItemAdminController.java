package com.ISCAS.OneBeltOneRoad.web.background.annotationMenuItem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/annotationmenuitem")
public class GisAnnotationMenuItemAdminController {
    @RequestMapping("/change")
    public String annotationMenuItemChange(){
        return "gis/annotationMenuItems/annotationMenuItemChange";
    }
    @RequestMapping("/list")
    public String annotationMenuItemList(){
        return "gis/annotationMenuItems/annotationMenuItemList";
    }
    @RequestMapping("/add")
    public String annotationMenuItemAdd(){
        return "gis/annotationMenuItems/annotationMenuItemAdd";
    }
    @RequestMapping("/delete")
    public String annotationMenuItemDelete(){
        return "gis/annotationMenuItems/annotationMenuItemDelete";
    }
}
