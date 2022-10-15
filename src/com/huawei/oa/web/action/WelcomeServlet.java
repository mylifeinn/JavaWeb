package com.huawei.oa.web.action;

import com.huawei.oa.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author:VictoryChan
 * @Description：
 * @Date:Creat in5:26 2022/10/16
 * @Modified By:
 */
@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取cookie
        //这个Cookie[]数组可能是null,如果不是Null,数组的长度一定是大于0的。
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("username".equals(name)) {
                    username = cookie.getValue();
                } else if ("password".equals(name)) {
                    password = cookie.getValue();
                }
            }
        }

        //要在这里使用username和password变量
        if (username != null && password != null) {
            //验证用户名和密码是否正确
            //正确表示登录成功
            //错误表示登录失败
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            boolean success = false;

            try {
                conn = DBUtil.getConnection();
                String sql = "select * from t_user where username=? and password=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    //登录成功
                    success = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(conn, ps, rs);
            }
            if (success) {
                //获取session对象，这里的要求是：必须获取到session，没有也得新建一个session对象
                HttpSession session=request.getSession();
                session.setAttribute("username",username);
                //正确，表示登录成功
                response.sendRedirect(request.getContextPath()+"/dept/list");
            } else {
                //错误，表示登录失败
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }
        } else {
            //跳转到登录页面
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

    }
}
