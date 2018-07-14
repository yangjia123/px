<%--
  Created by IntelliJ IDEA.
  User: my
  Date: 2018/7/12
  Time: 10:30
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
    <link rel="stylesheet" href="/css/layout_login.css">
    <link rel="stylesheet" href="/css/component.css">
    <link rel="stylesheet" href="/css/color.css">

    <title>Login | pixivX</title>
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        // 是否正确的手机号
        function checkPhone() {
            var phone = $("#phoneEl").val();
            console.log(phone);
            if(phone==""){
                document.getElementById("phoneTip").innerHTML="未输入手机号";
                return false;
            }else if(!isMobile(phone)){
                document.getElementById("phoneTip").innerHTML="手机号错误";
                return false;
            }else if(!isRegisterMobile(phone)){
                document.getElementById("phoneTip").innerHTML="手机号未注册";
                return false;
            }
            else
                return false;
        }
        function checkPwd() {
            var pwd = $("#passwordEl").val();
            if(pwd==""){
                document.getElementById("pwdTip").innerHTML="未输入密码";
                return false;
            }else if(!isPassword(pwd)) {
                document.getElementById("pwdTip").innerHTML="密码格式错误";
            }
            else
            {
                document.getElementById("pwdTip").innerHTML="   ";
                return false;
            }
        }

        /**
         * 验证手机号
         * @param value
         * @returns {boolean}
         */
        function isMobile(value) {
            var reg = /^1[0-9]{10}$/;
            return reg.test(value);
        }

        /**
         * 验证手机号是否注册
         * @param value
         * @returns {boolean}
         */
        function isRegisterMobile(value) {
            $.ajax({
                url: 'http://localhost:8080/User/selectPhone?phone='+value,
                type: 'post',
                dataType: 'json',
                cache: false,
                timeout: 1000,
                success: function (tt) {
                    var json = tt;
                    console.log(json);
                    var num = json.num;
                    console.log(num);
                    if(num == "0")
                    {
                        document.getElementById("phoneTip").innerHTML="手机号未注册";
                        return false;
                    }
                    else
                    {
                        document.getElementById("phoneTip").innerHTML="   ";
                        return true;
                    }
                },
                error:function (tt) {
                    console.log(tt);
                   return false;
                }
            });
        }
        /**
         * 验证密码
         * @param value
         * @returns {RegExp|boolean}
         */
        function isPassword(value) {
            return /^[\u0000-\u00ff]{6,16}$/.test(value)
        }

        function login() {
            var phone = $("#phoneEl").val();
            var pwd = $("#passwordEl").val();
            $.ajax({
                url: 'http://localhost:8080/User/login?phone='+phone+"&password="+pwd,
                type: 'post',
                dataType: 'json',
                cache: false,
                timeout: 1000,
                success: function (tt) {
                    var json = tt;
                    console.log(json);
                    var num = json.num;
                    console.log(num);
                    if(num == "0")
                    {
                        alert("手机号或密码错误");
                        return false;
                    }
                    else
                        window.self.location = "/User/toHome?phone="+phone;
                },
                error:function (tt) {
                    console.log(tt);
                    return false;
                }
            });
        }
    </script>
</head>
<body>
<form id="loginbox" action="ULTIMATE!LOGIN!ACTION">
    <div class="text-center">
        <img src="/image/pixivx_logo.png">
    </div>
    <div class="text-center my-2 color_grey9 font_bold">
        让创作变得炒鸡有乐趣
    </div>
    <hr style="margin-bottom: 50px">
    <input class="form-control bcolor_bordergrey" id="phoneEl" name="phoneEl" onblur="checkPhone()" placeholder="手机号">
    <div class="color_grey9 font_small" id="phoneTip" name="phoneTip"></div>
    <input class="form-control bcolor_bordergrey" type="password" id="passwordEl" name="passwordEl" onblur="checkPwd()"placeholder="密码">
    <div class="color_grey9 font_small" id="pwdTip" name="pwdTip"></div>
    <input class="mt-4 submitbox"  type="button" onclick="login()" value="登录">
    <div class="text-right">
        <a class="color_grey9 font_small" href="/User/toReminder">天呐，我忘记了我的密码!</a>
    </div>
</form>
<div id="toprightbtn">
    <a href="/User/toRegister" class="colorbtnskyblueinline">立即注册!</a>
</div>

<div id="crossfade">
    <img src="/image/work1.jpg">
    <img src="/image/work2.jpg">
    <img src="/image/work3.png">
    <img src="/image/work4.png">
    <img src="/image/work5.jpeg">
    <img src="/image/work6.jpg">
    <img src="/image/work7.jpg">
    <img src="/image/work8.jpg">
</div>
</body>
</html>

