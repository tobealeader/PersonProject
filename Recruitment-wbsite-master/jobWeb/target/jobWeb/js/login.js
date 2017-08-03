/**
 * Created by lenovo on 2016/10/29.
 */
var ce=0;
$("#check").click(function () {
    ce=1-ce;
})
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++)
    {
        var c = ca[i].trim();
        if (c.indexOf(name)==0){
            return  unescape(c.substring(name.length,c.length));
        }

    }
    return "";
}
function autoSetCookie() {
    if(getCookie("password")!=""&&getCookie("username")!=""){

        $("#username").val(getCookie("username"));
        $("#password").val(getCookie("password"));
    }
}
$("button").click(function () {

    var flag=true;
    var username=$("#username").val(),
        password=$("#password").val();
    $("#body-right-body-p1").css("display","none");
    $("#body-right-body-p2").css("display","none");
    if(username==""){
        $("#body-right-body-p1").html("请输入用户名");
        $("#body-right-body-p1").css("display","block");
        flag=false;
    }
    if(password==""){
        $("#body-right-body-p2").css("display","block");
        flag=false;
    }
    if(flag==false)
        return;
    $.ajax({
        url:"/user/login",
        type:"post",
        dataType:"json",
        async:false,
        data:{
            username:username,
            password:password,
        },
        success:function(data){
            if(data.message=="yes"){
                if(ce==1){
                    var d=new Date();
                    d.setTime(d.getTime()+7*24*60*60*1000);
                    document.cookie="username="+escape(username)+";expires="+d.toGMTString();
                    document.cookie="password="+escape(password)+";expires="+d.toGMTString();
                }
                window.location.href="index.html";
            }
            else if(data.message=="no"){
                $("#body-right-body-p2").html("你输入的密码与账号不符");
                $("#body-right-body-p2").css("display","block");
            }
            else if(data.message=="not exit"){
                $("#body-right-body-p1").html("你输入的账号不存在");
                $("#body-right-body-p1").css("display","block");
            }
        },
        error:function(){
            alert("500");
        }
    })
});