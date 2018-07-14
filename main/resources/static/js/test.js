function theclick()
{
        var phone="15872401815";
        alert("7");
        console.log("3279");
        $.ajax({
            url: 'http://47.94.175.242/Sms/demo/sendSms.php?',
            type: 'post',
            data:{phone:15872401815},
            dataType: 'jsonp',
            timeout: 1000,
            cache: false,
            success: function (tt) {
                var json = tt; //数组
                var tt = "";
                var num = json[0].code;
                alert(num);
            },
            error:function (tt) {
                alert("解析出错");
            }
        });
}