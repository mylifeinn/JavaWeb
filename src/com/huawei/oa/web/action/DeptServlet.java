package com.huawei.oa.web.action;

import com.huawei.oa.bean.Dept;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:VictoryChan
 * @Description：
 * @Date:Creat in12:55 2022/9/25
 * @Modified By:
 */

@WebServlet("/dept/list")

public class DeptServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/dept/list".equals(servletPath)) {
            doList(request, response);
        }
    }

    /**
     * 连接数据库，查询所有部门信息，将部门信息收集好，然后跳转到jsp做页面展示
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //准备一个容器，用来专门存储部门
        List<Dept> depts = new ArrayList<>();
        //连接数据库，查询所有的部门信息
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            //获取连接
            conn= DBUtil.getConnection();
            //执行查询语句
            String sql="select deptno,dname,loc from dept";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            //遍历结果集
            while (rs.next()){
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                //将以上零散数据封装成java对象。
                Dept dept =new Dept();
                dept.setDeptno(deptno);
                dept.setDname(dname);
                dept.setLoc(loc);

                //将部门集合放到list集合当中
                depts.add(dept);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            //释放资源
            DBUtil.close(conn,ps,rs);
        }

        //将一个集合放到请求域当中
        request.setAttribute("deptList",depts);

        //转发(不要重定向)
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }
}
