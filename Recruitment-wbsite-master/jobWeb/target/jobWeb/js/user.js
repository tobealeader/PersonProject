
    $("#img-change").click(function () {
        $("#file").click();
    });
    var fileChangeUp=function (event) {
        var files = event.target.files, file;
        if (files && files.length > 0) {
            file = files[0];
            if(file.size > 1024 * 1024 * 2) {
                alert('图片大小不能超过 2MB!');
                return false;
            }
            var URL = window.URL || window.webkitURL;
            var imgURL = URL.createObjectURL(file);
            $("#img-change").attr("src",imgURL);
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
                $("#img-change").attr("src",data.photo);
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
    var kk=0;
    var rowNum2=1;
    var comapnyMessageRead=function (id) {
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
                $('#companyModal').modal({
                    keyboard: true
                });
            }
        });
    };
    $("#show-message").click(function () {
        if(kk==0){
            table2 = $("#jobtable").DataTable({
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
                    "url":  "/user/comapnyMessageRead",
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
                    "mData" : "companyname",
                    "orderable": false , // 禁用排序
                    "sDefaultContent" : "",
                    "sWidth" : "15%"
                },{
                    "mData" : "resumename",
                    "orderable": false , // 禁用排序
                    "sDefaultContent" : "",
                    "sWidth" : "15%"
                },{
                    "mData" : "date",
                    "orderable": false , // 禁用排序
                    "sDefaultContent" : "",
                    "sWidth" : "15%"
                },{
                    "mData" : "status",
                    "orderable": false , // 禁用排序
                    "sDefaultContent" : "",
                    "sWidth" : "12%"
                },{
                    "mData" : "id",
                    "orderable" : false, // 禁用排序
                    "sDefaultContent" : '',
                    "sWidth" : "13%",
                    "render":function(data, type, full, meta){
                        data='<button onclick="comapnyMessageRead('+data+')" class="btn btn-success btn-sm" data-id='+data+'><span class="icon-white icon-edit"></span>查 看</button> ';
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
                    $('td:eq(0)', row).html($("#jobtable").dataTable().fnSettings()._iDisplayStart + rowNum2++);
                    return row;
                }
            });
            kk=1;
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
            url:"/user/userResumeMessageDelete",
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
        var group = $(".form1 .control-group");
        $.ajax({
            url:"/user/userResumeGet",
            type:"post",
            data:{"id":id},
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
                $("#getid").val(data.id);
                d1=data.userid;
                d2=data.date;
                $('#myModal').modal({
                    keyboard: true
                });
            }
        });
    };
    $("#resume-change").click(function () {
        var position=$("#Uposition").val(),
            sex=$("#Usex").val(),
            username=$("#Uname").val(),
            birth=$("#Ubirth").val(),
            tell=$("#Utell").val(),
            email=$("#Uemail").val(),
            address=$("#Uaddress").val(),
            major=$("#Umajor").val(),
            education=$("#Ueducation").val(),
            english=$("#Uenglish").val(),
            university=$("#Uuniversity").val(),
            project=$("#Uproject").val(),
            id=parseInt($("#getid").val());
        $.ajax({
            url:"/user/resumechange",
            type:"post",
            dataType:"json",
            data:{
                id:id,
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
                date:d2,
                userid:d1
            },
            success:function(data){
                if(data.message=="yes"){
                    alert("保存成功");
                    table.ajax.reload( null, false );
                }
                else{
                    alert("保存失败");
                }
            }
        });
    });
    function copyResum(data) {
        $.ajax({
            url:"/user/CopyResume",
            dataType:"json",
            type:"post",
            data:{
                id:data
            },
            success:function (data) {
                if(data==true)
                {
                    table.ajax.reload( null, false );
                }
                else {
                    alert("复制失败");
                }
            }
        })
    }
    var tt=0;
    var table;
    $("#chang-resume").click(function () {
        $.ajax({
            url:"/user/isuserResumeMessage",
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
                    if(tt==0){
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
                                "url":  "/user/userResumeMessage",
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
                                "mData" : "photo",
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
                                    data='<button onclick="delet('+data+')" class="btn btn-danger btn-sm" data-id='+data+'><span class="icon-white icon-remove"></span>删 除</button> '+'<button id="deleteOne" onclick="edit('+data+')" class="btn btn-success btn-sm" data-id='+data+'><span class="icon-white icon-edit"></span>编 辑</button> '
                                    +'<button id="deleteOne" onclick="copyResum('+data+')" class="btn btn-success btn-sm" data-id='+data+'><span class="icon-white icon-edit"></span>复 制</button> ';
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
                        tt=1;
                    }
                    else{
                        table.ajax.reload( null, false );
                    }
                }
            }
        });
    })
