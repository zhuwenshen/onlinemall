$(function() {
	
	$("#search_btn").click(function(){
		queryAdminUser(1);	
	});	
	
	ms.initSelect("sex","sex","");
	ms.initSelect("userType","user_type","");
	queryAdminUser(1);
	
	$("#model_update_btn").click(onFrozen);
	$("#unfrozen_model_btn").click(onUnfrozen);

});

function queryAdminUser(pageNum){
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/a/user/queryUser?pageNum="+pageNum, // url
		data: $('#query_form').serialize(),
		success: function(result) {
			
			console.log(result); // 打印服务端返回的数据(调试用)
			
			if(result.status) {
				var tbodyHtml = "";
				var list = result.data.list;
				var i = 0;
				
				for(i = 0; i < list.length; i++) {
					tbodyHtml = tbodyHtml + "<tr>" +
						"<td>" + ms.tlEmpty(list[i].name) + "</td>" +
						"<td>" + ms.tlEmpty(list[i].phone)  + "</td>" +
						"<td>" + ms.tlEmpty(list[i].email)  + "</td>" +
						"<td>" + ms.tlEmpty(list[i].realName)  + "</td>" +
						"<td>" + ms.tlEmpty(list[i].idNumber) + "</td>" +
						/*"<td>" + ms.tlEmpty(list[i].headPortraitUrl)  + "</td>" +
						"<td>" + list[i].allIntegral  + "</td>" +*/
						"<td>" + list[i].fund  + "</td>" +
						"<td>" + ms.tlEmpty(list[i].sexTL)  + "</td>" +	
						"<td>" + ms.tlEmpty(list[i].userTypeTL)  + "</td>" +	
						"<td>" + ms.tlEmpty(list[i].loginId)  + "</td>" +	
						"<td>" +ms.translateText(list[i].frozen)  + "</td>" +	
						"<td>" + ms.tlEmpty(list[i].unfreezingTime)  + "</td>" ;	
						
					if(list[i].frozen) {
						tbodyHtml = tbodyHtml +	
						"<td>" +
						"<input type='button' value='解封' class='btn btn-sm btn-info'" +
						"onclick=unfrozen("+"'"+ list[i].id + "'"+") />"+	
						"</td>" ;
					}else {
						tbodyHtml = tbodyHtml +	
						"<td>" +
						"<input type='button' value='冻结' class='btn btn-sm btn-warning'" +
						"onclick=frozen("+"'"+ list[i].id + "'"+") />"+	
						"</td>" ;
					}
					
					
					tbodyHtml = tbodyHtml +						
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
	queryAdminUser(p);	
}

function frozen(id){
	$("#idU").val(id);
	$("#msg_alert_myModal").html(" ");
	//显示模态框
	$('#myModal').modal('show');
}

function onFrozen(){
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/a/user/onFrozen", // url
		data: $('#modal_body_form').serialize(),
		success: function(result) {
			console.log(result); // 打印服务端返回的数据(调试用)
			if(result.status) {										
				
				$("#msg_alert_myModal").html(" ");
				
				$('#myModal').modal('hide');
				
				ms.append_alert("success",true,result.msg,"msg_alert_div");
				
				queryAdminUser($("#pageNum").val());
				
			} else {
				
				ms.append_alert("danger",true,result.msg,"msg_alert_myModal");

			}
		},
		error: function() {				
			ms.append_alert("danger",true,"服务器异常！","msg_alert_myModal");

		}
	});
}

function unfrozen(id){
	$("#idUn").val(id);
	$("#msg_alert_myModal").html(" ");
	//显示模态框
	$('#unfrozen_model').modal('show');
}

function onUnfrozen(){
	var idUn = $("#idUn").val();
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/a/user/onUnfrozen?id="+idUn, // url
		success: function(result) {
			console.log(result); // 打印服务端返回的数据(调试用)
			if(result.status) {										
				
				$("#msg_alert_myModal").html(" ");
				
				$('#unfrozen_model').modal('hide');
				
				ms.append_alert("success",true,result.msg,"msg_alert_div");
				queryAdminUser($("#pageNum").val());
				
			} else {
				
				$('#unfrozen_model').modal('hide');
				ms.append_alert("danger",true,result.msg,"msg_alert_div");

			}
		},
		error: function() {		
			$('#unfrozen_model').modal('hide');
			ms.append_alert("danger",true,"服务器异常！","msg_alert_div");

		}
	});
	
}




