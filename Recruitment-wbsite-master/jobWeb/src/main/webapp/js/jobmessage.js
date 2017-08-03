/**
 * Created by lenovo on 2016/11/5.
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
                $.ajax({
                    url: "/company/iscompanyMessageGet",
                    type: "post",
                    dataType: "json",
                    async: false,
                    success: function (data) {
                        if(data==false){
                            alert("您的信息审核还未通过");
                            window.location.href="messageCheck.html";
                        }
                    }
                });
            }
            else{
                alert("请先登录");
                window.location.href="login.html";
            }
        }
    });
}
var typee;
$(".radio input").click(function () {
    typee=$(this).attr("value");
});
$("button").click(function () {

    var position=$("#position").val(),
        tell=$("#tell").val(),
        address=$("#address").val(),
        duty=$("#duty").val();

    $("#alert1").css("display","none");
    $("#alert2").css("display","none");
    if(position==""||tell==""||address==""||duty==""){
        $("#alert2 .alert").html("请填写完整的信息");
        $("#alert2").css("display","block");
        return;
    }
    reg=/^1(3|4|5|7|8)\d{9}$/;
    if(reg.test(tell)==false){
        $("#alert1").css("display","block");
        return;
    }
    var d=new Date();
    var date=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
    $.ajax({
        url:"/company/jobMessageSave",
        dataType:"json",
        type:"post",
        data:{
            tell:tell,
            address:address,
            duty:duty,
            position:position,
            date:date,
            type:typee
        },
        success:function (data) {
            if(data==true){
                window.location.href="company.html";
            }
            else{
                $("#alert2 .alert").html("发布失败，500错误");
                $("#alert2").css("display","block");
            }
        }
    })

});