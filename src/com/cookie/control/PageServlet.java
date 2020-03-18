package com.cookie.control;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

public class PageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("接收到客户端的请求，返回响应的登陆页面");
        //数据共享，从Login页面获取信息,并进行判断
        String str = (String)request.getAttribute("str")==null?"":(String)request.getAttribute("str");
        //设置响应文本格式
        response.setContentType("text/html");
        //设置响应编码
        response.setCharacterEncoding("gbk");
        //获取响应的输出流对象
        PrintWriter out = response.getWriter();

        //写页面
        out.write("<html>");
        out.write("<head>");
        out.write("</head>");
        out.write("<body>");
        out.write("<font color='red' size='20px'>"+str+"</font>");
        out.write(" <form action='login' method='get'>");
        out.write("用户名:<input type='text' name='name' value=''><br/>");
        out.write("密&nbsp;&nbsp;码:<input type='password' name='pwd' value=''><br/>");
        out.write("<input type='submit' value='登录'>");
        out.write("</form>");
        out.write("</body>");
        out.write("</html>");

    }
}
