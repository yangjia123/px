<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.pixivx.www.Entity.*" %>
<%--
  Created by IntelliJ IDEA.
  User: Lain
  Date: 2018/7/12
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
  <link rel="stylesheet" href="/css/layout.css">
  <link rel="stylesheet" href="/css/component.css">
  <link rel="stylesheet" href="/css/color.css">
  <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
  <script type="text/javascript">
      function selectRank(pic_label) {
          var urls='/rank?label='+pic_label;
          $.ajax({
              type:"post",
              contentType: "application/json",
              url:urls,
              dataType:"json",
              success:function (data) {
                  var optionJson=data;
                  var start = "<div class=\"gallery_xs\"><a href=\"home\"><div class=\"frame\"> <img src=\"";
                  var middle = "\"></div></a><a class=\"color_grey5 font_bold\" href=\"home\">"
                  var middle2 = "</a><br><a class=\"color_grey9\" href=\"home\">";
                  var end = "</a></div>";
                  var str="";
                  str+=start+optionJson[0].urls+middle+optionJson[0].title+middle2+optionJson[0].nickname+end
                      +start+optionJson[1].urls+middle+optionJson[1].title+middle2+optionJson[1].nickname+end
                      +start+optionJson[2].urls+middle+optionJson[2].title+middle2+optionJson[2].nickname+end;
                  document.getElementById(optionJson[0].div_id).innerHTML=str;

              },
              error:function(data){
                  alert("error");
              }
          });

      }

  </script>
  <title>pixivX</title>
