package com.huawei.oa.web.action;

import com.huawei.oa.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author:VictoryChan
 * @Description：
 * @Date:Creat in20:17 2022/10/11
 * @Modified By:
 */

//Servlet负责业务的处理
//JSP负责页面展示
@WebServlet("/user/login")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean success = false;
        //我要做意见什么事？验证用户名和密码是否正确
        //获取用户名和密码
        //前端你是这样提交的：username=admin&password=123456
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //连接数据库验证用户名和密码
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from t_user where username=? and password=?";
            //编译SQL
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            //执行SQL
            rs = ps.executeQuery();
            //返回结果集，这个结果集中最多只有一条数据，不可能有两条
            if (rs.next()) {//不需要while
                //登录成功
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        //登录成功/失败
        if (success) {
            //成功，跳转到用户列表页面
            response.sendRedirect(request.getContextPath()+"/dept/list");
        } else {
            //失败，跳转到失败页面
            response.sendRedirect(request.getContextPath()+"/error.jsp");

        }
    }
}
