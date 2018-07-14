<%--
  Created by IntelliJ IDEA.
  User: my
  Date: 2018/7/13
  Time: 8:21
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
    <link rel="stylesheet" href="/css/layout_reminder.css">
    <link rel="stylesheet" href="/css/component.css">
    <link rel="stylesheet" href="/css/color.css">

    <script>
        function validateForm() {
            var pass = document.forms["reminderForm"]["inputPassword"].value;
            var pass2 = document.forms["reminderForm"]["inputPasswordConfirm"].value;
            if(pass != pass2)
            {
                alert("两次输入的密码不一致");
                return false;
            }
        }
    </script>

    <title>新密码 | pixivX</title>
</head>
<body>
<section class="bg-white border-bottom">
    <div class="bg-white header_wrapper">
        <div class="pt-2">
            <a class="pt-4" href="home.html"> <img src="/image/pixivx_logo.png"> </a>
        </div>
    </div>
</section>
<form name="reminderForm" class="bg-white main_wrapper" action="/User/modifyPassword" onsubmit="return validateForm()" method="post">
    <h1>忘记了密码</h1>
    <hr>
    <div class="reminder_row1">
        请为您的账户键入一个新的密码
    </div>
    <div class="reminder_row2">
        <div class="reminder_col1">
            新密码
        </div>
        <div class="reminder_col2">
            <input name="inputPassword" required="required" style="width:320px" type="password" placeholder="密码" pattern="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{9,16}$">
            <div class="color_grey9 font_small">密码必须为9到16位的数字与字母组合</div>
        </div>
    </div>
    <div class="reminder_row3">
        <div class="reminder_col1">
            再次输入新密码
        </div>
        <div class="reminder_col2">
            <input name="inputPasswordConfirm" required="required" style="width:320px" type="password" placeholder="确认密码" pattern="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{9,16}$">
        </div>
    </div>
    <hr>
    <div class="reminder_row4">
        <input class="submitboxreminder" type="submit" value="提交">
    </div>
</form>
<section class="tail_wrapper">
    <div class="ml-2 font_medium tealhyperlink">
        <span class="font_bold">关于pixivX</span>
        <a class="mx-2" href="ToU.html">使用条款</a>
        <a href="home.html">联系我们</a>
    </div>
    <hr class="my-1 dotted">
    <span class="ml-2 color_grey9 font_small">©pixivx</span>
</section>

</body>
</html>

