package com.cookie.control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("gbk");
//        String name = request.getParameter("name");
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        PrintWriter out = response.getWriter();
        out.write("<html>");
        out.write("<head>");
        out.write("</head>");
        out.write("<body>");
        out.write("<h1>hello,"+name+"欢迎登录</h1>");
        out.write("</body>");
        out.write("</html>");

    }
}
