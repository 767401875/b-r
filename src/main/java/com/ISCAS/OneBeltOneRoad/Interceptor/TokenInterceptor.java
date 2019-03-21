package com.ISCAS.OneBeltOneRoad.Interceptor;

import com.ISCAS.OneBeltOneRoad.dto.ResponseData;
import com.ISCAS.OneBeltOneRoad.entity.SystemUser;
import com.ISCAS.OneBeltOneRoad.util.HttpServletRequestUtil;
import com.ISCAS.OneBeltOneRoad.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String userStr = HttpServletRequestUtil.getString(request, "user");
        String token = request.getParameter("token");
        ObjectMapper mapper = new ObjectMapper();
        SystemUser user = mapper.readValue(userStr, SystemUser.class);
        ResponseData responseData = ResponseData.ok();
        if(token != null){
            SystemUser token_user = JWTUtil.unsign(token, SystemUser.class);
            //解密token后的user与用户传来的user不一致，一般都是token过期,验证账号和密码
            if(user != null && token_user != null){
                if(user.getLoginName() == token_user.getLoginName()&&user.getPassword() == token_user.getPassword()){
                    return true;
                }
                else {
                    responseData = ResponseData.forbidden();
                    responseMessage(response, response.getWriter(), responseData);
                    return false;
                }
            }

        }else {
            responseData = ResponseData.forbidden();
            responseMessage(response, response.getWriter(), responseData);
            return false;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out, ResponseData responseData) {
        responseData = ResponseData.forbidden();
        response.setContentType("application/json; charset=utf-8");
        String json = JSONObject.valueToString(responseData);
        out.print(json);
        out.flush();
        out.close();
    }
}
