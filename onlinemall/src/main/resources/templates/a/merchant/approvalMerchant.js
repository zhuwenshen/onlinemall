$(function() {
	
	$("#search_btn").click(function(){
		queryApprovalMerchant(1);	
	});
	
	ms.initSelect("approvalType","approval_type_of_merchant","");
	ms.initSelect("certificationWorkflow","certification_workflow_of_merchant","");
	queryApprovalMerchant(1);
	
	$("#model_update_btn").click(onApproval);
	

});

function queryApprovalMerchant(pageNum){
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/a/merchant/queryApprovalMerchant?pageNum="+pageNum, // url
		data: $('#query_form').serialize(),
		success: function(result) {
			console.log(result); // 打印服务端返回的数据(调试用)
			if(result.status) {
				var tbodyHtml = "";
				var list = result.data.list;
				var i = 0;
				
				for(i = 0; i < list.length; i++) {
					tbodyHtml = tbodyHtml + "<tr>" +
						"<td>" + list[i].realName + "</td>" +
						"<td>" + list[i].merchantNum + "</td>" +
						"<td>" + list[i].merchantName + "</td>" +
						"<td>" + list[i].workflowTL + "</td>" +
						"<td>" + ms.tlEmpty(list[i].approvalTypeTL) + "</td>" +
						"<td>" + list[i].applyTime + "</td>" +
						"<td>" + ms.tlEmpty(list[i].approvalTime) + "</td>" +
						"<td>" ;	
					if(list[i].approvalType==0) {
						tbodyHtml = tbodyHtml +
						"<input type='button' value='审核' class='btn btn-sm btn-info'" +
							"onclick=approval("+"'"+ list[i].id + "'"+","+"'"+ list[i].userId + "'"+","+"'"+ list[i].merchantInformationId + "'"+") />";
					
					}
					
					tbodyHtml = tbodyHtml +"</td>" +						
							"</tr>";
				}

				$("#query_tbody").html(tbodyHtml);
				$("#pageNum").val(result.data.pageNum);
				
				ms.navPage(result.data.pages, result.data.pageNum, "m_nav_ul");
				
			} else {
				ms.append_alert("danger",true,result.msg,"msg_alert_div");

			}
		},
		error: function() {				
			ms.append_alert("danger",true,"服务器异常！","msg_alert_div");

		}
	});

}

function goPage(p){	
	queryApprovalMerchant(p);	
}
//审核
function approval(id, userId ,merchantId){
	//显示模态框
	$('#myModal').modal('show');
	//隐藏模态框
	//$('#myModal').modal('hide');
	//show 方法调用之后立即触发该事件
//	$('#myModal').on('show.bs.modal', function (e) {
//		  // do something...
//	});
	
	ms.initSelect("approvalTypeU","approval_type_of_merchant","");
	ms.initSelect("certificationLevelU","certification_level_of_merchant","");
	
	$("#idU").val(id);	
}

//点击了审核的报错按钮
function onApproval(){
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/a/merchant/onApproval", // url
		data: $('#modal_body_form').serialize(),
		success: function(result) {
			console.log(result); // 打印服务端返回的数据(调试用)
			if(result.status) {										
				
				$("#msg_alert_myModal").html(" ");
				
				$('#myModal').modal('hide');
				
				ms.append_alert("success",true,result.msg,"msg_alert_div");
				
				
			} else {
				
				ms.append_alert("danger",true,result.msg,"msg_alert_myModal");

			}
		},
		error: function() {				
			ms.append_alert("danger",true,"服务器异常！","msg_alert_myModal");

		}
	});
}



