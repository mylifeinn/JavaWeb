<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改</title>
</head>
<body>
    <h1>修改部门</h1>
    <hr>
    <form action="<%=request.getContextPath()%>/list.jsp" method="get">
        部门编号<input type="text" name="deptno" value="20" readonly/><br>
        部门名称<input type="text" name="dname" value="销售部"/><br>
        部门位置<input type="text" name="loc" value="北京"/><br>
        <input type="submit" value="修改"/><br>
    </form>
</body>
</html>