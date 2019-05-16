package com.ISCAS.OneBeltOneRoad.web.frontend.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/useradmin", method = RequestMethod.GET)
public class UserAdminController {
    @RequestMapping(value = "useroperation")
    public String userOperation(){
        return "/user/userOperation";
    }
    @RequestMapping(value = "/login")
    public String userLogin(){
        return "/user/userLogin";
    }
}
