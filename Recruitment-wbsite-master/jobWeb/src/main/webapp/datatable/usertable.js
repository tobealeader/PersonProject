var rowNum = 1;
var table = $("#table").DataTable({
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
        "url":  "/user/alluser",
        "data":function(d){
        	d.search =""
        }
	},
	"aoColumns":[{
		"mData" : "userid",
		"orderable": false , // 禁用排序
		"sDefaultContent" : "",
		"sWidth" : "8%"
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
	    	    	"sWidth" : "15%"
	    	    },{
	    	        "mData" : "useremail",
	    	    	"orderable": false , // 禁用排序
	    	    	"sDefaultContent" : "",
	    	    	"sWidth" : "10%"
	    	    },{
	    	    	"mData" : "userid",
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
		"sInfo" : "",
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
var delet = function(id){
	$.ajax({
		url:rooturl+"deluser",
		type:"POST",
		data:{"id":id},
		success:function(data){
			if(data.result=="ok")
			{
				 table.ajax.reload( null, false );
			}else{
				alert("没有,快滚!");
			}
		}
	});
}

var edit = function(id){
	var group = $(".form1 .control-group");
	$('#editUserModal').modal({
		"keyboard":true
	});
	$.ajax({
		url:rooturl+"getUser4id",
		type:"post",
		data:{"id":id},
		success:function(data){
				$(group.get(0)).find("div").find("input").val(data.userName);
				$(group.get(1)).find("div").find("input").val(data.sex);
				$(group.get(2)).find("div").find("input").val(data.email);
				$(group.get(3)).find("div").find("input").val(moment(data.dateStr).format("YYYY-MM-DD HH:mm:ss"));
				$(group.get(4)).find("div").find("input").val(data.phone);
				$(".userid").val(id);
		}
	});
}

var addStu = function(){
	alert(1);
}


