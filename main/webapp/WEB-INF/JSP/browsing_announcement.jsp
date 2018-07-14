<%@ page import="com.pixivx.www.Entity.OfficalMessage" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: my
  Date: 2018/7/13
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/layout.css">
    <link rel="stylesheet" href="/css/layout_browsingAnnouncement.css">
    <link rel="stylesheet" href="/css/component.css">
    <link rel="stylesheet" href="/css/color.css">

    <title>公告 | pixivX</title>
</head>
<body>
<section class="bg-white border-bottom">
    <div class="header_wrapper">
        <div class="py-3">
            <a href="home"> <img src="/image/pixivx_logo.png"> </a>
            <span class="font_medium" style="float:right"><input class="searchbox" placeholder="搜索pixivX"> <a class="searchbtn" href="home.html"> <i class="fas fa-search" aria-hidden="true"></i></a></span>

        </div>
        <nav>
            <a class="color_dark font_bold mr-2" href="home"> <i class="fas fa-home mr-1 color_icongrey" aria-hidden="true"></i>首页</a>
            <a href="home"> <i class="fas fa-arrow-up mr-1 color_icongrey" aria-hidden="true"></i>作品投稿</a>
            <span class="color_icongrey">|</span>
            <a class="mr-2" href="home">管理</a>
            <a class="mr-2" href="home"> <i class="fas fa-heart mr-1 color_icongrey" aria-hidden="true"></i>收藏</a>
            <a href="home"> <i class="fas fa-comments color_icongrey mr-1" aria-hidden="true"></i>创建话题</a>
            <span class="color_icongrey">|</span>
            <a href="home">管理</a>
            <span class="badgehyperlink" style="float:right"><a class="p-2" href="home"> <i class="fas fa-compass mr-1 color_icongrey " aria-hidden="true"></i>发现</a>
          <a class="p-2" href="home"> <i class="fab fa-dropbox mr-1 color_icongrey" aria-hidden="true"></i>动态广场</a>
        </span>
        </nav>
    </div>
</section>
<section class="bg-white main_wrapper_joined">
    <div class="py-2">
        <%
            OfficalMessage officalMessage = (OfficalMessage) request.getAttribute("officalMessage");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            String offical_message_submit_time = sdf.format(officalMessage.getSubmit_time()*1000);
        %>
        <h1><%= officalMessage.getTitle()%></h1>
        <div class="py-2 mx-4">
            <h2> <%=offical_message_submit_time%></h2>
            <div class="multiline">
                <%= officalMessage.getDescription()%>
            </div>
        </div>
    </div>
</section>
<section class="tail_wrapper">
    <div class="ml-2 font_medium tealhyperlink">
        <span class="font_bold">关于pixivX</span>
        <a class="mx-2" href="ToU.html">使用条款</a>
        <a href="home">联系我们</a>
    </div>
    <hr class="my-1 dotted">
    <span class="ml-2 color_grey9 font_small">©pixivx</span>
</section>

</body>
</html>

