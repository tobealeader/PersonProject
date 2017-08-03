/**
 * Created by lenovo on 2016/11/8.
 */
    var type="";
    var q=location.search.substr(1).split("=");
    var id=parseInt(q[1]);
    $.ajax({
        url:"/user/isLogin",
        type:"post",
        dataType:"json",
        async:false,
        data:{},
        success:function (data) {
            if(data.message=="yes"){
                $("#username").html(data.username);
                $("#nav-unlogin").css("display","none");
                $("#nav-login").css("display","block");
                type=data.type;
            }
            else{
                $("#nav-login").css("display","none");
                $("#nav-unlogin").css("display","block");
            }
        }
    });
    $("#resume").click(function () {
        if(type=="person"){
            window.location.href="resume.html";
        }
        else if(type=="company"){
            window.location.href="jobmessage.html";
        }
    });
    $("#message").click(function () {
        if(type=="company"){
            window.location.href="company.html";
        }
        else if(type=="person"){
            window.location.href="person.html";
        }
    });
    $("#tounlogin").click(function () {
        $.ajax({
            url:"/user/tounLogin",
            type:"post",
            dataType:"json",
            async:false,
            success:function (data) {
                if(data.message=="yes"){
                    $("#nav-login").css("display","none");
                    $("#nav-unlogin").css("display","block");
                }
            }
        })
    });
    $.ajax({
        url:"/company/jobMessageGet",
        type:"post",
        dataType:"json",
        data:{"id":id},
        success:function(data){
            $("#job-name").html(data.position);
            $("#head-position").html(data.position);
            $("#jobtime").html(data.date);
            $("#jobpersonnum").html(data.count);
            $("#companyname").html(data.name);
            $("#address").html(data.address);
            $("#project").html(data.duty);
            $("#tell").html(data.tell);
            $("#type").html(data.companytype);
        }
    });
    function upresume(cid) {
        var d=new Date();
        var date=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
        $.ajax({
            url:"/index/upresume",
            dataType:"json",
            type:"post",
            data:{
                id:id,
                cid:cid,
                date:date
            },
            success:function (data) {
                if(data==true){
                    alert("投递成功");
                }
                else{
                    alert("投递失败,500错误");
                }
            }
        })
    };
    $("#work-submit button").click(function () {
        if(type==""){
            alert("请给我先登录，傻逼");
            return;
        }
        $.ajax({
            url:"/index/resumeget",
            type:"post",
            dataType:"json",
            async:false,
            success:function (data) {
                if(data.length>0){
                    var str="";
                    for(var i=data.length-1;i>=0;i--){
                       str+='<div class="form-group"><label>简历名：</label> <label>' +
                           data[i].photo+'</label><button type="button" onclick="upresume('+data[i].id+')" class="btn btn-primary">投递</button></div>';
                    }
                    $("#modal").html(str);
                    $('#myModal').modal({
                        keyboard: true
                    });
                }
                else{
                    alert("你还没有创建简历");
                }
            }
        });
    });