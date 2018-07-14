<%--
  Created by IntelliJ IDEA.
  User: my
  Date: 2018/7/12
  Time: 20:28
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
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        var code=null;
        /**
         * 验证手机号是否注册
         * @param value
         * @returns {boolean}
         */
        function isRegisterMobile(value) {
            var res = "true";
            $.ajax({
                url: 'http://localhost:8080/User/selectPhone?phone='+value,
                type: 'post',
                dataType: 'json',
                cache: false,
                async: false,
                timeout: 1000,
                success: function (tt){
                    var json = tt;
                    console.log(json);
                    var num = json.num;
                    console.log(num);
                    if(num == "0")
                    {
                        res = "false";
                    }
                    else
                        res = "true";
                },
                error:function (tt) {
                    res = "false";
                }
            });
            if(res == "true")
                return true;
            else
                return false;
        }

        function validateForm() {
            var phone = document.forms["reminderForm"]["phone"].value;
            var identityCode = document.forms["reminderForm"]["identityCode"].value;
            if(!isRegisterMobile(phone))
            {
                alert("手机号未注册");
                return false;
            }
            if(code != identityCode)
            {
                alert("验证码错误");
                return false;
            }
        }

        /**
         * 获取验证码
         * @param mobile
         */
        function getCaptcha() {
            var phone= document.forms["reminderForm"]["phone"].value;
            $.ajax({
                type: "POST",
                url: "http://47.94.175.242/Sms/demo/sendSms.php",
                dataType:"json",
                data: {
                    phone:phone
                },
                success: function(data){
                    code= data;
                },
                error:function(){
                    alert("发送失败");
                }
            });
        }

    </script>

    <title>忘记密码 | pixivX</title>
</head>
<body>
<section class="bg-white border-bottom">
    <div class="bg-white header_wrapper">
        <div class="pt-2">
            <a class="pt-4" href="home.html"> <img src="/image/pixivx_logo.png"> </a>
        </div>
    </div>
</section>
<form class="bg-white main_wrapper" name="reminderForm" action="/User/toReminder2" onsubmit="return validateForm()" method="post">
    <h1>忘记了密码</h1>
    <hr>
    <div class="reminder_row1">
        为了账户的安全，请在下面输入您的手机号
    </div>
    <div class="reminder_row2">
        <span style="margin-right: 150px">手机号</span>
        <input required="required" style="width:320px" name="phone" placeholder="手机号" pattern="(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$">
    </div>
    <hr>
    <div class="reminder_row3">
        <span style="margin-right: 150px">验证码</span>
        <input required="required" type="text" name="identityCode" placeholder="6位验证码">
        <button class="btn-outline-dark" onclick="getCaptcha()"type="button">获取验证码</button>
    </div>
    <div class="reminder_row4">
        <input class="submitboxreminder" type="submit" value="继续">
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
</body>
</html>
