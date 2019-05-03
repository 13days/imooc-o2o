$(function () {
    //登录验证的Controller url
    var loginUrl = '/o2o/local/logincheck';
    //从地址栏url里面获取usertype
    //usertype=1为用户，其余的为店铺所有者
    var usertype = getQueryString('usertype');
    //登录次数，登录失败三次之后自动弹出验证码进行安全验证
    var loginCount = 0;
    $('#sumbit').click(function () {
        //获取输入账号
        var userName = $('#username').val();
        //获取输入密码
        var password = $('#psw').val();
        //获取验证码信息
        var verifyCodeActual = $('#j_captcha').val();
        //是否需要验证码 默认不需要 false
        var needVerify = false;
        //连续三次登录失败
        if(loginCount>=3){
            //进行验证码安全校验
            if(!verifyCodeActual){
                $.toast('请输入验证码!');
                return;
            }else {
                needVerify = true;
            }
        }

        //ajax访问后台进行logincheck
        $.ajax({
            url:loginUrl,
            async:false,
            cache:false,
            type:"post",
            dataType:'json',
            data:{
                userName:userName,
                password:password,
                verifyCodeActual:verifyCodeActual,
                //是否需要验证码校验
                needVerify:needVerify
            },
            success:function (data) {
                if(data.success){
                    $.toast('登陆成功!');
                    if(usertype == 1){
                        //若用户在前端展示系统页面则自动退回到前端展示系统首页
                        window.location.href='/o2o/frontend/index';
                    }else {
                        //若用户是在店家管理系统页面则自动回退到店铺列表页面中
                        window.location.href='/o2o/shopadmin/shoplist';
                    }
                }else {
                    $.toast('登陆失败!--'+data.errMsg);
                    loginCount++;
                    //三次登录失败 激活验证码校验选项
                    if(loginCount>=3){
                        $('#verifyPart').show();
                    }
                }
            }
        });

    });

});