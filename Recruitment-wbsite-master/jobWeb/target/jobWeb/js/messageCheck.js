/**
 * Created by lenovo on 2016/11/7.
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
};
$(function () {
   $.ajax({
       url:"/company/companyMessageGet",
       type:"post",
       dataType:"json",
       success:function (data) {
           if(data.status=="true"){
               $("#check-start").hide();
               $("#check-success").show();
           }
           else if(data.status=="false"){
               $("#check-start").hide();
               $("#check-fail").show();
           }
           else if(data.status=="on"){
               $("#check-start").hide();
               $("#check-wait").show();
           }
       },
       error:function () {
           $("#check-start").css("dispaly","block");
       }
   })
    $("#check1").click(function () {
        $("#check-start").hide();
        $("#check-write").show();
    });
    $("#check2").click(function () {
        $.ajax({
            url:"/company/messageCheckSave",
            type:"post",
            dataType:"json",
            data:{
                name:$("#name").val(),
                number:$("#number").val(),
                email:$("#email").val(),
                legalname:$("#legalname").val(),
                legalnumber:$("#legalnumber").val(),
                type:$("#type").val()
            },
            success:function (data) {
                if(data==true){
                    $("#check-write").hide();
                    $("#check-wait").show();
                }
                else{
                    alert("保存失败，500错误");
                }
            }
        })
    });
    $("#check3").click(function () {
        window.location.href="jobmessage.html";
    });
    $("#check4").click(function () {
        $.ajax({
            url: "/company/messageCheckDelete",
            type: "post",
            dataType: "json",
            success: function (data) {
                if(data==true){
                    $("#check-fail").hide();
                    $("#check-start").show();
                }
                else{
                    alert("点击失败，500错误");
                }
            }
        });
    });
});