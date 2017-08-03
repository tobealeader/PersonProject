/**
 * Created by lenovo on 2016/10/31.
 */
var type="";
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
var int=self.setInterval("change()",2*1000);
var time=self.setInterval("clock()",3*1000);
var i=1;
var cloc=1;
function clock(){
    if(cloc){
        i=i+1;
    }
    else{
        i=i-1;
    }
    if(i==6){
        i=5;
        cloc=0;
    }
    if(i==1){
        cloc=1;
    }
}
function change(){
    var a=document.getElementById("head-img-loop");
    a.style.marginLeft=(1-i)*1100+"px";
}
$.ajax({
    url:"/index/jobMessageGet",
    type:"post",
    dataType:"json",
    success:function (data) {
        var str='<ul>';
        for (var i = 0; i <data.length; i++)
        {
            str+='<li><a href="specficjob.html?id='+data[i].id+'"><div class="job"><div class="job-title">'+data[i].position+
                '</div><div class="job-company">'+data[i].name+'</div><div class="job-address">' +
                data[i].address+'</div><div class="job-date">' +
                data[i].date+'</div></div></a></li>';
        }
        str+='</ul>';
        $("#hotjob").html(str);
    },
    error:function () {
        alert("500错误");
    }
});
$(".more").click(function () {
    $.ajax({
        url:"/index/jobmessageall",
        dataType:"json",
        type:"post",
        success:function (data) {
            var str='';
            for(var i=0;i<data.length;i++)
            {
                str+='<a href="specficjob.html?id='+data[i].id+'"><div class="search-body"><div class="search-title">'+data[i].position+
                        '</div><div class="search-company">'+data[i].name+
                        '</div><div class="search-address">'+data[i].address+
                        '</div><div class="search-date">'+data[i].date+'</div></div></a>';
            }
            $("#search-body").html(str);
            $("#no-search").hide();
            $("#search").show();
        }
    });
});
$("#search-submit").click(function () {
    var search;
    if($("#selectjob-ts").val()==""){
        search="IT";
    }
    else{
        search=$("#selectjob-ts").val();
    }
    $.ajax({
        url:"/index/searchJob",
        dataType:"json",
        type:"post",
        data:{
            search:search
        },
        success:function (data) {
            var k=new Array(1000);
            for(var i=0;i<data.length;i++)
            {
                k[data[i].id]=1;
            }
            var str='';
            for(var i=0;i<data.length;i++)
            {
                if(k[data[i].id]==0)
                    continue;
                str+='<a href="specficjob.html?id='+data[i].id+'"><div class="search-body"><div class="search-title">'+data[i].position+
                    '</div><div class="search-company">'+data[i].name+
                    '</div><div class="search-address">'+data[i].address+
                    '</div><div class="search-date">'+data[i].date+'</div></div></a>';
                k[data[i].id]=0;
            }
            $("#search-head").html(search);
            $("#search-body").html(str);
            $("#no-search").hide();
            $("#search").show();
        }
    })
});