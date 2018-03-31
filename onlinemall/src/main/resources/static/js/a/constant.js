$(function() {

	$("#search_btn").click(function(){
		queryConstant(1);		
		//deleteTr("all_checkbox");
		//ms.append_alert("danger",true,"这是警告内容","msg_alert_div");
	});
	
	$("#update_constant_btn").on("click",updateConstant);

	$("#batch_delete_btn").on("click",batchDeleteConstant);
	
	queryConstant(1);

});

function queryConstant(pageNum) {
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/a/queryConstant?pageNum="+pageNum, // url
		data: $('#query_constant_form').serialize(),
		success: function(result) {
			//console.log(result); // 打印服务端返回的数据(调试用)
			if(result.status) {
				//console.log(result.data);
				var tbodyHtml = " ";
				var list = result.data.list;
				var i = 0;
				
				for(i = 0; i < list.length; i++) {
					tbodyHtml = tbodyHtml + "<tr>" +
					"<td>" + "<input type='checkbox' name='ids' id='"+list[i].id+"' value='"+list[i].id+"'>" + "</td>" +
						"<td>" + list[i].kind + "</td>" +
						"<td>" + list[i].kindName + "</td>" +
						"<td>" + list[i].name + "</td>" +
						"<td>" + list[i].nameCn + "</td>" +
						"<td>" + list[i].value1 + "</td>" +
						"<td>" + list[i].useful + "</td>" +
						"<td>" +
//						"<a href='#' style='color:#337ab7;' onclick=detailsConstant("+"'"+ list[i].id + "'"+")>" +
//						"	<span class='glyphicon glyphicon-plus-sign' title='详情'>" +
//						"</span>" +
//						"</a>" +
//						"	&nbsp;" +
						"	<a href='#' style='color:#5cb85c;' onclick=getConstantById("+"'"+ list[i].id + "'"+") >" +
						"	<span class='glyphicon glyphicon-edit ' title='修改 '></span>" +
						"</a>" +
						"	&nbsp;" +
						"	<a href='#' style='color:#FF0000;' onclick=deleteConstant("+"'"+ list[i].id + "'"+")   >" +
						"		<span class='glyphicon glyphicon-remove-circle ' title='删除 '></span>" +
						"</a>" +
						"</td>" +
						"</tr>";
				}

				$("#constant_tbody").html(tbodyHtml);
				$("#pageNum").val(result.data.pageNum);
				
				ms.navPage(result.data.pages, result.data.pageNum, "m_nav_ul");
				
			} else {
				//alert(result.msg);
				ms.append_alert("danger",true,result.msg,"msg_alert_div");

			}
		},
		error: function() {				
			ms.append_alert("danger",true,"服务器异常！","msg_alert_div");

		}
	});
	
	$("#all_checkbox").click(checkedAll);
}

function checkedAll(){
	var checked_status = $("#all_checkbox").prop('checked');
	$("[name='ids']").prop('checked',checked_status);
}

function goPage(p){	
	queryConstant(p);	
}

function detailsConstant(id) {
	//alert("详情" + id);	
}

function getConstantById(id) {
	//alert("修改" + id);
	//显示模态框
	$('#myModal').modal('show');
	//隐藏模态框
	//$('#myModal').modal('hide');
	//show 方法调用之后立即触发该事件
//	$('#myModal').on('show.bs.modal', function (e) {
//		  // do something...
//	});
	
	$.ajax({
		type: "GET", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/a/getConstantById?idU="+id, // url
		success: function(result) {
			console.log(result); // 打印服务端返回的数据(调试用)
			if(result.status) {
				console.log(result.data);
				data = result.data;
				$("#kindU").val(data.kindU);
				$("#kindNameU").val(data.kindNameU);
				$("#nameU").val(data.nameU);
				$("#nameCnU").val(data.nameCnU);
				$("#value1U").val(data.value1U);
				
				if(data.usefulU) {
					//$("#useful1U").attr("checked");
					//$("#useful2U").removeAttr("checked");
					$("#useful1U").prop('checked',true).parent('span').addClass('checked');
					
				}else {
					//$("#useful2U").attr("checked");
					$("#useful2U").prop('checked',true).parent('span').addClass('checked');
				}
				
				
				$("#createTimeU").val(data.createTimeU);
				$("#createNameU").val(data.createNameU);
				$("#remakeU").val(data.remakeU);
				$("#idU").val(data.idU);
				
				$("#msg_alert_myModal").html(" ");
				
				//$("#update_constant_btn").off("click");
				
				
			} else {
				//alert(result.msg);
				ms.append_alert("danger",true,result.msg,"msg_alert_myModal");

			}
		},
		error: function() {				
			ms.append_alert("danger",true,"服务器异常！","msg_alert_myModal");

		}
	});
}

