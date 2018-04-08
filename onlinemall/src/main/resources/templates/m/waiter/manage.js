$(function() {
	
	$("#search_btn").click(function(){
		queryMerchantWaiter(1);	
	});	
	
	queryMerchantWaiter(1);	

});

function queryMerchantWaiter(pageNum){
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/waiter/queryMerchantWaiter?pageNum="+pageNum, // url
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
						"<td>" + ms.tlEmpty(list[i].loginId)  + "</td>" +
						"<td>" + ms.translateText(list[i].frozen)  + "</td>" +
						"<td>" + ms.tlEmpty(list[i].unfreezingTime)  + "</td>" ;	
					//TODO 加上重置密码按钮
					
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
	queryMerchantWaiter(p);	
}






