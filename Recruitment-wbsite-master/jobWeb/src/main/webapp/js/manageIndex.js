var cid;
var tar;
var userdelet = function(id){
    $.ajax({
        url:"/manage/userDelete",
        type:"POST",
        data:{"id":id},
        success:function(data){
            if(data==true)
            {
                table1.ajax.reload( null, false );
            }else{
                alert("没有,快滚!");
            }
        }
    });
};

var useredit = function(id){
    $.ajax({
        url:"/manage/userGet",
        type:"post",
        dataType:"json",
        data:{"id":id},
        success:function(data){
            $("#username").val(data.username);
            $("#userdate").val(data.userdate);
            $("#useremil").val(data.useremail);
            $("#usertype").val(data.usertype);
            $("#password").val(data.userpwd);
            $("#resumeNum").val(data.resumeNum);
            cid=data.userid;
            $('#userModal').modal({
                "keyboard":true
            });
        }
     });
};
var companyedit = function(id){
    $.ajax({
        url:"/manage/companyCheckGet",
        type:"post",
        dataType:"json",
        data:{"id":id},
        success:function(data){
            $("#name").html(data.name);
            $("#number").html(data.number);
            $("#type").html(data.type);
            $("#email").html(data.email);
            $("#legalname").html(data.legalname);
            $("#legalnumber").html(data.legalnumber);
            $("#status").html(data.status);
            cid=data.id;
            $("#companyfoot2").css("display","none");
            $("#companyfoot1").css("display","block");
            $('#companyModal').modal({
                "keyboard":true
            });
        }
    });
};
var companyMessageedit = function(id){
    $.ajax({
        url:"/manage/companyMessageGet",
        type:"post",
        dataType:"json",
        data:{"id":id},
        success:function(data){
            $("#name").html(data.name);
            $("#number").html(data.number);
            $("#type").html(data.type);
            $("#email").html(data.email);
            $("#legalname").html(data.legalname);
            $("#legalnumber").html(data.legalnumber);
            $("#status").html(data.status);
            $("#jobNum").html(data.jobNum);
            cid=data.id;
            $("#companyfoot1").css("display","none");
            $("#companyfoot2").css("display","block");
            $('#companyModal').modal({
                "keyboard":true
            });
        }
    });
};
var rowNum1 = 1;
var flag2=0,flag3=0;
var table1 = $("#table1").DataTable({
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
        "url":  "/manage/allperson",
        "data":function(d){
            d.search =""
        }
    },
    "aoColumns":[{
        "mData" : "userid",
        "orderable": false , // 禁用排序
        "sDefaultContent" : "",
        "sWidth" : "7%"
    },{
        "mData" : "username",
        "orderable": false , // 禁用排序
        "sDefaultContent" : "",
        "sWidth" : "10%"
    },{
        "mData" : "userpwd",
        "orderable": false , // 禁用排序
        "sDefaultContent" : "",
        "sWidth" : "10%"
    },{
        "mData" : "userdate",
        "orderable": false , // 禁用排序
        "sDefaultContent" : "",
        "sWidth" : "10%"
    },{
        "mData" : "useremail",
        "orderable": false , // 禁用排序
        "sDefaultContent" : "",
        "sWidth" : "15%"
    },{
        "mData" : "userid",
        "orderable" : false, // 禁用排序
        "sDefaultContent" : '',
        "sWidth" : "20%",
        "render":function(data, type, full, meta){
            data='<button onclick="userdelet('+data+')" class="btn btn-danger btn-sm" data-id='+data+'><span class="icon-white icon-remove"></span>删 除</button> '+'<button id="deleteOne" onclick="useredit('+data+')" class="btn btn-success btn-sm" data-id='+data+'><span class="icon-white icon-edit"></span>编 辑</button> ';
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
        rowNum1 = 1;
    },
    rowCallback : function(row, data, displayIndex) {
        $('td:eq(0)', row).html($("#table1").dataTable().fnSettings()._iDisplayStart + rowNum1++);
        return row;
    }
});
$("#person").click(function () {
    tar=0;
    $("#table2-div").css("display","none");
    $("#table3-div").css("display","none");
    $("#table1-div").css("display","block");
    table1.ajax.reload( null, false );
});
var table2;
var rowNum2 = 1;
$("#company").click(function () {
    tar=1;
    $("#table1-div").css("display","none");
    $("#table3-div").css("display","none");
    $("#table2-div").css("display","block");

    if(flag2==0){
        table2 = $("#table2").DataTable({
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
                "url":  "/manage/allcompany",
                "data":function(d){
                    d.search =""
                }
            },
            "aoColumns":[{
                "mData" : "userid",
                "orderable": false , // 禁用排序
                "sDefaultContent" : "",
                "sWidth" : "7%"
            },{
                "mData" : "username",
                "orderable": false , // 禁用排序
                "sDefaultContent" : "",
                "sWidth" : "10%"
            },{
                "mData" : "userpwd",
                "orderable": false , // 禁用排序
                "sDefaultContent" : "",
                "sWidth" : "10%"
            },{
                "mData" : "userdate",
                "orderable": false , // 禁用排序
                "sDefaultContent" : "",
                "sWidth" : "10%"
            },{
                "mData" : "useremail",
                "orderable": false , // 禁用排序
                "sDefaultContent" : "",
                "sWidth" : "15%"
            },{
                "mData" : "userid",
                "orderable" : false, // 禁用排序
                "sDefaultContent" : '',
                "sWidth" : "20%",
                "render":function(data, type, full, meta){
                    data='<button onclick="userdelet('+data+')" class="btn btn-danger btn-sm" data-id='+data+'><span class="icon-white icon-remove"></span>删 除</button> '+'<button id="deleteOne" onclick="useredit('+data+')" class="btn btn-success btn-sm" data-id='+data+'><span class="icon-white icon-edit"></span>编 辑</button> '+'<button id="" onclick="companyMessageedit('+data+')" class="btn btn-success btn-sm" data-id='+data+'><span class="icon-white icon-edit"></span>公司信息</button> ';
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
                $('td:eq(0)', row).html($("#table2").dataTable().fnSettings()._iDisplayStart + rowNum2++);
                return row;
            }
        });
        flag2=1;
    }
    else if(flag2==1){
        table2.ajax.reload( null, false );
    }
});
var table3;
var rowNum3 = 1;
$("#company-check").click(function () {
    $("#table1-div").css("display","none");
    $("#table2-div").css("display","none");
    $("#table3-div").css("display","block");

    if(flag3==0){
        table3 = $("#table3").DataTable({
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
                "url":  "/manage/companyCheck",
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
                "mData" : "name",
                "orderable": false , // 禁用排序
                "sDefaultContent" : "",
                "sWidth" : "15%"
            },{
                "mData" : "email",
                "orderable": false , // 禁用排序
                "sDefaultContent" : "",
                "sWidth" : "15%"
            },{
                "mData" : "type",
                "orderable": false , // 禁用排序
                "sDefaultContent" : "",
                "sWidth" : "15%"
            },{
                "mData" : "status",
                "orderable": false , // 禁用排序
                "sDefaultContent" : "",
                "sWidth" : "7%"
            },{
                "mData" : "id",
                "orderable" : false, // 禁用排序
                "sDefaultContent" : '',
                "sWidth" : "20%",
                "render":function(data, type, full, meta){
                    data='<button id="deleteOne" onclick="companyedit('+data+')" class="btn btn-success btn-sm" data-id='+data+'><span class="icon-white icon-edit"></span>审 核</button> ';
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
                rowNum3 = 1;
            },
            rowCallback : function(row, data, displayIndex) {
                $('td:eq(0)', row).html($("#table3").dataTable().fnSettings()._iDisplayStart + rowNum3++);
                return row;
            }
        });
        flag3=1;
    }
    else if(flag3==1){
        table3.ajax.reload( null, false );
    }
});
$("#pass").click(function () {
    $.ajax({
        url:"/manage/companyCheckWrite",
        dataType:"json",
        type:"post",
        data:{
            id:cid,
            status:"true"
        },
        success:function (data) {
            if(data==true){
                alert("审核成功");
                table3.ajax.reload( null, false );
            }
            else{
                alert("审核失败");
            }
        }
    })
});
$("#outpass").click(function () {
    $.ajax({
        url:"/manage/companyCheckWrite",
        dataType:"json",
        type:"post",
        data:{
            id:cid,
            status:"false"
        },
        success:function (data) {
            if(data==true){
                alert("审核成功");
                table3.ajax.reload( null, false );
            }
            else{
                alert("审核失败");
            }
        }
    })
});
$("#userSave").click(function () {

    $.ajax({
        url:"/manage/userChange",
        dataType:"json",
        type:"post",
        data:{
            id:cid,
            username:$("#username").val(),
            password:$("#password").val(),
            useremail:$("#useremil").val()
        },
        success:function (data) {
            if(data==true){
                alert("保存成功");
                if(tar==0)
                    table1.ajax.reload( null, false );
                if(tar==1)
                    table2.ajax.reload( null, false );
            }
            else{
                alert("保存失败，500错误");
            }
        },
        error:function (data) {
            alert(data);
        }
    });
    console.log(2);
});