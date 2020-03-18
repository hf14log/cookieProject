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

public class LoginServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //用户在客户端（网页）填写登录的用户名和密码，获取请求数据
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        //将name和pwd封装，以便对应数据库进行curd操作
        User user = new User(name, pwd);
        //调用service进行逻辑处理
        User u = userService.checkUser(user);
        System.out.println(u);
        //设置响应编码
        response.setCharacterEncoding("gbk");
        //进行判断返回success or false
        if (u !=null){
            //创建cookie
            Cookie cookie = new Cookie("uid", u.getId()+"");
            //设置cookie的过期时间
            cookie.setMaxAge(7*24*3600);
            //设置cookie路径
//            cookie.setPath("/cookie/aabb");
            //将cookie信息设置到response对象中
            response.addCookie(cookie);

//            response.getWriter().write("登录成功");
            //请求转发，会一直请求，并且打印登录信息
//            request.getRequestDispatcher("hello").forward(request,response);
            //使用重定向改变地址栏，改变请求,但是数据无法共享了，request和response不是一个了，无法获取用户信息了
            response.sendRedirect("hello");

        }else{
//            response.getWriter().write("登录失败");

            //设置参数，实现不同servlet之间的数据共享
            request.setAttribute("str","用户名或密码错误");
            //请求转发
            request.getRequestDispatcher("page").forward(request,response);
            //后续不需要逻辑代码处理
            return;

        }

    }
}
