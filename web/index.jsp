<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>欢迎使用OA系统</title>
</head>
<body>
<!--前端超链接发送请求的时候，请求路径以"/"开始i，并且要带着项目名-->
<%--    <a href="/oa/list.jsp">查看部门列表</a>--%>

<%--<a href="<%=request.getContextPath()%>/list.jsp">查看部门列表</a>--%>

<%--<a href="<%=request.getContextPath()%>/dept/list">查看部门列表</a>--%>

<%--<hr>--%>
<%--<%=request.getContextPath()%>--%>

<h1>LOGIN PAGE</h1>
<hr>
<form action="<%=request.getContextPath()%>/user/login" method="post">
    username:<input type="text" name="username"><br>
    password:<input type="password" name="password"><br>
    <input type="submit" value="login">

</form>
</body>
</html>