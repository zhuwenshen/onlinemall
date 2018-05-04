$(function(){
	queryEvaluation(1);
	
	$("#search_btn").click(function(){
		queryEvaluation(1);
	});
})

function goPage(num){	
	queryEvaluation(num);
}

function refreshPage(){
	goPage($("#pageNum").val());
}


function queryEvaluation(pageNum){
	if(!pageNum){
		pageNum = 1;
	}	
	
	//加载模态
	var loadingUnique = zeroModal.loading(3);
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/evaluation/query", // url
		data: $("#query_form").serialize(),
		success: function(result) {
			//console.log(result); // 打印服务端返回的数据(调试用)
			if(result.status) {
				var data = result.data;
				var list = data.list;
				//console.log(data)
				var html = "";				
				
				if(list){
					var name = "";
					for(var i=0;i<list.length;i++){			
						
						var op = '<input type="button" class="btn btn-primary btn-xs m-td-btn" onclick=replyEvaluation("'+list[i].id+'") value="回复" /> '
								+' <input type="button" class="btn btn-warning btn-xs m-td-btn" onclick=ignoreEvaluation("'+list[i].id+'") value="忽略" />';
						
						html = html + '<tr>'
									+'<td class="m-trolley-img-td"><img  src="'+list[i].goodsImg+'"></td>'
									+ '<td>'+list[i].goodsName+'</td>'
									+ '<td>'+ms.translateNullText(list[i].classDetails)+'</td>'
									+ '<td>'+list[i].userTime+'</td>'
									+ '<td>'+list[i].score+'</td>'									
									+ '<td>'+ms.translateNullText(list[i].userEvaluation)+'</td>'
									+ '<td>'+op+'</td>'
									+ '</tr>';					
						
					}
				}
				
				
				$("#query_tbody").html(html);
				$("#pageNum").val(result.data.pageNum);
				
				ms.navPage(result.data.pages, result.data.pageNum, "m_nav_ul");
				zeroModal.close(loadingUnique);
				
			} else {
				zeroModal.error(result.msg);
				zeroModal.close(loadingUnique);

			}
		},
		error: function() {				
			zeroModal.error("服务器异常！");
			zeroModal.close(loadingUnique);

		}
	});
}


function ignoreEvaluation(evaluationId){
	zeroModal.confirm("忽略该评价？", function(){
		//加载模态
		var loadingUnique = zeroModal.loading(3);
		$.ajax({
			type: "POST", // 方法类型
			dataType: "json", // 预期服务器返回的数据类型
			url: ms.path + "/m/evaluation/ignore", // url
			data: {evaluationId:evaluationId},
			success: function(result) {
				//console.log(result); // 打印服务端返回的数据(调试用)
				if(result.status) {
					zeroModal.success(result.msg);
					zeroModal.close(loadingUnique);
					refreshPage();					
				} else {
					zeroModal.error(result.msg);
					zeroModal.close(loadingUnique);
				}
			},
			error: function() {				
				zeroModal.error("服务器异常！");
				zeroModal.close(loadingUnique);
			}
		});
	});
}


function replyEvaluation(evaluationId){
	var zm = zeroModal.show({
        title: "回复评价",
        url:ms.path +"/m/evaluation/reply",
        width:"400px",
        height:"200px",
        max:true,
        onComplete:function(){
        	$("#evaluationId").val(evaluationId);
        },
        cancel:true,
        cancelTitle:"取消",
        ok:true,
        okTitle: "回复",
        okFn:okFn
    });
	
	function okFn(){
		$("#modal_id").val(zm);
		$("#modal_form").submit();
		return false;
	}
}
