<%--
  Created by IntelliJ IDEA.
  User: my
  Date: 2018/7/12
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/layout_login.css">
    <link rel="stylesheet" href="/css/component.css">
    <link rel="stylesheet" href="/css/color.css">
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        var code = null;
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
                      res = "true";
                    }
                    else
                       res = "false";
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

        /**
         * 验证昵称是否已存在
         * @param value
         * @returns {boolean}
         */
        function isRegisterNickname(value) {
            var res = "true";
            $.ajax({
                url: 'http://localhost:8080/User/selectNickname?nickname='+value,
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
                        res = "true";
                    }
                    else
                        res = "false";
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
            var phone = document.forms["registerForm"]["phone"].value;
            var nickname = document.forms["registerForm"]["nickname"].value;
            var identityCode = document.forms["registerForm"]["identityCode"].value;
            var pass = document.forms["registerForm"]["inputPassword"].value;
            var pass2 = document.forms["registerForm"]["inputPasswordConfirm"].value;
            var y = document.forms["registerForm"]["selectYear"].value;
            var m = document.forms["registerForm"]["selectMonth"].value;
            var d = document.forms["registerForm"]["selectDay"].value;
            if(!isRegisterMobile(phone))
            {
                alert("手机号已注册");
                return false;
            }
            if(code != identityCode)
            {
                alert("验证码错误");
                return false;
            }
            if(!isRegisterNickname(nickname))
            {
                alert("该昵称已存在");
                return false;
            }
            if(pass != pass2)
            {
                alert("两次输入的密码不一致");
                return false;
            }
            if(y == "年" || m == "月" || d == "日")
            {
                alert("请选择生日");
                return false;
            }
            if((m == "04" || m == "06" || m == "09" || m == "11") && d == "31")
            {
                alert("请选择有效的日期");
                return false;
            }
            if(y % 4 == 0)
            {
                if(m == "02" && d > 29)
                {
                    alert("请选择有效的日期");
                    return false;
                }
            }
            else if(m == "02" && d > 28)
            {
                alert("请选择有效的日期");
                return false;
            }
        }
        /**
         * 获取验证码
         * @param mobile
         */
        function getCaptcha() {
            var phone= document.forms["registerForm"]["phone"].value;
            $.ajax({
                type: "POST",
                url: "http://47.94.175.242/Sms/demo/sendSms.php",
                dataType:"json",
                data: {
                    phone:phone
                },
                success: function(data){
                    code= data;
                    console.log(phone);
                    console.log(data);
                },
                error:function(){
                    alert("发送失败");
                }
            });
        }

    </script>

    <title>Sign Up | pixivX</title>
</head>
<body>
<form name="registerForm" action="/User/register"   method="post" onsubmit="return validateForm()" id="registerbox">
    <div class="text-center">
        <img src="/image/pixivx_logo.png">
    </div>
    <div class="text-center my-2 color_grey9 font_bold">
        让创作变得炒鸡有乐趣
    </div>
    <div class="text-center mt-2 mb-4 color_grey9 font_bold" style="font-size: 20px">
        立即注册
    </div>
    <input required="required" class="form-control bcolor_bordergrey" name="phone" placeholder="手机号" pattern="(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$">
    <div class="input-group">
        <input required="required" type="text" name="identityCode" class="form-control bcolor_bordergrey" placeholder="6位验证码">
        <div class="input-group-append">
            <button class="btn btn-outline-secondary" onclick="getCaptcha()" type="button">获取验证码</button>
        </div>
    </div>
    <hr>
    <input name="inputPassword" required="required" class="form-control bcolor_bordergrey" type="password" placeholder="密码" pattern="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{9,16}$">
    <div class="color_grey9 font_small">密码必须为9到16位的数字与字母组合</div>
    <input name="inputPasswordConfirm" required="required" class="form-control bcolor_bordergrey" type="password" placeholder="确认密码" pattern="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{9,16}$">
    <hr>
    <span>昵称</span>
    <input required="required" name="nickname" class="form-control bcolor_bordergrey" placeholder="昵称">
    <div class="color_grey9 font_small">昵称是您在pixivx里的名字</div>
    <div class="my-2">
        <span class="mr-3">性别</span>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="customRadioInline1" name="customRadioInline1" value="男" class="custom-control-input" checked>
            <label class="custom-control-label" for="customRadioInline1"  name="gender">男性</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="customRadioInline2" name="customRadioInline1"  value="女" class="custom-control-input">
            <label class="custom-control-label" for="customRadioInline2" name="gender">女性</label>
        </div>
    </div>
    <div class="mb-2">
        生日
    </div>
    <select name="selectYear" style="width:100px">
        <option>年</option>
        <option>2010</option>
        <option>2009</option>
        <option>2008</option>
        <option>2007</option>
        <option>2006</option>
        <option>2005</option>
        <option>2004</option>
        <option>2003</option>
        <option>2002</option>
        <option>2001</option>
        <option>2000</option>
        <option>2008</option>
        <option>1999</option>
        <option>1998</option>
        <option>1997</option>
        <option>1996</option>
        <option>1995</option>
        <option>1994</option>
        <option>1993</option>
        <option>1992</option>
        <option>1991</option>
        <option>1990</option>
        <option>1989</option>
        <option>1988</option>
        <option>1987</option>
        <option>1986</option>
        <option>1985</option>
        <option>1984</option>
        <option>1983</option>
        <option>1982</option>
        <option>1981</option>
        <option>1980</option>
        <option>1979</option>
        <option>1978</option>
        <option>1977</option>
        <option>1976</option>
        <option>1975</option>
        <option>1974</option>
        <option>1973</option>
        <option>1972</option>
        <option>1971</option>
        <option>1970</option>
        <option>1969</option>
        <option>1968</option>
        <option>1967</option>
        <option>1966</option>
        <option>1965</option>
        <option>1964</option>
        <option>1963</option>
        <option>1962</option>
        <option>1961</option>
        <option>1960</option>
    </select>
    <select name="selectMonth" style="width:100px">
        <option>月</option>
        <option>01</option>
        <option>02</option>
        <option>03</option>
        <option>04</option>
        <option>05</option>
        <option>06</option>
        <option>07</option>
        <option>08</option>
        <option>09</option>
        <option>10</option>
        <option>11</option>
        <option>12</option>
    </select>
    <select name="selectDay" style="width:100px">
        <option>日</option>
        <option>01</option>
        <option>02</option>
        <option>03</option>
        <option>04</option>
        <option>05</option>
        <option>06</option>
        <option>07</option>
        <option>08</option>
        <option>09</option>
        <option>10</option>
        <option>11</option>
        <option>12</option>
        <option>13</option>
        <option>14</option>
        <option>15</option>
        <option>16</option>
        <option>17</option>
        <option>18</option>
        <option>19</option>
        <option>20</option>
        <option>21</option>
        <option>22</option>
        <option>23</option>
        <option>24</option>
        <option>25</option>
        <option>26</option>
        <option>27</option>
        <option>28</option>
        <option>29</option>
        <option>30</option>
        <option>31</option>
    </select>
    <!-- submit the register form! -->
    <input class="mt-4 submitbox" type="submit" value="注册">
</form>
<div id="toprightbtn">
    <a href="/User/toLogin" class="colorbtnbrowninline">登录</a>
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
