$(function() {
	
	function queryConstant(){
		$.ajax({
			type: "POST", // 方法类型
			dataType: "json", // 预期服务器返回的数据类型
			url: ms.path + "/a/queryConstant", // url
			data: $('#query_constant_form').serialize(),
			success: function(result) {
				console.log(result); // 打印服务端返回的数据(调试用)
				if(result.status) {
					console.log(result.data);
					var htm = " ";					
					
					$("#constant_tbody").html(htm);
				} else {
					// alert(result.msg);
					
					
				}
			},
			error: function() {
				alert("服务器异常！");
				
			}
		});
	} 
	
	queryConstant();
	
});