</head>
<body>
<section class="bg-white border-bottom">
  <div class="header_wrapper">
    <div class="py-3">
      <a href="home"> <img src="/image/pixivx_logo.png"> </a>
      <span class="font_medium" style="float:right">
          <input class="searchbox" placeholder="搜索pixivX">
          <a class="searchbtn" href="home">
            <i class="fas fa-search" aria-hidden="true"></i>
          </a>
        </span>

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
<section class="main_wrapper">
  <!--左侧栏-->
  <div class="left_wrapper">
    <div class="profile_wrapper">
      <%
        User login_user = (User) request.getAttribute("user");
      %>
      <a class="pl-2 color_dark font_bold font_small" href="home"><img class="mr-2 avatar_home" src="<%=login_user.getHead_pic()%>"><%=login_user.getNickname()%></a>
      <hr class="mx-2">
      <ul class="profilelist font_medium">
        <li><a href="home">收藏</a></li>
        <li><a href="home">关注</a></li>
        <li><a href="home">粉丝</a></li>
        <li><a href="home">动态</a></li>
        <li><a href="home">提醒</a></li>
      </ul>
      <hr class="mx-2">
      <ul class="profilelist font_medium">
        <li><a href="home">浏览记录</a></li>
        <li><a href="home">评论记录</a></li>
      </ul>
      <hr class="mx-2">
      <ul class="profilelist font_medium">
        <li><a href="home">账户设置</a></li>
      </ul>
    </div>
  </div>
  <div class="middle_wrapper">
    <!--公告-->
    <div class="announcement_wrapper">
      <h1>公告</h1>
      <div class="ml-2 font_small tealhyperlink">
        <%
          List<OfficalMessage> offical_message_list = (List<OfficalMessage>) request.getAttribute("offical_message_list");
          for (OfficalMessage officalMessage : offical_message_list){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            String offical_message_submit_time = sdf.format(officalMessage.getSubmit_time()*1000);
            String offical_message_id = officalMessage.getOffical_message_id();
        %>
        <div>
          <a href="/User/browsing_announcement?offical_message_id=<%=offical_message_id%>"><%=officalMessage.getTitle()%></a>
          <span class="color_grey9">-<%=offical_message_submit_time%></span>

        </div>
        <%
          }
        %>
      </div>
    </div>
    <!--推荐-->
    <div class="recoomendation_wrapper">
      <h1>pixivX倾力推荐</h1>
      <%
        List index_rec_pic_group_list = (List)request.getAttribute("index_rec_pic_group_list");
        List index_rec_first_pic_list = (List)request.getAttribute("index_rec_first_pic_list");
        List index_rec_user_list = (List)request.getAttribute("index_rec_user_list");
        for( int i = 0; i <index_rec_pic_group_list.size(); i ++ ){
          PicGroup picGroup = (PicGroup) index_rec_pic_group_list.get(i);
          Pic pic = (Pic) index_rec_first_pic_list.get(i);
          User user = (User) index_rec_user_list.get(i);
      %>
      <div class="gallery_small">
        <a href="home"><div class="frame"><img src="<%=pic.getUrl()%>"></div></a>
        <a class="color_grey5 font_bold" href="home"><%=picGroup.getTitle()%></a><br>
        <a class="color_grey9" href="home"><%=user.getNickname()%></a>
      </div>
      <%
        }
      %>
    </div>
    <div class="content_wrapper">
      <!-- Discovery -->
      <h1><a class="color_dark" href="home">发现</a></h1>
      <%
        List discovery_pic_group_list = (List) request.getAttribute("discovery_pic_group_list");
        List discovery_user_list = (List) request.getAttribute("discovery_user_list");
        List discovery_first_pic_list = (List) request.getAttribute("discovery_first_pic_list");
        for (int i = 0; i< discovery_pic_group_list.size(); i ++ ){
          PicGroup picGroup =(PicGroup) discovery_pic_group_list.get(i);
          Pic pic = ( Pic) discovery_first_pic_list.get(i);
          User user = (User) discovery_user_list.get(i);

      %>
      <div class="gallery_small">
        <a href="home"><div class="frame"><img src="<%=pic.getUrl()%>"/></div></a>
        <a class="color_grey5 font_bold" href="home"><%=picGroup.getTitle()%></a><br>
        <a class="color_grey9" href="home"><%=user.getNickname()%></a>
      </div>
      <%
        }
      %>
      <div class="my-4 text-right">
        <a class="mr-2 color_hyperlinkteal font_medium" href="home">>>浏览更多</a>
      </div>
      <!-- Topic -->
      <h1><a class="color_dark" href="home">话题</a></h1>
      <%
        List topic_list = (List) request.getAttribute("topic_list");
        List topic_user_list = (List) request.getAttribute("topic_user_list");
        for (int i = 0; i< topic_list.size(); i ++ ){
          Topic topic =(Topic) topic_list.get(i);
          User user = (User) topic_user_list.get(i);

      %>
      <div class="gallery_medium">
        <a href="home"><div class="frame"><img src="<%=topic.getPic_url()%>"></div></a>
        <a class="color_grey5 font_bold" href="home"><%=topic.getTopic_name()%></a><br>
        <a class="color_grey9" href="home"><%=user.getNickname()%></a>
      </div>
      <%
        }
      %>
      <div class="my-4 text-right">
        <a class="mr-2 color_hyperlinkteal font_medium" href="home">>>浏览更多</a>
      </div>
      <!-- New Work -->
      <h1><a class="color_dark" href="home">大家的最新作品</a></h1>
      <%
        List newest_pic_group_list = (List) request.getAttribute("newest_pic_group_list");
        List newest_user_list = (List) request.getAttribute("newest_user_list");
        List newest_first_pic_list = (List) request.getAttribute("newest_first_pic_list");
        for (int i = 0; i< newest_pic_group_list.size(); i ++ ){
          PicGroup picGroup =(PicGroup) newest_pic_group_list.get(i);
          Pic pic = ( Pic) newest_first_pic_list.get(i);
          User user = (User) newest_user_list.get(i);

      %>
      <div class="gallery_small">
        <a href="home"><div class="frame"><img src="<%=pic.getUrl()%>"></div></a>
        <a class="color_grey5 font_bold" href="home"><%=picGroup.getTitle()%></a><br>
        <a class="color_grey9" href="home"><%=user.getNickname()%></a>
      </div>
      <%
        }
      %>
    </div>
  </div>
  <div class="right_wrapper">
    <div class="daily_wrapper">
      <h1>今日排行榜</h1>
      <div class="font_small">
        <a class="color_dark" id="rank_0_0" href="javascript:selectRank(0)">综合</a>
        <span class="color_icongrey">|</span>
        <a class="color_hyperlinkteal" id="rank_0_1" href="javascript:selectRank(1)">插画</a>
        <span class="color_icongrey">|</span>
        <a class="color_hyperlinkteal" id="rank_0_2" href="javascript:selectRank(2)">动图</a>
      </div>
      <div id="rank_1">
        <!--Rank List-->
        <%
          List rank_pic_group_list = (List)request.getAttribute("rank_pic_group_list");
          List rank_user_list = (List)request.getAttribute("rank_user_list");
          List rank_first_pic_list = (List)request.getAttribute("rank_first_pic_list");
          for( int i = 0; i <index_rec_pic_group_list.size(); i ++ ){
            PicGroup picGroup = (PicGroup) rank_pic_group_list.get(i);
            Pic pic = (Pic) rank_first_pic_list.get(i);
            User user = (User) rank_user_list.get(i);
        %>
        <div class="gallery_xs">
          <a href="home"><div class="frame">
            <img src="<%=pic.getUrl()%>">
          </div></a>
          <a class="color_grey5 font_bold" href="home"><%=picGroup.getTitle()%></a><br>
          <a class="color_grey9" href="home"><%=user.getNickname()%></a>
        </div>
        <%
          }
        %>
      </div>
    </div>
    <div class="daily_wrapper">
      <h1>男女热门排行榜</h1>
      <div class="font_small">
        <a class="color_dark" id="rank_1_0" href="javascript:selectRank(3)">受男性欢迎</a>
        <span class="color_icongrey">|</span>
        <a class="color_hyperlinkteal" id="rank_1_1" href="javascript:selectRank(4)">受女性欢迎</a>
      </div>
      <div id="rank_2">
        <%
          List rank_gender_pic_group_list_male = (List)request.getAttribute("rank_gender_pic_group_list_male");
          List rank_gender_user_list_male = (List)request.getAttribute("rank_gender_user_list_male");
          List rank_gender_first_pic_list_male = (List)request.getAttribute("rank_gender_first_pic_list_male");
          for( int i = 0; i <index_rec_pic_group_list.size(); i ++ ){
            PicGroup picGroup = (PicGroup) rank_gender_pic_group_list_male.get(i);
            Pic pic = (Pic) rank_gender_first_pic_list_male.get(i);
            User user = (User) rank_gender_user_list_male.get(i);
        %>
        <div class="gallery_xs">
          <a href="home"><div class="frame">
            <img src="<%=pic.getUrl()%>">
          </div></a>
          <a class="color_grey5 font_bold" href="home"><%=picGroup.getTitle()%></a><br>
          <a class="color_grey9" href="home"><%=user.getNickname()%></a>
        </div>
        <%
          }
        %>
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
