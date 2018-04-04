$(function() {
	
	$("#search_btn").click(function(){
		queryMerchantInformation(1);	
	});	
	
	ms.initSelect("certificationLevel","certification_level_of_merchant","");
	queryMerchantInformation(1);	

});

function queryMerchantInformation(pageNum){
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/a/merchant/queryMerchantInformation?pageNum="+pageNum, // url
		data: $('#query_form').serialize(),
		success: function(result) {
			console.log(result); // 打印服务端返回的数据(调试用)
			if(result.status) {
				var tbodyHtml = "";
				var list = result.data.list;
				var i = 0;
				
				for(i = 0; i < list.length; i++) {
					tbodyHtml = tbodyHtml + "<tr>" +
						"<td>" + ms.tlEmpty(list[i].num) + "</td>" +
						"<td>" + ms.tlEmpty(list[i].userName)  + "</td>" +
						"<td>" + ms.tlEmpty(list[i].nameCn)  + "</td>" +
						"<td>" + ms.tlEmpty(list[i].nameEn)  + "</td>" +
						"<td>" + ms.tlEmpty(list[i].certificationLevelTL) + "</td>" +
						"<td>" + ms.tlEmpty(list[i].detailedAddress)  + "</td>" +
						"<td>" + ms.tlEmpty(list[i].phone) + "</td>" +
						"<td>" + list[i].integral  + "</td>" +
						"<td>" + ms.tlEmpty(list[i].businessLicense)  + "</td>" ;	
					
					
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
	queryApprovalMerchant(p);	
}






