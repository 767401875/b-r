package com.ISCAS.OneBeltOneRoad.web.frontend.permission;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/permission")
public class testController {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> test(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("name", "qsq");
        modelMap.put("agree","engineer");
        String path = System.getProperty("BeltRoad.webapp");
        System.out.println(path);
        return modelMap;
    }
}
