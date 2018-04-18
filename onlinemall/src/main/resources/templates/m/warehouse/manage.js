$(function(){	
	
	//queryWarehouse(1);
	$("#search_btn").click(function(){
		queryWarehouse(1);
	});	
	initManage();
	
	$("#unshelve").change(initName);
	$("#name").change(initGoodsClass);
	
	$("#add_btn").click(addGoodsPrice);
})

function initManage(){	
	initName();
	//initGoodsClass();
	
}

function initName(){	
	$.ajax({
		type: "GET", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/warehouse/listGoods", // url
		data:{unshelve:$("#unshelve").val()},
		success: function(result) {	
			if(result.status) {
				var html = '<option value=""></option>';
				var list = result.data;
				var i = 0;
				
				for(i = 0; i < list.length; i++) {
					html = html + '<option value="'+list[i].id+'">'+list[i].name+'</option>';
				}

				$("#name").html(html);				
				
			} else {
				zeroModal.error(result.msg);

			}
		},
		error: function() {				
			zeroModal.error("服务器异常！");

		}
	});
}

function initGoodsClass(){
	if($("#name").val().isBlank()) {
		$("#query_form [name='goodsClass_div']").remove();
		return;
	}
	$.ajax({
		type: "GET", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/warehouse/listGoodsClass", // url
		data:{goodsId:$("#name").val()},
		success: function(result) {	
			if(result.status) {
				//移除原本分类
				$("#query_form [name='goodsClass_div']").remove();
				var data = result.data;
				if(!data|| !data.length ||data.length==0){
					return;
				}
				var html = "";
				
				for (var i = 0; i <data.length; i++){
					html = html +'<div class="form-group" name="goodsClass_div">'
								+'<label>分类:'+data[i].name+'</label>'
								+'<select class="form-control" name="classDetailId">'
								+'  <option value=""></option>  ';
					var details = data[i].details;
					if(details){
						for(var j=0; j<details.length; j++){
							html = html +'  <option value="'+details[j].id+'">'+details[j].name+'</option>  ' ; 
						}
								
					}
					                                                                                        
					html = html	+' </select>'			
								+'</div>';	   
				 }
								
				$("#name_div").after(html);		
				
			} else {
				zeroModal.error(result.msg);

			}
		},
		error: function() {				
			zeroModal.error("服务器异常！");

		}
	});
}


function queryWarehouse(pageNum){
	
	if(!$("#name").val()){
		zeroModal.error("请选择商品");
		return;
	}
	
	var list;
	//加载模态
	var loadingUnique = zeroModal.loading(3);
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/warehouse/queryGoodsPrice?pageNum="+pageNum, // url
		data: $('#query_form').serialize(),
		success: function(result) {
			//console.log(result); // 打印服务端返回的数据(调试用)
			if(result.status) {
				var tbodyHtml = "";
				list = result.data.list;
				goodsList = list;
				var i = 0;
				var j = 0;
				var detailList;
				
				for(i = 0; i < list.length; i++) {
					tbodyHtml = tbodyHtml + "<tr>" 					
										  + '<td>';
					if(list[i].detailList && list[i].detailList.length) {
						detailList = list[i].detailList;					
						for(j=0; j<detailList.length ; j++) {
							if(detailList[j]){
								tbodyHtml = tbodyHtml +'<label class="m_class_detail">'+detailList[j].name+'</label>';
							}							
						}						
					}
					tbodyHtml = tbodyHtml + '</td>';
					tbodyHtml = tbodyHtml  
									+"<td>￥" + ms.translateZero(list[i].price)  + "</td>" 
									+"<td>" + ms.translateZero(list[i].num) + "</td>" 
									+"<td>" + ms.translateZero(list[i].allNum - list[i].num)  + "</td>" 
									+"<td>" + ms.translateZero(list[i].allNum)  + "</td>" ;
				
					tbodyHtml = tbodyHtml +	"<td>" + 
					
					" <input type='button' value='进货' class='btn btn-sm btn-info'" +
					'onclick=operateGoodsPrice("'+ list[i].id +'",1) />';					
										
					
					tbodyHtml = tbodyHtml +
					" <input type='button' value='退货' class='btn btn-sm btn-info'" +
					'onclick=operateGoodsPrice("'+ list[i].id +'",2) />'+
					
					" <input type='button' value='修改售价' class='btn btn-sm btn-info'" +
					'onclick=operateGoodsPrice("'+ list[i].id +'",3) />';
					
					
					tbodyHtml = tbodyHtml +						
							"</tr>";
				}

				//console.log(tbodyHtml);
				
				$("#query_tbody").html(tbodyHtml);
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

function goPage(p){	
	queryWarehouse(p);	
}

function refreshPage(){
	//alert(1136466);
	queryWarehouse($("#pageNum").val());
}

function addGoodsPrice(){
	var zm = zeroModal.show({
        title: "添加商品价格",
        url:ms.path +"/m/warehouse/addGoodsPrice",
        width:"400px",
        height:"80%",
        max:true,
        onComplete:function(){
        	//$("#goodsId").val(id);
        },
        cancel:true,
        cancelTitle:"取消",
        ok:true,
        okTitle: "保存",
        okFn:okFn
    });
	
	function okFn(){
		$("#modalId").val(zm);
		$("#add_form").submit();
		return false;
	}
}


function operateGoodsPrice(id,type){
	
	var zm = zeroModal.show({
        title: "进货",
        url:ms.path +"/m/warehouse/operate",
        width:"400px",
        height:"80%",
        max:true,
        onComplete:function(){
        	$("#goodsPriceId").val(id);
        	$("#operationType").val(type)
        },
        cancel:true,
        cancelTitle:"取消",
        ok:true,
        okTitle: "保存",
        okFn:okFn
    });
	
	function okFn(){
		$("#modalId").val(zm);		
		$("#add_form").submit();
		return false;
	}
}


