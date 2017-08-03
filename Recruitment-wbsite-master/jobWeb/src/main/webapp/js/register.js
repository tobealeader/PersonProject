/**
 * Created by lenovo on 2016/10/27.
 */
$("#body-submit").click(function () {
    var username=$("#username").val();
    var password=$("#password").val();
    var repassword=$("#repassword").val();
    var email=$("#email").val();
    var type=$("input[name='type']:checked").val();
    var flag=true;
    $("#span-name").html("");
    $("#span-repass").html("");
    $("#span-pass").html("");
    $("#span-email").html("");
    if(username=="请输入用户名"){
        $("#span-name").html("&times;用户名不能为空");
        flag=false;
    }
    if(password=="请输入密码"){
        $("#checkbox").css("display","none");
        $("#span-pass").html("&times;请输入密码");
        flag=false;
    }
    if(repassword=="请重新输入密码"){
        $("#span-repass").html("&times;请输入密码");
        flag=false;
    }
    if(email=="请输入邮箱"){
        $("#span-email").html("&times;邮箱不能为空");
        flag=false;
    }
    if(typeof type=="undefined"){
        $("#span-type").html("&times;请选择用户类型");
        flag=false;
    }
    var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    if(reg.test(email)==false&&email!="请输入邮箱"){
        $("#span-email").html("&times;请输入正确邮箱");
        flag=false;
    }
    if(flag==false)
        return;
    var d=new Date();
    var date=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
    flag=false;
    $.ajax({
        url:"/user/checkname",
        type:"post",
        dataType:'json',
        async:false,
        data:{
            username:username,
        },
        success:function(data){
            if(data.message=="yes"){
                flag=true;
            }
        },
        error:function(){
            alert("500");
        }
    })
    if(flag==false){
        $("#span-name").html("&times;用户名已存在");
        return;
    }

    $.ajax({
        url:"/user/register",
        type:"post",
        dataType:'json',
        async:false,
        data:{
            username:username,
            password:password,
            email:email,
            type:type,
            date:date
        },
        success:function(data){
            window.location.href="index.html";

        },
        error:function(){
            alert("500");
        }
    })
})