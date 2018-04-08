$(function(){
	
	$("#add_form_btn").click(addMerchantWaiter);
	$("#reset_btn").click(resetAdd);
	queryChangedrecently();
	$("#add_form_next_btn").click(resetAdd);
	
});

function addMerchantWaiter(){
	$("#add_form_btn").attr('disabled', "true");
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/waiter/add", // url
		data: $('#add_form').serialize(),
		success: function(result) {
			console.log(result); // 打印服务端返回的数据(调试用)
			if(result.status) {
				
				ms.append_alert("success",true,result.msg,"msg_alert_div");
				$("#add_form_next_btn").removeAttr("disabled");
				queryChangedrecently();
				
				
			} else {
				$("#add_form_btn").removeAttr("disabled");
				ms.append_alert("danger",true,result.msg,"msg_alert_div");

			}
		},
		error: function() {	
			$("#add_form_btn").removeAttr("disabled");
			ms.append_alert("danger",true,"服务器异常！","msg_alert_div");

		}
	});
}

function resetAdd(){
	$("#name").val(" ");
	$("#loginId").val(" ");
	$("#add_form_btn").removeAttr("disabled");
	$("#add_form_next_btn").attr('disabled', "true");
}

function queryChangedrecently(){
	$.ajax({
		type: "GET", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/waiter/queryChangedrecently", // url
		success: function(result) {
			console.log(result); // 打印服务端返回的数据(调试用)
			if(result.status) {
				var tbodyHtml = "";
				var list = result.data.list;
				var i = 0;
				
				for(i = 0; i < list.length; i++) {
					tbodyHtml = tbodyHtml + "<tr>" +
						"<td>" + ms.tlEmpty(list[i].name) + "</td>" +
						"<td>" + ms.tlEmpty(list[i].loginId)  + "</td>" +
						"<td>" + ms.translateText(list[i].frozen)  + "</td>" +
						"<td>" + ms.tlEmpty(list[i].unfreezingTime)  + "</td>" ;	
					//TODO 加上重置密码按钮
					
					tbodyHtml = tbodyHtml +						
							"</tr>";
				}

				$("#recently_tbody").html(tbodyHtml);				
				
			} else {
				ms.append_alert("danger",true,result.msg,"msg_alert_div");

			}
		},
		error: function() {				
			ms.append_alert("danger",true,"服务器异常！","msg_alert_div");

		}
	});
}