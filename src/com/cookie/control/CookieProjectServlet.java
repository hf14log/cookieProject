package com.cookie.control;

import com.cookie.entity.User;
import com.cookie.service.UserService;
import com.cookie.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieProjectServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置request和response编码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("gbk");
        //获取cookie对象
        Cookie[] cookies = request.getCookies();
        //如果cookies等于空，直接跳转到登录页面进行登录请求，如果不为空，进行相关处理
        if(cookies !=null){
            for (Cookie c : cookies) {
                //如果uid等于cookie中的key
                if ("uid".equals(c.getName())){
                    String value = c.getValue();
                    //跟数据库中的user交互返回user信息
                    User user = userService.getUserById(Integer.valueOf(value));
                    //如果u不等于空，那么直接跳转登录后的欢迎页面
                    if (user !=null){
                        response.sendRedirect("hello");
                    }else{
                        response.sendRedirect("page");
                    }
                }

            }
        }else{
            //跳转登录页面
            request.getRequestDispatcher("page").forward(request,response);
        }
    }
}
