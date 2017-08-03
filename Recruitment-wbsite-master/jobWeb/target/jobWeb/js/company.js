/**
 * Created by lenovo on 2016/11/5.
 */
$("#img-change").click(function () {
    $("#file").click();
})
var fileChange=function (event) {
    var files = event.target.files, file;
    if (files && files.length > 0) {
        file = files[0];
        if(file.size > 1024 * 1024 * 2) {
            alert('图片大小不能超过 2MB!');
            return false;
        }
        var URL = window.URL || window.webkitURL;
        var imgURL = URL.createObjectURL(file);
        $("#img-change").prop("src",imgURL);
        // URL.revokeObjectURL(imgURL);
    }
};
var userid;
$.ajax({
    url:"/user/isLogin",
    type:"post",
    dataType:"json",
    async:false,
    success:function (data) {
        if(data.message=="yes"){
            userid=data.id;
            $.ajax({
                url:"/user/showMessage",
                type:"post",
                dataType:"json",
                success:function (data) {
                    $("#username").val(data.username);
                    $("#password").val(data.userpwd);
                    $("#email").val(data.useremail);
                    $("#date").val(data.userdate);
                }
            });
        }
        else{
            alert("请先登录");
            window.location.href="login.html";
        }
    }
});
$("#chang-message").click(function () {
    $.ajax({
        url:"/user/showMessage",
        type:"post",
        dataType:"json",
        success:function (data) {
            $("#username").val(data.username);
            $("#password").val(data.userpwd);
            $("#email").val(data.useremail);
            $("#date").val(data.userdate);
        }
    });
});
$("#chang-photo").click(function () {
    $.ajax({
        url:"/user/showMessage",
        type:"post",
        dataType:"json",
        async:false,
        success:function (data) {
            $("#img-change").prop("src",data.photo);
        }
    });
});
$("#img-button").click(function () {
    $.ajaxFileUpload({
        url: '/user/imgUpload',
        fileElementId:'file',
        dataType:'txt',
        secureuri : false,
        success: function (data){
            if(data=="yes"){
                $("#img-alert").css("display","block");
            }
        },
        error:function(data,status,e){
            alert(e);
        }
    });
});
var table2;
var tt=0;
var rowNum2=0;
var cid;
var resumeRead=function (id) {
    cid=id;
    $.ajax({
        url:"/company/getresumeid",
        type:"post",
        dataType:"json",
        data:{
            id:id
        },
        success:function (d) {
            $.ajax({
                url:"/user/userResumeGet",
                type:"post",
                dataType:"json",
                data:{"id":d},
                success:function(data){
                    $("#Uposition").val(data.photo);
                    $("#Usex").val(data.sex);
                    $("#Uname").val(data.name);
                    $("#Ubirth").val(data.birth);
                    $("#Utell").val(data.tell);
                    $("#Uemail").val(data.mail);
                    $("#Uaddress").val(data.address);
                    $("#Umajor").val(data.major);
                    $("#Ueducation").val(data.education);
                    $("#Uenglish").val(data.english);
                    $("#Uuniversity").val(data.university);
                    $("#Uproject").val(data.project);
                    $('#myModal').modal({
                        keyboard: true
                    });
                }
            });
        }
    })
};
$("#resume-accept").click(function () {
    $.ajax({
        url:"/company/resumeMessageAcceptOrRefuse",
        dataType:"json",
        type:"post",
        data:{
            id:cid,
            status:"true"
        },
        success:function (data) {
            if(data==true){
                alert("您同意了他的工作请求");
                table2.ajax.reload( null, false );
            }
            else{
                alert("同意失败，500错误");
            }
        }
    })
});
$("#resume-refuse").click(function () {
    $.ajax({
        url:"/company/resumeMessageAcceptOrRefuse",
        dataType:"json",
        type:"post",
        data:{
            id:cid,
            status:"false"
        },
        success:function (data) {
            if(data==true) {
                alert("您拒绝了他的工作请求");
                table2.ajax.reload( null, false );
            }
            else{
                alert("拒绝失败，500错误");
            }
        }
    })
});
$("#show-message").click(function () {
   if(tt==0){
       table2 = $("#resume-table").DataTable({
           "aLengthMenu":[5,10,15,20],
           "searching":false,//禁用搜索
           "lengthChange":true,
           "paging": true,//开启表格分页
           "bProcessing" : true,
           "bServerSide" : true,
           "bAutoWidth" : false,
           "sort" : "position",
           "deferRender":false,//延迟渲染
           "bStateSave" : false, //在第三页刷新页面，会自动到第一页
           "iDisplayLength" : 5,
           "iDisplayStart" : 0,
           "ordering": false,//全局禁用排序
           "ajax": {
               "type": "POST",
               "url":  "/company/resumeMessageGet",
               "data":function(d){
                   d.search =""
               }
           },
           "aoColumns":[{
               "mData" : "id",
               "orderable": false , // 禁用排序
               "sDefaultContent" : "",
               "sWidth" : "7%"
           },{
               "mData" : "position",
               "orderable": false , // 禁用排序
               "sDefaultContent" : "",
               "sWidth" : "15%"
           },{
               "mData" : "name",
               "orderable": false , // 禁用排序
               "sDefaultContent" : "",
               "sWidth" : "10%"
           },{
               "mData" : "date",
               "orderable": false , // 禁用排序
               "sDefaultContent" : "",
               "sWidth" : "15%"
           },{
               "mData" : "satues",
               "orderable": false , // 禁用排序
               "sDefaultContent" : "",
               "sWidth" : "7%"
           },{
               "mData" : "id",
               "orderable" : false, // 禁用排序
               "sDefaultContent" : '',
               "sWidth" : "10%",
               "render":function(data, type, full, meta){
                   data='<button id="deleteOne" onclick="resumeRead('+data+')" class="btn btn-success btn-sm" data-id='+data+'><span class="icon-white icon-edit"></span>查 看</button> ';
                   return	data;
               }
           }
           ],
           "oLanguage" : { // 国际化配置
               "sProcessing" : "正在获取数据，请稍后...",
               "sLengthMenu" : "显示 _MENU_ 条",
               "sZeroRecords" : "没有找到数据",
               "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
               "sInfoEmpty" : "记录数为0",
               "sInfoFiltered" : "(全部记录数 _MAX_ 条)",
               "sInfoPostFix" : "",
               "sSearch" : "搜索",
               "sUrl" : "",
               "oPaginate" : {
                   "sFirst" : "第一页",
                   "sPrevious" : "上一页",
                   "sNext" : "下一页",
                   "sLast" : "最后一页"
               }
           },
           drawCallback: function(){
               rowNum2 = 1;
           },
           rowCallback : function(row, data, displayIndex) {
               $('td:eq(0)', row).html($("#resume-table").dataTable().fnSettings()._iDisplayStart + rowNum2++);
               return row;
           }
       });
       tt=1;
   }
   else{
       table2.ajax.reload( null, false );
   }
});
$("#message-save").click(function () {
    $.ajax({
        url:"/user/saveMessage",
        type:"post",
        dataType:"json",
        data:{
            username:$("#username").val(),
            userpwd:$("#password").val(),
            useremail:$("#email").val()
        },
        success:function (data) {
            if(data.message=="yes"){
                $("#message-alert").css("display","block");
            }
        }
    });
});
var rowNum = 1;
var delet = function(id){
    $.ajax({
        url:"/company/JobMessageDelete",
        type:"POST",
        data:{"id":id},
        success:function(data){
            if(data==true)
            {
                table.ajax.reload( null, false );
            }else{
                alert("没有,快滚!");
            }
        }
    });
};
var d1,d2;
var edit = function(id){
    $.ajax({
        url:"/company/jobMessageGet",
        type:"post",
        data:{"id":id},
        success:function(data){
            $("#Cposition").val(data.position);
            $("#Cname").val(data.name);
            $("#Ctell").val(data.tell);
            $("#Ctype").val(data.companytype);
            $("#Caddress").val(data.address);
            $("#Cproject").val(data.duty);
            $("#getid").val(data.id);
            d1=data.userid;
            d2=data.date;
            $('#companyModal').modal({
                keyboard: true
            });
        }
    });
};
$("#company-change").click(function () {
    var position=$("#Cposition").val(),
        tell=$("#Ctell").val(),
        address=$("#Caddress").val(),
        duty=$("#Cproject").val(),
        id=parseInt($("#getid").val());
    $.ajax({
        url:"/company/jobMessagechange",
        type:"post",
        dataType:"json",
        data:{
            id:id,
            tell:tell,
            address:address,
            duty:duty,
            position:position,
            date:d2,
            userid:d1
        },
        success:function(data){
            if(data==true){
                alert("保存成功");
                table.ajax.reload( null, false );
            }
            else{
                alert("保存失败");
            }
        }
    });
});
var tar=0;
var table;
$("#chang-resume").click(function () {var f;
    $.ajax({
        url:"/company/iscompanyMessageGet",
        type:"post",
        dataType:"json",
        async:false,
        success:function (data) {
            if(data==true){
                $.ajax({
                    url:"/company/isJobMessage",
                    type:"post",
                    dataType:"json",
                    success:function (data) {
                        if(data==false){
                            $("#table-div").css("display","none");
                            $("#table-no-alert").css("display","block");
                        }
                        else{
                            $("#table-no-alert").css("display","none");
                            $("#table-div").css("display","block");
                            if(tar==0){
                                table = $("#table").DataTable({
                                    "aLengthMenu":[5,10,15,20],
                                    "searching":false,//禁用搜索
                                    "lengthChange":true,
                                    "paging": true,//开启表格分页
                                    "bProcessing" : true,
                                    "bServerSide" : true,
                                    "bAutoWidth" : false,
                                    "sort" : "position",
                                    "deferRender":false,//延迟渲染
                                    "bStateSave" : false, //在第三页刷新页面，会自动到第一页
                                    "iDisplayLength" : 5,
                                    "iDisplayStart" : 0,
                                    "ordering": false,//全局禁用排序
                                    "ajax": {
                                        "type": "POST",
                                        "url":  "/company/showJobMessage",
                                        "data":function(d){
                                            d.search =""
                                        }
                                    },
                                    "aoColumns":[{
                                        "mData" : "id",
                                        "orderable": false , // 禁用排序
                                        "sDefaultContent" : "",
                                        "sWidth" : "10%"
                                    },{
                                        "mData" : "position",
                                        "orderable": false , // 禁用排序
                                        "sDefaultContent" : "",
                                        "sWidth" : "15%"
                                    },{
                                        "mData" : "date",
                                        "orderable": false , // 禁用排序
                                        "sDefaultContent" : "",
                                        "sWidth" : "15%"
                                    },{
                                        "mData" : "id",
                                        "orderable" : false, // 禁用排序
                                        "sDefaultContent" : '',
                                        "sWidth" : "20%",
                                        "render":function(data, type, full, meta){
                                            data='<button onclick="delet('+data+')" class="btn btn-danger btn-sm" data-id='+data+'><span class="icon-white icon-remove"></span>删 除</button> '+'<button id="deleteOne" onclick="edit('+data+')" class="btn btn-success btn-sm" data-id='+data+'><span class="icon-white icon-edit"></span>编 辑</button> ';
                                            return	data;

                                        }
                                    }
                                    ],
                                    "oLanguage" : { // 国际化配置
                                        "sProcessing" : "正在获取数据，请稍后...",
                                        "sLengthMenu" : "显示 _MENU_ 条",
                                        "sZeroRecords" : "没有找到数据",
                                        "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
                                        "sInfoEmpty" : "记录数为0",
                                        "sInfoFiltered" : "(全部记录数 _MAX_ 条)",
                                        "sInfoPostFix" : "",
                                        "sSearch" : "搜索",
                                        "sUrl" : "",
                                        "oPaginate" : {
                                            "sFirst" : "第一页",
                                            "sPrevious" : "上一页",
                                            "sNext" : "下一页",
                                            "sLast" : "最后一页"
                                        }
                                    },
                                    drawCallback: function(){
                                        rowNum = 1;
                                    },
                                    rowCallback : function(row, data, displayIndex) {
                                        $('td:eq(0)', row).html($("#table").dataTable().fnSettings()._iDisplayStart + rowNum++);
                                        return row;
                                    }
                                });
                                tar=1;
                            }
                            else{
                                table.ajax.reload( null, false );
                            }
                        }
                    }
                });
            }
            else{
                $("#table-div").css("display","none");
                $("#table-no-alert").css("display","block");
            }
        }
    });
});
