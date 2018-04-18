$(function(){
	
	$("#unshelveU").val($("#unshelveU [selected]").val());
	if(!$("#goodsPriceId").val()){
		initAddName();
		$("#priceU").val(0);
		$("#numU").val(0);
	}
	
	
	
	$("#unshelveU").change(initAddName);
	$("#goodsIdU").change(initAddGoodsClass);	
	$("#goodsIdU").change(selectNowGoodsPrice);
	
	$("#add_form").submit(function(event){
		//阻止表单提交
	    event.preventDefault();
	    //分类必须全部选上
	    var classList = $("#add_form [name='classDetailId']")
	    
	    for(var i=0;i<classList.length;i++){
	    	if(classList[i].value.isBlank()){
	    		zeroModal.error("分类必须全部选上");
	    		return false;
	    	}
	    }	
	   
	    if(!$("#priceU").val()){
	    	zeroModal.error("价格不能为空");
    		return false;
	    }else if(!($("#priceU").val()>0)){
	    	zeroModal.error("价格必须为合法数且大于0");
    		return false;
	    }
	    
	    if($("#numU").val().isBlank()){
	    	zeroModal.error("进货数量不为空");
    		return false;
	    }
	    addFormSubmit();
	});
	
	
})

function classDetailOnchange(){
	$("#add_form [name='classDetailId']").change(selectNowGoodsPrice);
}

function initAddName(){	
	$.ajax({
		type: "GET", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/warehouse/listGoods", // url
		data:{unshelve:$("#unshelveU").val()},
		success: function(result) {	
			if(result.status) {
				var html = '';
				var list = result.data;
				var i = 0;
				
				for(i = 0; i < list.length; i++) {
					html = html + '<option value="'+list[i].id+'">'+list[i].name+'</option>';
				}

				$("#goodsIdU").html(html);	
				$("#goodsIdU").change();
				
				//查询当前的商品信息
				//selectNowGoodsPrice();
				
				
			} else {
				zeroModal.error(result.msg);

			}
		},
		error: function() {				
			zeroModal.error("服务器异常！");

		}
	});
}

function initAddGoodsClass(){	
	if($("#goodsIdU").val().isBlank()) {
		$("#add_form [name='goodsClassU_div']").remove();
		return;
	}
	$.ajax({
		type: "GET", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/warehouse/listGoodsClass", // url
		data:{goodsId:$("#goodsIdU").val()},
		success: function(result) {	
			if(result.status) {
				//移除原本分类
				$("#add_form [name='goodsClassU_div']").remove();
				var data = result.data;
				if(!data|| !data.length ||data.length==0){
					return;
				}
				var html = "";
				
				for (var i = 0; i <data.length; i++){
					html = html +'<div class="form-group" name="goodsClassU_div">'
								+'<label class="col-sm-3 control-label">分类:'+data[i].name+'</label>'
								+'<div class="col-sm-9">'
								+'	<select class="form-control" name="classDetailId">'
								+'  	<option value=""></option>  ';
					var details = data[i].details;
					if(details){
						for(var j=0; j<details.length; j++){
							html = html +'  <option value="'+details[j].id+'">'+details[j].name+'</option>  ' ; 
						}
								
					}
					                                                                                        
					html = html	+' 	</select>'	
								+'</div>'
								+'</div>';	   
				 }
								
				$("#nameU_div").after(html);	
				
				//重新绑定事件
				classDetailOnchange();
				
			} else {
				zeroModal.error(result.msg);

			}
		},
		error: function() {				
			zeroModal.error("服务器异常！");

		}
	});
}

function addFormSubmit(){
	//alert("提交表单");
	var loadingUnique = zeroModal.loading(3);
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/warehouse/saveGoodsPrice", // url
		data: $("#add_form").serialize(),
		success: function(result) {
			var data = result.data;
			if(result.status) {
				
				zeroModal.success(result.msg);
				zeroModal.close(loadingUnique);	
				selectNowGoodsPrice();
				//zeroModal.close($("#modalId").val());
				
			} else {
				zeroModal.close(loadingUnique);
				zeroModal.error(result.msg);

			}
		},
		error: function() {	
			zeroModal.close(loadingUnique);
			zeroModal.error("服务器异常！");

		}
	});
}

function selectNowGoodsPrice(){
	alert("触发查询");
	//var loadingUnique = zeroModal.loading(3);
	$.ajax({
		type: "POST", // 方法类型
		dataType: "json", // 预期服务器返回的数据类型
		url: ms.path + "/m/warehouse/selectGoodsPrice", // url
		data: $("#add_form").serialize(),
		success: function(result) {
			console.log(result)
			if(result.status) {
				var data = result.data;
				console.log(data)
				//赋值
				$("#priceU").val(data.price);
				$("#restMunU").val(data.num);
				$("#allNumU").val(data.allNum);				
				
				//zeroModal.close(loadingUnique);	
				
			} else {
				//zeroModal.close(loadingUnique);
				//zeroModal.error(result.msg);
				$("#priceU").val(0);
				$("#restMunU").val(0);
				$("#allNumU").val(0);	

			}
		},
		error: function() {	
			//zeroModal.close(loadingUnique);
			zeroModal.error("服务器异常！");

		}
	});
}
