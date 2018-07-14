<%@ page import="java.util.List" %>
<%@ page import="com.pixivx.www.Entity.IndexRec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lain
  Date: 2018/7/12
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <%
        List index_rec_list = (List)session.getAttribute("index_rec_list");
        for( int i = 0; i <index_rec_list.size(); i ++ ){
            IndexRec indexRec = (IndexRec) index_rec_list.get(i);
    %>
        <tr>
            <td><%=indexRec.getIndex_rec_id()%></td>
            <td><%=indexRec.getPic_group_id()%></td>
            <td><%=indexRec.getSubmit_time()%></td>
        </tr>
    <%
        }
    %>
    <tr>
        <p>${index_rec_list.get(0).index_rec_id}</p>
        <p>${index_rec_list.get(0).pic_group_id}</p>
        <p>${index_rec_list.get(0).submit_time}</p>
    </tr>
</table>

</body>
</html>
