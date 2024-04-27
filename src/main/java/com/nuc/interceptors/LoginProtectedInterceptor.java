package com.nuc.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuc.utils.JwtHelper;
import com.nuc.utils.Result;
import com.nuc.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginProtectedInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //请求头中获取token
        String token = request.getHeader("token");
        //检查是否有效
        boolean expiration = jwtHelper.isExpiration(token);
        //有效方行
        if (!expiration){
            //放行
            return true;
        }
        //无效返回504的状态json
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);
        response.getWriter().print(json);

        return false;
    }
}
