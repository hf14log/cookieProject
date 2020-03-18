package com.cookie.dao.impl;

import com.cookie.dao.UserDao;
import com.cookie.entity.User;

import java.sql.*;

/*
* 跟数据库发生交互
* */

public class UserDaoImpl implements UserDao {
    @Override
    public User checkUser(User user) {
        //定义连接的对象
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet resultSet = null;
        User u = null;
        //使用反射
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","123456");
            String sql = "select id,name,pwd from user where name = ? and pwd = ?";
            psmt =conn.prepareStatement(sql);
            //给？赋值
            psmt.setString(1,user.getName());
            psmt.setString(2,user.getPwd());
            resultSet = psmt.executeQuery();

            while (resultSet.next()){
                u = new User(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("pwd"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                psmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return u;
    }

    @Override
    public User getUserById(int id) {
        //定义连接的对象
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet resultSet = null;
        User u = null;
        //使用反射
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","123456");
            String sql = "select * from user where id = ?";
            psmt =conn.prepareStatement(sql);
            //给？赋值
            psmt.setInt(1,id);

            resultSet = psmt.executeQuery();

            while (resultSet.next()){
                u = new User(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("pwd"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                psmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return u;
    }
}
