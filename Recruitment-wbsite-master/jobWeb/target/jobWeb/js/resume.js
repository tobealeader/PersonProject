/**
 * Created by lenovo on 2016/10/28.
 */
function checkIsLogin() {
    $.ajax({
        url:"/user/isLogin",
        type:"post",
        dataType:"json",
        async:false,
        data:{},
        success:function (data) {
            if(data.message=="yes"){
                userid=data.id;
            }
            else{
                alert("请先登录");
                window.location.href="login.html";
            }
        }
    });
}
$(document).ready(function(){

    var sex="boy";
    $("#eduction-hover").mouseover(function(){

        $("#education-list").css("display","block");
    });
    $("#eduction-hover").mouseout(function(){
        $("#education-list").css("display","none");
    });
    $("#english-hover").mouseover(function(){
        $("#engish-list").css("display","block");
    });
    $("#english-hover").mouseout(function(){
        $("#engish-list").css("display","none");
    });
    var d=new Date();
    var year=d.getFullYear();
    year-=18;
    var i=40;
    var s="";
    while (i>=0){
        s+="<li>"+year+"</li>";
        year--;
        i--;
    }
    $("#birth-ul").html(s);
    $("#birth-hover").mouseover(function(){
        $("#birth-list").css("display","block");
    });
    $("#birth-hover").mouseout(function(){
        $("#birth-list").css("display","none");
    });
    $("#birth-list li").each(function(){
        $(this).click(function () {
            $("#birth").val($(this).text());
            $("#birth-list").css("display","none");
        })
    });
    $("#education-list li").each(function(){
        $(this).click(function () {

            $("#education").val($(this).text());
            $("#education-list").css("display","none");
        })
    });
    $("#engish-list li").each(function(){
        $(this).click(function () {
            $("#english").val($(this).text());
            $("#engish-list").css("display","none");
        })
    });
    $("#girl").click(function () {
        if(sex=="boy"){
            sex="girl";
            $("#boy").removeClass("sex-selected");
            $("#girl").addClass("sex-selected");
        }
    });
    $("#boy").click(function () {
        if(sex=="girl"){
            sex="boy";
            $("#girl").removeClass("sex-selected");
            $("#boy").addClass("sex-selected");
        }
    });
    $("button").click(function () {
        $("#check").html("");
        $("#email-check").html("");
        $("#tell-check").html("");
        var username=$("#username").val(),
            birth=$("#birth").val(),
            tell=$("#tell").val(),
            email=$("#email").val(),
            address=$("#address").val(),
            major=$("#major").val(),
            education=$("#education").val(),
            english=$("#english").val(),
            university=$("#university").val(),
            project=$("#project").val(),
            position=$("#position").val();
        if(username==""||birth==""||tell==""||email==""||address==""
        ||major==""||education==""||english==""||university==""||project==""||position==""){
            $("#check").html("&times;请填写完整信息");
            return;
        }
        var tag=true;
        var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
        if(reg.test(email)==false){
            $("#email-check").html("&times;请输入正确邮箱");
            tag=false;
        }
        reg=/^1(3|4|5|7|8)\d{9}$/;
        if(reg.test(tell)==false){
            $("#tell-check").html("&times;请输入正确号码");
            tag=false;
        }
        if(tag==false){
            $("#check").html("&times;请输入正确信息");
            return;
        }
        var d=new Date();
        var date=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
        $.ajax({
            url:"/user/resume",
            type:"post",
            dataType:"json",
            async:false,
            data:{
                username:username,
                sex:sex,
                birth:birth,
                tell:tell,
                email:email,
                address:address,
                major:major,
                education:education,
                english:english,
                university:university,
                project:project,
                position:position,
                date:date
            },
            success:function(data){
                if(data.message=="success"){
                    window.location.href=document.referrer;
                    //self.location=document.referrer;
                }
            },
            error:function(){
                alert("500");
            }
        })
    });
});
