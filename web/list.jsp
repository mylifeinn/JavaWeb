<%@ page import="com.huawei.oa.bean.Dept" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>部门列表页面</title>
</head>

<body>

<script type="text/javascript">
    function del(dno) {
        var ok = window.confirm("亲，删除不可恢复哦！");
        if (ok) {
            document.location.href = "<%=request.getContextPath()%>/dept/delete?deptno=" + dno;
        }
    }
</script>

<h1 align="center">部门列表</h1>
<hr>
<table border="1px" align="center" width="50%">
    <tr>
        <th>序号</th>
        <th>部门编号</th>
        <th>部门名称</th>
        <th>操作</th>
    </tr>

    <%
        //从reuqest域当中取出集合
        List<Dept> deptList = (List<Dept>) request.getAttribute("deptList");
        //循环遍历
        int i=0;
        for (Dept dept : deptList) {
            //后台输出
            //System.out.println(dept.getDname());
            //out.write(dept.getDname());
    %>

    <%--<%=dept.getDname()%>--%>
    <%--<br>--%>

    <tr>
        <td><%=++i%></td>
        <td><%=dept.getDeptno()%></td>
        <td><%=dept.getDname()%></td>
        <td>
            <a href="javascript:void(0)" onclick="window.confirm('亲，确认删除数据吗？')">删除</a>
            <a href="<%=request.getContextPath()%>/edit.jsp">修改</a>
            <a href="<%=request.getContextPath()%>/dept/detail?dno=<%=dept.getDeptno()%>">详情</a>
        </td>
    </tr>


    <%
        }//for循环的结束花括号
    %>

</table>

<hr>
<a href="<%=request.getContextPath()%>/add.jsp">新增部门</a>
</body>

</html>