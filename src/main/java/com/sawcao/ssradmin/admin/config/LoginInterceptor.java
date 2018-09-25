package com.sawcao.ssradmin.admin.config;

import com.sawcao.ssradmin.admin.dto.AdminUser;
import com.sawcao.ssradmin.admin.dto.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/9/21
 **/
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        boolean flag = true;
        AdminUser adminUser = (AdminUser)request.getSession().getAttribute("adminuser");
        if (adminUser == null){
            response.sendRedirect("/login");
            flag = false;
        }else {
            flag = true;
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