function updateConstant(){
	
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/a/updateConstant", // url
		data: $('#update_constant_form').serialize(),
		success: function(result) {
			//console.log(result); // 打印服务端返回的数据(调试用)
			if(result.status) {
				
				ms.append_alert("success",true,result.msg,"msg_alert_myModal");
				
				goPage($("#pageNum").val());
				
				
			} else {
				//alert(result.msg);
				ms.append_alert("danger",true,result.msg,"msg_alert_myModal");

			}
		},
		error: function() {				
			ms.append_alert("danger",true,"服务器异常！","msg_alert_myModal");

		}
	});
}

function deleteConstant(id) {
	//显示模态框
	$('#delete_constant_model').modal('show');	
	$("#delete_constant_btn").off("click");
	$("#delete_constant_btn").click(function(){				
		$.ajax({
			type: "GET", // 方法类型
			dataType: "json", // 预期服务器返回的数据类型
			url: ms.path + "/a/deleteConstantById?id="+id, // url
			success: function(result) {
				console.log(result); // 打印服务端返回的数据(调试用)
				if(result.status) {
					
					ms.append_alert("success",true,result.msg,"msg_alert_div");
					$('#delete_constant_model').modal('hide');
					deleteTr(id);
					
				} else {
					//alert(result.msg);
					ms.append_alert("danger",true,result.msg,"msg_alert_div");
					$('#delete_constant_model').modal('hide');
				}
			},
			error: function() {				
				ms.append_alert("danger",true,"服务器异常！","msg_alert_div");
				$('#delete_constant_model').modal('hide');
			}
		});
	});	
}

function deleteTr(id){
	$("[id='"+id+"']").parent().parent().remove();
}

function deleteTrByChecked(){
	$("[name='ids']:checked").parent().parent().remove();
}

function batchDeleteConstant(){
	var ids = $("[name='ids']:checked").map(function(index,elem) {
        return $(elem).val();
    }).get().join(',');
	
	//显示模态框
	$('#delete_constant_model').modal('show');	
	$("#delete_constant_btn").off("click");
	$("#delete_constant_btn").click(function(){	
		//deleteTrByChecked();
		//$("#all_checkbox").prop('checked',false);
		//alert(ids);
		$.ajax({
			type: "POST", // 方法类型
			dataType: "json", // 预期服务器返回的数据类型
			url: ms.path + "/a/deleteConstantByIds", // url
			data:{ids:ids},
			success: function(result) {
				console.log(result); // 打印服务端返回的数据(调试用)
				if(result.status) {
					
					ms.append_alert("success",true,result.msg,"msg_alert_div");
					$('#delete_constant_model').modal('hide');
					deleteTrByChecked();
					$("#all_checkbox").prop('checked',false);
					
				} else {
					//alert(result.msg);
					ms.append_alert("danger",true,result.msg,"msg_alert_div");
					$('#delete_constant_model').modal('hide');
				}
			},
			error: function() {				
				ms.append_alert("danger",true,"服务器异常！","msg_alert_div");
				$('#delete_constant_model').modal('hide');
			}
		});
	});	
	
}





