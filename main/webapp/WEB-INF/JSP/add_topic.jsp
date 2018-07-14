<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2018/7/11
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add_topic</title>
</head>
<body>
<form action = "/topic/Add_topic" method="post">
    <input type = "text" placeholder= "请输入话题名" name = "topic_name"/>
    <input type="text" placeholder="请输入话题描述" name = "description"/>
    <input type="text" placeholder="请输入用户ID" name = "user_id"/>
    <input type="submit" value="创建话题"/>
</form>
</body>
</html>
